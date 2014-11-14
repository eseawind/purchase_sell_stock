/**
 * 
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

package com.shixi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BackupAndRestore {
	private Context mContext = null;
	private String[] fileList = null; // 数据库文件列表
	private int choicePostion = -3; // 选择数据库列表中的位置
	private AlertDialog dialog = null;
	private String BACK_FOLDER = "backup";
	private String appName = "myApp";

	public BackupAndRestore(Context context) {
		mContext = context;
	}

	/**
	 * 恢复数据的Dialog
	 */
	public void restoreDB() {
		fileList = getFileList();
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("恢复");
		builder.setSingleChoiceItems(getFileList(), -1, new DialogClick());
		builder.setPositiveButton("确定", new DialogClick());
		builder.setNegativeButton("取消", new DialogClick());
		builder.show();
	}

	/**
	 * 备份数据库
	 */
	public void backupDB() {
		showDialog("是否备份数据库", 'B');
	}

	/**
	 * 显示一个Dialog 
	 * @param title 标题 ，必须引用资源ID resource ID
	 * @param sign 根据标示调用方法 I - 恢复默认设置 D - 恢复默认设置 H -选择主机
	 */
	private void showDialog(String title, char sign) {
		final char s = sign;
		new AlertDialog.Builder(mContext).setTitle(title)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogI, int which) {
						switch (s) {
						case 'B': // 备份数据库
							if (dialog == null) {
								dialog = awaitDialog(mContext);
							} else {
								dialog.show();
							}
							new ExecuteTask().execute('B');
							
							break;
						default:
							break;
						}
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}

	/**
	 * 备份操作
	 * @return
	 */
	private boolean backUp() {
		boolean isOk = false;
		//File.separator表示文件路径的分隔符
		String sp = File.separator;
		File sdFile = sdCardOk();
		if (sdFile != null) {
			try {
				String[] dbNames = { "basicInfo.db" };
				// 创建日期文件夹
				String folder_date = datePrefix();
				File f = new File(sdFile.getAbsolutePath() + sp + folder_date);
				if (!f.exists()) {
					f.mkdirs();		//可以在不存在的路径中创建文件夹
				}
				for (int i = 0; i < dbNames.length; i++) {
					String dbName = dbNames[i];
					File dbFile = dbOk(dbName);
					if (dbFile != null) {
						File backFile = new File(f.getAbsolutePath() + sp
								+ dbFile.getName());
						backFile.createNewFile();
						isOk = fileCopy(backFile, dbFile.getAbsoluteFile());
						if (!isOk) {
							break;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isOk;
	}

	/**
	 * 时间前缀
	 * @return
	 */
	private String datePrefix() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date(System.currentTimeMillis());
		String str = format.format(date);
		return str;
	}

	/**
	 * 文件夹列表
	 * @return
	 */
	private String[] getFileList() {
		String[] fileList = null;
		File file = sdCardOk();
		if (file != null) {
			File[] list = file.listFiles();
			if (list != null && list.length > 0) {
				fileList = new String[list.length];
				for (int i = 0; i < list.length; i++) {
					fileList[i] = list[i].getName();
				}
			}
		}
		return fileList;
	}

	/**
	 * sdCard是否存在 备份的文件夹是否存在
	 * @return null不能使用
	 */
	private File sdCardOk() {
		File bkFile = null;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			String sp = File.separator;
			String backUpPath = Environment.getExternalStorageDirectory() + sp
					+ appName + sp + BACK_FOLDER;
			bkFile = new File(backUpPath);
			if (!bkFile.exists()) {
				bkFile.mkdirs();
			} else
				return bkFile;
		} else
			Toast.makeText(mContext, "Sdcard 不存在", Toast.LENGTH_SHORT).show();
		return bkFile;
	}

	/**
	 * 恢复数据库
	 * 
	 * @param name 选择的文件名称 选中的数据库名称
	 * @param resoreDbName 需要恢复的数据库名称
	 * @return
	 */
	public boolean restore(String name, File f) {
		boolean isOk = false;
		if (f != null) {
			File dbFile = dbOk(name);
			try {
				// System.out.println("覆盖的名称"+dbName);
				if (dbFile != null) {
					isOk = fileCopy(dbFile, f.getAbsoluteFile());
				} else
					isOk = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isOk;
	}

	/**
	 * 数据库文件是否存在，并可以使用
	 * @return
	 */
	private File dbOk(String dbName) {
		String sp = File.separator;
		String absPath = Environment.getDataDirectory().getAbsolutePath();
		String pakName = mContext.getPackageName();
		
		String dbPath = sp + "data" + sp + absPath + sp + pakName + sp + "databases"
				+ sp + dbName;
		
		File file = new File(dbPath);
		if (file.exists()) {
			return file;
		} else {
			return null;
		}
	}

	/**
	 * 等候动画
	 */
	public AlertDialog awaitDialog(Context context) {
		ProgressBar bar = new ProgressBar(context);
		bar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = 50;
		params.height = 50;
		window.setAttributes(params);
		window.setContentView(bar);
		return dialog;
	}

	/**
	 * 
	 * @param outFile 写入
	 * @param inFile 读取
	 * @throws FileNotFoundException
	 */
	private boolean fileCopy(File outFile, File inFile) throws IOException {
		if (outFile == null || inFile == null) {
			return false;
		}
		boolean isOk = true;
		FileChannel inChannel = new FileInputStream(inFile).getChannel();// 只读
		FileChannel outChannel = new FileOutputStream(outFile).getChannel();// 只写
		try {
			long size = inChannel.transferTo(0, inChannel.size(), outChannel);
			if (size <= 0) {
				isOk = false;
			}
		} catch (IOException e) {
			isOk = false;
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
		return isOk;
	}

	private class DialogClick implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == -1) {// 确定
				if (choicePostion < 0) {
					Toast.makeText(mContext, "选择数据库", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				String sp = File.separator;
				String folderName = fileList[choicePostion];
				String backUpPath = Environment.getExternalStorageDirectory()
						+ sp + appName + sp + BACK_FOLDER + sp + folderName;
				
				File file = new File(backUpPath);
				if (file.isDirectory()) {
					File[] files = file.listFiles();
					boolean isOk = false;
					for (int i = 0; i < files.length; i++) {
						File f = files[i];
						isOk = restore(f.getName(), f);
						if (!isOk) {
							String fail_msg = "恢复失败" + ":" + f.getName();
							Toast.makeText(mContext, fail_msg,
									Toast.LENGTH_SHORT).show();
							return;
						}
					}
					if (isOk) {
						// 如果有数据体现则需要刷新出新的数据

					}
				}
			} else if (which == -2) {// 取消
			} else if (which >= 0) {
				choicePostion = which;
			}
		}
	}

	/**
	 * 执行任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ExecuteTask extends AsyncTask<Character, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Character... params) {
			char c = params[0];
			if (c == 'B') {
				backUp();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (dialog != null) {
				dialog.dismiss();
			}
		}
	}
}

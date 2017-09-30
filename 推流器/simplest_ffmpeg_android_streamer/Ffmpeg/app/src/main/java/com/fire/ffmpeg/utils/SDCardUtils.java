package com.fire.ffmpeg.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class SDCardUtils {
	public static String mSDCardPath;

	public static void init(Context context){
		String path = getSDCardDownloadPath(context);
		if (checkDirWritable(path)) {
			mSDCardPath = path;
		} else {
			mSDCardPath = null;
		}
	}
	
	public static boolean ExistSDCard(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	private static boolean checkDirWritable(String dirPath){
		File file = new File(dirPath);
		if ((file.exists() && file.isDirectory() && file.canWrite()) || file.mkdirs()) {
			String tmpPath = file.getAbsolutePath() + "/test_tmp";
			File tmp = new File(tmpPath);
			return (!tmp.exists() || tmp.delete()) && tmp.mkdirs() && tmp.delete();
		}
		return false;
	}
	
	private static String getSDCardDownloadPath(Context context){
		String mExternalSDCardPath = Environment.getExternalStorageDirectory().getPath() + "/zjsk/";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			File[] files = context.getExternalCacheDirs();
			String path = "";
			if (files != null && files.length > 1 && files[1] != null) {
				path = files[1].getAbsolutePath();
			} else if (files != null && files.length == 1 && files[0] != null) {
				path = files[0].getAbsolutePath();
			}

			if (!path.equals("") && path.contains(context.getPackageName())) {
				mExternalSDCardPath = path.substring(0, path.lastIndexOf(context.getPackageName()) + context.getPackageName().length()) + "/";
			} else if (path.endsWith("/")) {
				mExternalSDCardPath = path + "fire/";
			} else {
				mExternalSDCardPath = path + "/fire/";
			}
		} else {
			mExternalSDCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			if (mExternalSDCardPath.endsWith("/")) {
				mExternalSDCardPath = mExternalSDCardPath + "Android/data/fire/";
			} else {
				mExternalSDCardPath = mExternalSDCardPath + "/Android/data/fire/";
			}
		}
		return mExternalSDCardPath;
	}
}

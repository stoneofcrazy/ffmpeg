package com.fire.ffmpeg.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
	public static String mCacheDirPath;
	
	public static void init(Context context){
		SDCardUtils.init(context);
		File file = context.getExternalCacheDir();
		if (file == null){
			mCacheDirPath = context.getCacheDir().getAbsolutePath();
		} else {
			mCacheDirPath = context.getExternalCacheDir().getAbsolutePath();
		}
		if (mCacheDirPath.endsWith("/")) {
			mCacheDirPath = mCacheDirPath + "peiluo/";
		} else {
			mCacheDirPath = mCacheDirPath + "/peiluo/";
		}
	}
	public static File getDownloadFile(String url){
		String basePath = getDownloadBasePath();
		File filePath = new File(basePath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(basePath + getDownloadUrlFileName(url));
		if (file.exists()) {
			return file;
		} else {
			try {
				boolean isSuccessed = file.createNewFile();
				if (isSuccessed) {
					return file;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String getDownloadUrlFileName(String url){
		StringBuffer sb = new StringBuffer();
		
		String fileName = MD5Utils.md5(url);
		sb.append(fileName);
		
		sb.append(".apk");
		return sb.toString();
	}
	private static String getDownloadBasePath(){
		String filePath;
		if (SDCardUtils.mSDCardPath != null) {
			filePath = SDCardUtils.mSDCardPath + "download/";
		} else {
			filePath = mCacheDirPath + "download/";
		}
		return filePath;
	}
	public static String saveImage(Bitmap bitmap){
		String filePath;
		if (SDCardUtils.mSDCardPath != null) {
			filePath = SDCardUtils.mSDCardPath + "images/";
		} else {
			filePath = mCacheDirPath + "images/";
		}
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
		String name = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+Math.random()+".jpg";
        filePath += name;
        File myCaptureFile =new File(filePath);
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
	}

	/**
	 * 保存文件到files下
	 * @param str
	 * @return
	 */
	public static String saveStringToFile(String str){
		String filePath;
		if (SDCardUtils.mSDCardPath != null) {
			filePath = SDCardUtils.mSDCardPath + "files/";
		} else {
			filePath = mCacheDirPath + "files/";
		}
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		long timestamp = System.currentTimeMillis();
		String time = formatter.format(new Date());
		String fileName = "crash-" + time + "-" + timestamp + ".log";
		filePath += fileName;
		try {
			FileOutputStream fileOutputStream =new FileOutputStream(filePath);
			fileOutputStream.write(str.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	public static String getFilePath(){
		String filePath;
		if (SDCardUtils.mSDCardPath != null) {
			filePath = SDCardUtils.mSDCardPath + "files/";
		} else {
			filePath = mCacheDirPath + "files/";
		}
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath;
	}
	public static String getStringFromFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()){
			return "filePath is not a file";
		}
		StringBuilder result = new StringBuilder();
		try {
			String lineStr = null;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((lineStr = bufferedReader.readLine()) != null){
				result.append(lineStr + System.lineSeparator());
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("zw", e.toString());
		}
		return result.toString();
	}
}

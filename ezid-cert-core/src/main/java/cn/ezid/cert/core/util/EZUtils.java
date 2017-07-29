/*
 *****************************************************************************
 ** (C) Copyright 2013, BC Technology, All Rights Reserved.
 ** This software is the proprietary information of BC, Ltd.
 ** Use is subject to license terms.
 *****************************************************************************
 */
package cn.ezid.cert.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fanwan
 * 
 */
public class EZUtils {
	public enum OSPlatform {
		Windows(1), Linux(2);
		
		private int value;
		private OSPlatform(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	private static OSPlatform osPlatform; 


	public static boolean isValidString(String str) {
		return (str != null) && (!str.isEmpty());
	}

	public static String toString(Object o) {
		if (o == null) {
			return null;
		}
		
		return o.toString();
	}

	public static String toArrayString(Object[] array, String seperator) {
		if (array == null || array.length == 0) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for (Object element : array) {
			sb.append(element.toString()).append(seperator);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String subString(String str, int length) {
		if (str.length() <= length)
			return str;

		return str.substring(0, length);
	}

	public static String formateDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String formateDate(long date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static int getFileSize(File file) {
		if (!file.exists())
			return 0;

		if (file.isDirectory())
			return 0;
		
		try (FileInputStream in = new FileInputStream(file)) {
			return in.available();
		} catch (IOException e) {
			return 0;
		}
	}
	
	public static OSPlatform getOSPlatform() {
		if (osPlatform == null) {
			osPlatform = System.getProperty("os.name").toLowerCase().contains("windows") ? OSPlatform.Windows
					: OSPlatform.Linux;
		}
		return osPlatform;
	}
}

package com.next.timetable;

public class ProductInfo {

	private final static String developerName = "Byeol";
	private final static String productName = "NEXT Timetable Manager";
	private final static String versionCode = "1.0";
	private final static String productFullName = productName + " " + versionCode;

	public static String getDeveloperName() {
		return developerName;
	}

	public static String getProductName() {
		return productName;
	}

	public static String getVersionCode() {
		return versionCode;
	}

	public static String getProductFullName() {
		return productFullName;
	}
}

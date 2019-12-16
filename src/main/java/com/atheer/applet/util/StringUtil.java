package com.atheer.applet.util;

import java.util.StringTokenizer;

public class StringUtil {
	
	public static String pad(String str, char ch, int length) {
		int prefixLength = Math.max((length - str.length()) / 2, 0);
		return rpad(lpad(str, ch, prefixLength + str.length()), ch, length);
	}
	
	public static String lpad(String str, char ch, int length) {
		StringBuilder builder = new StringBuilder();
		while(length-- > str.length()) {
			builder.append(ch);
		}
		return builder.append(str).toString();
	}
	
	public static String rpad(String str, char ch, int length) {
		StringBuilder builder = new StringBuilder();
		builder.append(str);
		while(length-- > str.length()) {
			builder.append(ch);
		}
		return builder.toString();
	}

	public static String wrap(String str, int length) {
		StringBuilder builder = new StringBuilder();
		StringBuilder line = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(str, " \t\n", true);
		while(tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			if(line.length() + token.length() <= length) {
				line.append(token);
			} else {
				builder.append(line.toString()).append("\n");
				line.delete(0, line.length());
				line.append(token);
			}
		}
		builder.append(line.toString());
		
		return builder.toString();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}

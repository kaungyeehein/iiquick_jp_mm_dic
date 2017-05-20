package com.iiquick.util;

public class CharacterEncode {
	
	public static String UnicodeToZawgyi(String unicode) {
		String zawgyi = "";
		
		unicode = unicode.replaceAll("\u104E\u1004\u103A\u1038", "\u104E");
		unicode = unicode.replaceAll("\u102B\u103A", "\u105A");
		unicode = unicode.replaceAll("\u102D\u1036", "\u108E");
		unicode = unicode.replaceAll("\u103F", "\u1086");
				
		return zawgyi;
	}

}

package com.chippo.LoginWeb.crawl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Encode {
	
	public static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
//	public static void main(String[] args) {
//		String baseUrl = "https://www.google.com/search?q=";
//
//        String query = "https://item.taobao.com//item.htm?spm=a21wu.241046-global.4691948847.25.1e64b6cbqKilOL&scm=1007.15423.84311.100200300000001&id=525002371445&pvid=9e495038-2cfa-4ad9-9ead-3dc44eb34cc3";
//        String encodedQuery = encodeValue(query); // Encoding a query string
//
//        String completeUrl = baseUrl + encodedQuery;
//        System.out.println(encodedQuery);
//	}
	//https%3A%2F%2Fitem.taobao.com%2F%2Fitem.htm%3Fspm%3Da21wu.241046-global.4691948847.25.1e64b6cbqKilOL%26scm%3D1007.15423.84311.100200300000001%26id%3D525002371445%26pvid%3D9e495038-2cfa-4ad9-9ead-3dc44eb34cc3

}

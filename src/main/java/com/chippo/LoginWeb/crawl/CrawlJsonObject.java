package com.chippo.LoginWeb.crawl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.http.client.methods.HttpPost;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chippo.LoginWeb.model.ProductsDTO;

public class CrawlJsonObject {

	public static void main(String[] args) {

		try {

			InputStream input = new FileInputStream("573646160366.txt");

			BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String string = streamReader.readLine();
			System.out.println(string);

			System.out.println(string.substring(11));

			JSONObject json = new JSONObject(string.substring(11));

			JSONObject jsonObject = json.getJSONObject("data");
			jsonObject.getJSONObject("item").getString("title");

			JSONArray arrImages = jsonObject.getJSONObject("item").getJSONArray("images");

			String string2 = arrImages.get(0).toString();
			List<String> list = new ArrayList<String>();

			for (int i = 0; i < arrImages.length(); i++) {
				list.add(arrImages.get(i).toString());
			}
			System.out.println("images:");
			for (String string3 : list) {
				System.out.println(string3);
			}
			System.out.println("name: " + jsonObject.getJSONObject("item").getString("title"));
			// get price
			JSONArray arr = jsonObject.getJSONArray("apiStack");
			List<String> list1 = new ArrayList<String>();
			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < arr.length(); i++) {
				list1.add(arr.getJSONObject(i).getString("value"));
				jsonArray.put(arr.getJSONObject(i));
			}
			System.out.println("hi: " + jsonArray.toString());
			JSONObject jbj_data = jsonArray.getJSONObject(0);
			JSONObject price = new JSONObject(jbj_data.getString("value")).getJSONObject("price")
					.getJSONObject("price");
			String priceText = price.getString("priceText");

			String shopName = jsonObject.getJSONObject("seller").getString("shopName");
			System.out.println(shopName);
			// get thuộc tính sảm phẩm.
			JSONArray arrProps = jsonObject.getJSONObject("skuBase").getJSONArray("props");
			System.out.println("-----------------------------");
			List<Object> objects2 = new ArrayList<Object>();
			for (int i = 0; i < arrProps.length(); i++) {

				
				JSONObject jsonProps = new JSONObject(arrProps.get(i).toString());
				List<JSONObject> objects = new ArrayList<JSONObject>();
				
				
				objects.add(jsonProps);

//				System.out.println(objects);

				for (int k = 0; k < objects.size(); k++) {
					ProductsDTO productsDTO = new ProductsDTO();
					JSONObject object = new JSONObject(objects.get(k).toString());
					if (object.getString("name").equals("商品属性")) {
						System.out.println("true");
						JSONArray arrValues = object.getJSONArray("values");
						JSONArray jsonValues = new JSONArray();
						for (int j = 0; j < arrValues.length(); j++) {
							jsonValues.put(arrValues.getJSONObject(j));
						}
						for (int j = 0; j < jsonValues.length(); j++) {
							System.out.print(jsonValues.getJSONObject(j).getString("name"));
							System.out.print(object.getString("pid")+":"+jsonValues.getJSONObject(j).getString("vid"));
							System.out.println();
							productsDTO.setNameProduct(jsonValues.getJSONObject(j).getString("name"));
							productsDTO.setIdpro(object.getString("pid")+":"+jsonValues.getJSONObject(j).getString("vid"));
							objects2.add(productsDTO.getIdpro());
							objects2.add(productsDTO.getNameProduct());
						}
						
					}
					if (object.getString("name").equals("销售属性")) {
						System.out.println("true");
						JSONArray arrValues = jsonProps.getJSONArray("values");
						
						List<String> list2 = new ArrayList<String>();
						for (int j = 0; j < arrValues.length(); j++) {
							JSONObject object1 = new JSONObject(arrValues.get(j).toString());
							System.out.print(object1.getString("name")+" ");
							System.out.print(object.getString("pid")+":"+object1.getString("vid"));
							System.out.println();
//							list2.add(object1.getString("name"));
							productsDTO.setNameProduct(object1.getString("name"));
							productsDTO.setIdpro(object.getString("pid")+":"+object1.getString("vid"));
							objects2.add(productsDTO.getIdpro());
							objects2.add(productsDTO.getNameProduct());
						}
						
						System.out.println();
					}
					
				}
			}
			System.out.println("----------------------------");
			System.out.println(objects2.size());
			for (Object o : objects2) {
				System.out.println(o);
			}
//				if (jsonProps.getString("name").equals("商品属性")) {
//					System.out.println("true");
//					JSONArray arrValues = jsonProps.getJSONArray("values");
//					JSONArray jsonValues = new JSONArray();
//					for (int j = 0; j < arrValues.length(); j++) {
//						jsonValues.put(arrValues.getJSONObject(j));
//					}
//					for (int j = 0; j < jsonValues.length(); j++) {
//						System.out.println(jsonValues.getJSONObject(j).getString("name"));
//					}
//				}
//				if (jsonProps.getString("name").equals("销售属性")) {
//					System.out.println("true");
//					JSONArray arrValues = jsonProps.getJSONArray("values");
//
//					List<String> list2 = new ArrayList<String>();
//					for (int j = 0; j < arrValues.length(); j++) {
//						JSONObject object = new JSONObject(arrValues.get(j).toString());
//						System.out.println(object.getString("name"));
//						list2.add(object.getString("name"));
//					}
//					System.out.println("list: "+list2);
//				}
//			}
			
//			jsonProps.put(arrProps.getJSONObject(0).getJSONArray("values"));

//			String name1 = jsonProps.getJSONArray(0).getJSONObject(0).getString("name");
//			System.out.println("name: "+name1);
			// get Thuộc tính bán hàng
//			JSONArray jsonProps1 = new JSONArray();
//			jsonProps1.put(arrProps.getJSONObject(1).getJSONArray("values"));
//			for (int i = 0; i < jsonProps1.length(); i++) {
//				System.out.println(jsonProps1.getJSONArray(i).getJSONObject(i).getString("name"));
//
//			}
//			
//		
//			System.out.println(jsonProps1.toList());
//			for (int i = 0; i < arrProps.getJSONObject(1).length(); i++) {
//				System.out.println(arrProps.getJSONObject(1).getJSONArray("values"));
//			}
//			System.out.println("name: "+name1);
//			System.out.println("day: " + jsonProps.length() + "\n" + jsonProps.toString());
//			JSONObject json_value = jsonProps.getJSONObject(1);

//            JSONObject value = new JSONObject(jbj_data.getString("value")).getJSONObject("price").getJSONObject("price");

			System.out.println();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

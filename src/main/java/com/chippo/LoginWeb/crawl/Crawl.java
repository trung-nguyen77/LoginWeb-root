package com.chippo.LoginWeb.crawl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chippo.LoginWeb.model.ProductsDTO;
import com.chippo.LoginWeb.model.ProperSkus;
import com.chippo.LoginWeb.model.PropertyProps;
import com.chippo.LoginWeb.model.PropertyPropsValues;
import com.chippo.LoginWeb.model.PropertySku2info;

public class Crawl {

	public ProductsDTO crawl(String URL) {
		ProductsDTO dto = new ProductsDTO();
//		String regex = "(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?";

		if (URL.contains("item.taobao.com")) {
			try {
//			String URL = "https://item.taobao.com/item.htm?spm=a21wu.241046-global.4691948847.21.1e64b6cbqKilOL&scm=1007.15423.84311.100200300000001&id=536324067474&pvid=9e495038-2cfa-4ad9-9ead-3dc44eb34cc3";
				Document document = Jsoup.connect(URL).get();
				String name = document.getElementsByTag("title").html();
//				String imageTag = document.getElementById("J_ImgBooth").getElementsByTag("img").attr("src");

				String price = document.getElementById("J_StrPrice").getElementsByTag("em").last().html();
//				String price = document.select("strong.tb-promo-price").first().getElementsByTag("em").last().html();
				dto.setNameProduct(name);
				dto.setPrice(price);

				List<String> imageSmall = new ArrayList<String>();

				Elements elements = document.getElementById("J_UlThumb").select("img[data-src$=.jpg]").tagName("img");

				for (Element e : elements) {
					imageSmall.add(cutImagesLast(e.attr("data-src")));
				}

				dto.setLinks(imageSmall);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (URL.contains("detail.1688.com")) {
			String cookie = "ali_ab=14.177.239.244.1569635745643.8; cna=tqsVFjfMky4CAQ6x7/QRdmaW; hng=GLOBAL%7Czh-CN%7CUSD%7C999; t=9fdf88b4b698aae17e35f5cffe68f7b7; _tb_token_=5e507b363737; googtrans=/zh-CN/vi; lid=hoangdung1995; __cn_logon__=true; __cn_logon_id__=hoangdung1995; ali_apache_track=c_mid=b2b-2206648231215dcf11|c_lid=hoangdung1995|c_ms=1; ali_apache_tracktmp=c_w_signed=Y; last_mid=b2b-2206648231215dcf11; _is_show_loginId_change_block_=b2b-2206648231215dcf11_false; _show_force_unbind_div_=b2b-2206648231215dcf11_false; _show_sys_unbind_div_=b2b-2206648231215dcf11_false; _show_user_unbind_div_=b2b-2206648231215dcf11_false; __rn_alert__=false; UM_distinctid=16da9fd2a172a0-0184ccd63e8d1b-67e1b3f-100200-16da9fd2a181c0; CNZZDATA1253659577=593358252-1570511187-https%253A%252F%252Fhuopin.1688.com%252F%7C1570511187; taklid=717a4c9519df47cea8740b8bf0bb10cc; alicnweb=touch_tb_at%3D1570515969811%7Clastlogonid%3Dhoangdung1995; sg=551; uc4=id4=0%40U2grF89CXyz2K3n1Aj6FX9Ugw3yrLoNs&nk4=0%40CWUtFhVaTyZj5lc0j5J21uSvr5VpbXs7; _nk_=hoangdung1995; _csrf_token=1570516125019; l=cBNeXz9nqomyEnvSBOfChurza779qQAfcoVzaNbMiICPO_WXpW2NWZBdfK-WCnMNL6rX-3Wm0ejkB0YKDyUB5lUhznG9sErf.; isg=BFRUHFrF9ZtfnmHFI4M2e6NeJZLGrXiXVGDQT-43pl8U2fwjFL2QJ8wT3YlkJbDv";

			try {
				HttpHost proxy = new HttpHost("103.35.185.153", 8000);
				String res = Executor.newInstance().auth(proxy, "QFfxfV", "R55YKn")
						.execute(Request.Get(URL).addHeader("cookie", cookie).viaProxy(proxy)).returnContent()
						.asString();
				Document document = Jsoup.parse(String.valueOf(res));
				String name = document.getElementsByTag("title").html();
				String price = document.select("span.price-now").first().html();
				Elements elements = document.getElementById("dt-tab").getElementsByTag("img").select("img[src$=.jpg]")
						.tagName("img");

				List<String> imagesSmall = new ArrayList<String>();
				for (Element e : elements) {
					imagesSmall.add(cutImagesLast(e.attr("src").toString().substring(6)));
				}
				dto.setNameProduct(name);
				dto.setPrice(price);
				dto.setLinks(imagesSmall);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (URL.contains("1")) {
			try {

				InputStream input = new FileInputStream("573646160366.txt");

				BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				String string = streamReader.readLine();

				JSONObject json = new JSONObject(string.substring(11));

				JSONObject jsonObject = json.getJSONObject("data");
				// name
				jsonObject.getJSONObject("item").getString("title");

				JSONArray arrImages = jsonObject.getJSONObject("item").getJSONArray("images");

				List<String> list = new ArrayList<String>();
				// ảnh
				for (int i = 0; i < arrImages.length(); i++) {
					list.add(arrImages.get(i).toString());
				}
				System.out.println("name: " + jsonObject.getJSONObject("item").getString("title"));
				JSONArray arr = jsonObject.getJSONArray("apiStack");
				
				//price
				List<String> listPrice = new ArrayList<String>();
				JSONArray jsonArray = new JSONArray();

				for (int i = 0; i < arr.length(); i++) {
					listPrice.add(arr.getJSONObject(i).getString("value"));
					jsonArray.put(arr.getJSONObject(i));
				}
				
				JSONObject jbj_data = jsonArray.getJSONObject(0);
				JSONObject price = new JSONObject(jbj_data.getString("value")).getJSONObject("price")
						.getJSONObject("price");
				
				JSONArray arrProps = jsonObject.getJSONObject("skuBase").getJSONArray("props");

				for (int i = 0; i < arrProps.length(); i++) {

					JSONObject jsonProps = new JSONObject(arrProps.get(i).toString());
					if (jsonProps.getString("name").equals("商品属性")) {
						System.out.println("true");
						JSONArray arrValues = jsonProps.getJSONArray("values");
						JSONArray jsonValues = new JSONArray();
						for (int j = 0; j < arrValues.length(); j++) {
							jsonValues.put(arrValues.getJSONObject(j));
						}
						for (int j = 0; j < jsonValues.length(); j++) {
							System.out.println(jsonValues.getJSONObject(j).getString("name"));
							dto.setMethodProductString(jsonValues.getJSONObject(j).getString("name"));
						}
					}
					if (jsonProps.getString("name").equals("销售属性")) {
						System.out.println("true");
						JSONArray arrValues = jsonProps.getJSONArray("values");
						List<String> list2 = new ArrayList<String>();
						for (int j = 0; j < arrValues.length(); j++) {
							JSONObject object = new JSONObject(arrValues.get(j).toString());
							list2.add(object.getString("name"));
						}
						dto.setMethodSellString(list2);
					}
				}
				
				//property
						
				JSONArray arrProp = jsonObject.getJSONObject("skuBase").getJSONArray("props");
				List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
				for (int i = 0; i < arrProp.length(); i++) {
					JSONObject jsonProps = new JSONObject(arrProp.get(i).toString());
					List<JSONObject> objects = new ArrayList<JSONObject>();
					
					objects.add(jsonProps);
					
					for (int k = 0; k < objects.size(); k++) {
						JSONObject object = new JSONObject(objects.get(k).toString());
						if (object.getString("name").equals("商品属性")) {
							JSONArray arrValues = object.getJSONArray("values");
							List<PropertyPropsValues> propertyPropsValues = new ArrayList<PropertyPropsValues>();
							for (int j = 0; j < arrValues.length(); j++) {
								
								String nameValueString = arrValues.getJSONObject(j).getString("name");
								String vid = object.getString("pid") + ":" + arrValues.getJSONObject(j).getString("vid");
								
								PropertyPropsValues propsValues = new PropertyPropsValues();
								propsValues.setVid(vid);
								propsValues.setNameValueString(nameValueString);

								propertyPropsValues.add(propsValues);
							}
							
							PropertyProps props = new PropertyProps();
							props.setNameProps(object.getString("name"));
							props.setPropertyPropsValues(propertyPropsValues);
							
//							List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
							propertyProps.add(props);
//							dto.setPropertyProps(propertyProps);
						}
						
						if (object.getString("name").equals("销售属性")) {
							JSONArray arrValues = jsonProps.getJSONArray("values");
							PropertyProps props = new PropertyProps();
							List<PropertyPropsValues> propertyPropsValues = new ArrayList<PropertyPropsValues>();
							for (int j = 0; j < arrValues.length(); j++) {

								String nameValueString = arrValues.getJSONObject(j).getString("name");
								String vid = object.getString("pid") + ":" + arrValues.getJSONObject(j).getString("vid");
				
								PropertyPropsValues propsValues = new PropertyPropsValues();
								propsValues.setVid(vid);
								propsValues.setNameValueString(nameValueString);

								propertyPropsValues.add(propsValues);
							}
							
							
							props.setNameProps(object.getString("name"));
							props.setPropertyPropsValues(propertyPropsValues);
							
//							List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
							propertyProps.add(props);
//							dto.setPropertyProps(propertyProps);
						}
						if (object.getString("name").equals("颜色分类")) {
							JSONArray arrValues = jsonProps.getJSONArray("values");
							List<PropertyPropsValues> propertyPropsValues = new ArrayList<PropertyPropsValues>();
							for (int j = 0; j < arrValues.length(); j++) {
								
								String nameValueString = arrValues.getJSONObject(j).getString("name");
								String vid = object.getString("pid") + ":" + arrValues.getJSONObject(j).getString("vid");
								String images = arrValues.getJSONObject(j).getString("image");
 								PropertyPropsValues propsValues = new PropertyPropsValues();
								propsValues.setVid(vid);
								propsValues.setNameValueString(nameValueString);
								propsValues.setImages(images);
								
								propertyPropsValues.add(propsValues);
							}
							
							PropertyProps props = new PropertyProps();
							props.setNameProps(object.getString("name"));
							props.setPropertyPropsValues(propertyPropsValues);
							
//							List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
							propertyProps.add(props);
//							dto.setPropertyProps(propertyProps);
						}
						if (object.getString("name").equals("大小")) {
							JSONArray arrValues = jsonProps.getJSONArray("values");
							List<PropertyPropsValues> propertyPropsValues = new ArrayList<PropertyPropsValues>();
							for (int j = 0; j < arrValues.length(); j++) {
							
								String nameValueString = arrValues.getJSONObject(j).getString("name");
								String vid = object.getString("pid") + ":" + arrValues.getJSONObject(j).getString("vid");

								PropertyPropsValues propsValues = new PropertyPropsValues();
								propsValues.setVid(vid);
								propsValues.setNameValueString(nameValueString);

								propertyPropsValues.add(propsValues);
							}
							
							PropertyProps props = new PropertyProps();
							props.setNameProps(object.getString("name"));
							props.setPropertyPropsValues(propertyPropsValues);

	//						List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
							propertyProps.add(props);
	//						dto.setPropertyProps(propertyProps);
						}			
						
					}
					
				}
				dto.setPropertyProps(propertyProps);
				JSONArray arrSkus = jsonObject.getJSONObject("skuBase").getJSONArray("skus");
				List<ProperSkus> properSkuss = new ArrayList<ProperSkus>();
				for (int i = 0; i < arrSkus.length(); i++) {
					JSONObject jsonProps = arrSkus.getJSONObject(i);
					ProperSkus properSkus = new ProperSkus(jsonProps.getString("skuId").toString(), (jsonProps.getString("propPath").toString()));		
					properSkuss.add(properSkus);
				}
				
				// lay id, price, quantity
				JSONArray arrApiStack = jsonObject.getJSONArray("apiStack");
				JSONArray jsonArrayApiStack = new JSONArray();
				for (int i = 0; i < arrApiStack.length(); i++) {
					jsonArrayApiStack.put(arrApiStack.getJSONObject(i));
				}
				JSONObject skuCore = jsonArrayApiStack.getJSONObject(0);
				
				JSONObject sku2info = new JSONObject(skuCore.getString("value")).getJSONObject("skuCore").getJSONObject("sku2info");
		
				List<PropertySku2info> propertySku2infos = new ArrayList<PropertySku2info>();

		//		System.out.println(sku2info);
				for (int i = 0; i < properSkuss.size(); i++) {
			//		JSONObject object = sku2info.getJSONObject(properSkuss.get(i).getSkuId());
					JSONObject object = sku2info.getJSONObject(properSkuss.get(i).getSkuId());
					System.out.println(object.getJSONObject("price").getString("priceText"));
					PropertySku2info sku2info2 = new PropertySku2info(properSkuss.get(i).getSkuId(), object.getString("quantity"), object.getJSONObject("price").getString("priceText"));
					propertySku2infos.add(sku2info2);
				}
				// shopName
				dto.setShopName(jsonObject.getJSONObject("seller").getString("shopName"));
				dto.setNameProduct(jsonObject.getJSONObject("item").getString("title"));
				dto.setPrice(price.getString("priceText"));
				dto.setLinks(list);
				dto.setProperSkus(properSkuss);
				dto.setPropertySku2infos(propertySku2infos);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return dto;
	}

	public String checkPrice(Document document) {

		if (document.getElementById("J_StrPrice").attr("id").toString().equals("J_StrPrice")) {
			return document.getElementById("J_StrPrice").getElementsByTag("em").last().html();
		} else if (document.getElementById("J_StrPriceModBox").attr("id").toString().equals("J_StrPriceModBox")) {
			return document.getElementById("J_StrPriceModBox").getElementsByTag("em").last().html();
		} else {
			return document.getElementById("J_PromoPriceNum").getElementsByTag("em").last().html();
		}
	}

	public String cutImagesLast(String images) {

		if (images.contains(".60x60")) {
			String[] newStrings = images.split(".60x60");
//			for (String string : newStrings) {
//				System.out.println(string);
//				return string;
//			}
			return newStrings[0] + newStrings[1];
		}
		if (images.contains("_50x50.jpg")) {
			String[] newStrings = images.split("_50x50.jpg");
			for (String string : newStrings) {
				return string;
			}
		}
		return null;

	}

	public ProductsDTO crawlJson() {
		ProductsDTO dto = new ProductsDTO();

		try {

			InputStream input = new FileInputStream("573646160366.txt");

			BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String string = streamReader.readLine();

			JSONObject json = new JSONObject(string);

			JSONObject jsonObject = json.getJSONObject("data");
			// name
			jsonObject.getJSONObject("item").getString("title");

			JSONArray arrImages = jsonObject.getJSONObject("item").getJSONArray("images");

			List<String> list = new ArrayList<String>();
			// ảnh
			for (int i = 0; i < arrImages.length(); i++) {
				list.add(arrImages.get(i).toString());
			}
			System.out.println("name: " + jsonObject.getJSONObject("item").getString("title"));
			JSONArray arr = jsonObject.getJSONArray("apiStack");
			
			//price
			List<String> listPrice = new ArrayList<String>();
			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < arr.length(); i++) {
				listPrice.add(arr.getJSONObject(i).getString("value"));
				jsonArray.put(arr.getJSONObject(i));
			}
			
			JSONObject jbj_data = jsonArray.getJSONObject(0);
			JSONObject price = new JSONObject(jbj_data.getString("value")).getJSONObject("price")
					.getJSONObject("price");
//			String priceText = price.getString("priceText");
			
			dto.setNameProduct(jsonObject.getJSONObject("item").getString("title"));
			dto.setPrice(price.getString("priceText"));
			dto.setLinks(list);

		} catch (Exception e) {
			System.out.println(e);
		}

		return dto;

	}
}

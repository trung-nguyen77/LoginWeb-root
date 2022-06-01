package com.chippo.LoginWeb.crawl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.chippo.LoginWeb.model.ProductsDTO;
import com.chippo.LoginWeb.model.ProperSkus;
import com.chippo.LoginWeb.model.PropertyProps;
import com.chippo.LoginWeb.model.PropertyPropsValues;
import com.chippo.LoginWeb.model.PropertySku2info;

public class CrawlProperty {
	public static void main(String[] args) {
		try {
			ProductsDTO dto = new ProductsDTO();
			InputStream input = new FileInputStream("573646160366.txt");

			BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String string = streamReader.readLine();
			JSONObject json = new JSONObject(string.substring(11));
			JSONObject jsonObject = json.getJSONObject("data");

			JSONArray arrProps = jsonObject.getJSONObject("skuBase").getJSONArray("props");
			
			
			List<PropertyProps> propertyProps = new ArrayList<PropertyProps>();
			for (int i = 0; i < arrProps.length(); i++) {
				JSONObject jsonProps = new JSONObject(arrProps.get(i).toString());
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
						System.out.println();
						PropertyProps props = new PropertyProps();
						props.setNameProps(object.getString("name"));
						props.setPropertyPropsValues(propertyPropsValues);

						propertyProps.add(props);
						System.out.println();
					}

					if (object.getString("name").equals("销售属性")) {
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

						propertyProps.add(props);

					}
					if (object.getString("name").equals("颜色分类")) {
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

						propertyProps.add(props);

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

						propertyProps.add(props);
					}		
				}
			}
			JSONArray arrSkus = jsonObject.getJSONObject("skuBase").getJSONArray("skus");
			List<ProperSkus> properSkuss = new ArrayList<ProperSkus>();
			for (int i = 0; i < arrSkus.length(); i++) {
				JSONObject jsonProps = arrSkus.getJSONObject(i);
				ProperSkus properSkus = new ProperSkus(jsonProps.getString("skuId").toString(), (jsonProps.getString("propPath").toString()));		
				properSkuss.add(properSkus);
			}
			
			JSONArray arrApiStack = jsonObject.getJSONArray("apiStack");
			JSONArray jsonArrayApiStack = new JSONArray();
			for (int i = 0; i < arrApiStack.length(); i++) {
				jsonArrayApiStack.put(arrApiStack.getJSONObject(i));
			}
			JSONObject skuCore = jsonArrayApiStack.getJSONObject(0);
			
			JSONObject sku2info = new JSONObject(skuCore.getString("value")).getJSONObject("skuCore").getJSONObject("sku2info");
			List<PropertySku2info> propertySku2infos = new ArrayList<PropertySku2info>();
//			System.out.println(sku2info);
		
			for (int i = 0; i < properSkuss.size(); i++) {
				JSONObject object = sku2info.getJSONObject(properSkuss.get(i).getSkuId());
				System.out.println(object.getJSONObject("price").getString("priceText"));
				PropertySku2info sku2info2 = new PropertySku2info(properSkuss.get(i).getSkuId(), object.getString("quantity"), object.getJSONObject("price").getString("priceText"));
				propertySku2infos.add(sku2info2);
			}
			System.out.println();


		} catch (

		Exception e) {
			System.out.println(e);

		}
	}

}
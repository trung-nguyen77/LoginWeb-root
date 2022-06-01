package com.chippo.LoginWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chippo.LoginWeb.entity.User;
import com.chippo.LoginWeb.repository.UserRepository;

@SpringBootApplication

public class LoginWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginWebApplication.class, args);
//		String cookie = "t=9fdf88b4b698aae17e35f5cffe68f7b7; thw=ca; _fbp=fb.1.1569635789194.646097236; hng=GLOBAL%7Czh-CN%7CUSD%7C999; _tb_token_=5e507b363737; swfstore=228109; googtrans=/zh-CN/vi; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; _m_h5_tk=5418126cbbdc8acf1e5580d8347a0ffa_1570629226828; _m_h5_tk_enc=686955d3864f2008d39d44290874a99d; whl=-1%260%260%261570620809829; cna=tqsVFjfMky4CAQ6x7/QRdmaW; v=0; uc1=cookie14=UoTbnV5u5dptag%3D%3D; l=cBQt1NAcqomyHkKbBOCZourza77OSIRYiuPzaNbMi_5Cl18sxOQOk9wtReJ62AWdt0YB4tm2-gv9-etkqom4AbM8sxAR.; isg=BP__hdfGnquY2JqkPEXFojnhjtOJ5FOGg4mL7pHMm671oB8imbTj1n2y5yj7wCv-; mt=ci%3D-1_0";
//			try {
//			String URL = "https://item.taobao.com/item.htm?spm=a21wu.241046-global.4691948847.11.4f69b6cb0YsZxm&scm=1007.15423.84311.100200300000005&id=560667008484&pvid=21af9c30-b2b1-4dd3-9466-c1ac143e2722";
////			String URL = "https://detail.1688.com/offer/604199841804.html?spm=a26e3.8027625.1999173159.2.4181540cf8xkSt";
//
//			HttpHost proxy = new HttpHost("103.35.185.153", 8000);
//			String res = Executor.newInstance().auth(proxy, "QFfxfV", "R55YKn")
//					.execute(Request.Get(URL).addHeader("cookie", cookie).viaProxy(proxy)).returnContent().asString();
//			Document document = Jsoup.parse(String.valueOf(res));
//			System.out.println(document);
//			System.out.println();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}

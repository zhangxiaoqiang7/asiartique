package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;


public class TestController {
	/**
	 * @Test是junit里面的注解。这里使用junit测试客户端向服务器请求非视图数据（json,xml,string）
	 */
	@Test
	public void testHttp(){
		String result = "";
		String url = "http://localhost:8080/artcenter/user/artifact?id=1";
//		http://139.129.23.50:8080/artcenter/user/account/login?email=123@qq.com&password=0000
//		http://139.129.23.50:8080/artcenter/user/account/signup?username=jack&password=0000&email=123@qq.com
//		http://139.129.23.50:8080/artcenter/user/addinterest?id=22&email=123@qq.com
//		http://139.129.23.50:8080/artcenter/user/artifact?id=1
//		http://139.129.23.50:8080/artcenter/user/fineart?id=22
//		http://139.129.23.50:8080/artcenter/user/profartist?id=1001
//		http://139.129.23.50:8080/artcenter/user/craftsman?id=1002
//		http://139.129.23.50:8080/artcenter/user/event?id=10001
//		http://139.129.23.50:8080/artcenter/user/location?id=123
//		http://139.129.23.50:8080/artcenter/user/allevents?page=0
//		http://139.129.23.50:8080/artcenter/user/allartifacts?page=0
//		http://139.129.23.50:8080/artcenter/user/allprofartists?page=0
//		http://139.129.23.50:8080/artcenter/user/allcraftsmen?page=0
//		http://139.129.23.50:8080/artcenter/user/allfinearts?page=0
		BufferedReader reader=null;
		try {
			URL realUrl = new URL(url);
			
			//下面完成向服务器发送数据
			// 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            // 设置请求报文（这里主要是报文头部信息）
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("name", "chun");
            // 建立实际的连接
            connection.connect();
            
            //下面完成读取从服务器接收的数据
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line=reader.readLine())!=null) result+=line;
            System.out.println(result);
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (reader!=null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

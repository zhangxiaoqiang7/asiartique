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
	 * @Test��junit�����ע�⡣����ʹ��junit���Կͻ�����������������ͼ���ݣ�json,xml,string��
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
			
			//����������������������
			// �򿪺�URL֮�������
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            // ���������ģ�������Ҫ�Ǳ���ͷ����Ϣ��
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("name", "chun");
            // ����ʵ�ʵ�����
            connection.connect();
            
            //������ɶ�ȡ�ӷ��������յ�����
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

package test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author:GGP
 * @Date:2020/6/18 23:07
 * @Description:
 */
public class DemoTest {
    @Test
    public void test_dns()throws Exception{
        InetAddress inetAddress = InetAddress.getByName("www.cqzk.com.cn");
        System.out.println(inetAddress);
        Socket socket = new Socket("www.cqzk.com.cn",80);
        socket.getOutputStream().write(
                ("GET http://www.cqzk.com.cn/ HTTP/1.1\r\n" +
                "Host: www.cqzk.com.cn1\r\n\r\n" +
                "Proxy-Connection: keep-alive1\r\n" +
                "Cache-Control: max-age=01\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Accept-Language: zh-CN,zh;q=0.9\r\n"+
                "Cookie: Hm_lvt_780f12646b56ec5caa7e64e2d303666b=1592402463,1592493719; Hm_lpvt_780f12646b56ec5caa7e64e2d303666b=1592493719\n" +
                "If-None-Match: \"AAAAXLHafHP\"\r\n" +
                "If-Modified-Since: Thu, 18 Jun 2020 12:31:48 GMT\r\n").getBytes());
        socket.getOutputStream().flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line ;
        while (null !=(line = reader.readLine())){
            System.out.println(line);
        }

    }

}

package com.kalvin.kvf.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:调用短信平台进行短信发送
 *
 * @Auther: jingjin
 * @Date: 2020-11-30 15:11
 * @Modified by:
 */
@Slf4j
@PropertySource("classpath:application.yml")//读取application.yml文件
public class SendMsgUtil {
    private static final String Map = null;
    @Value("${msg.massURL}")
    private String massURL;
    @Value("${msg.groupURL}")
    private  String groupURL;
    @Value("${msg.username}")
    private String username ;
    @Value("${msg.password}")
    private String password ;


    /**
     *
     * @param massJsonContent 必须是json格式的字符串
     * @throws Exception
     */
    public StringBuilder doMassSend(String massJsonContent) throws Exception {
        HttpURLConnection conn = getConnection(massURL, username, password);
        StringBuilder strResponse = writeResponse(conn, massJsonContent);
        return strResponse;
    }
    /**
     *
     * @param groupJsonContent 必须是json格式的字符串
     * @throws Exception
     */
    public void doGroupSend(String groupJsonContent) throws Exception {
        HttpURLConnection conn = getConnection(groupURL, username, password);
        writeResponse(conn, groupJsonContent);
    }

    private StringBuilder writeResponse(HttpURLConnection conn, String requestContent) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        String a=new String(requestContent.toString().getBytes("UTF-8"));
        out.write(a);
        out.close();

        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String tmp;
        log.info("********** Response **********");
        while ((tmp = reader.readLine()) != null) {
            response.append(tmp);
        }
        log.info(tmp);
        return response;
    }

    private HttpURLConnection getConnection(String serverURL, String username, String password) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("Accept", "application/json");
        String authorization = generateAuthorization(username, password);
        conn.setRequestProperty("Authorization", authorization);
        conn.connect();
        return conn;
    }

    /**
     * 生成http请求头Authorization串，用于鉴权
     */
    private String generateAuthorization(String username, String password) {
        String md5Pwd = DigestUtils.md5Hex(password);
        String pair = username + ":" + md5Pwd;
        String base= Base64.encodeBase64String(pair.getBytes());
        base = base.replaceAll("[\\s*\t\n\r]", "");
        return base;
    }
}

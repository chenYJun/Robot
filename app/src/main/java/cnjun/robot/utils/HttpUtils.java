package cnjun.robot.utils;

import android.widget.EditText;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Date;

import cnjun.robot.been.ChatMessage;
import cnjun.robot.been.Result;

/**
 * Created by chen on 15-5-25.
 */
public class HttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";

    private static final String API_KEY = "8013b2eec8b61d46264efc0f07b7fd35";

    /**
     * 发送一个消息，得到返回的消息
     * @param msg
     * @return
     */
    public static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        String jsonResult = doGet(msg);
        Gson gson = new Gson();
        Result result = null;
        try {
            result = gson.fromJson(jsonResult, Result.class);
            chatMessage.setMsg(result.getText());
        } catch (Exception e) {
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }

    public static String doGet(String msg) {
        String result = "";
        java.net.URL urlNet;
        HttpURLConnection conn;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        String url = setParams(msg);
        try {
            urlNet = new java.net.URL(url);
            conn = (HttpURLConnection) urlNet.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");

            is = conn.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();

            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);

            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    private static String setParams(String msg) {
        String url = null;
        try {
            url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

}

package com.ymatou.alarmcenter.domain.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
@Service
public class SmsService {

    public void SendMessage(String appId, String phone, String content) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            content = URLEncoder.encode(content, "UTF-8");
            String url = String.format("http://api.smsproxy.ymatou.com/api/Message/SendMessage?appId=%s&phone=%s&content=%s", appId, phone, content);
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(3000).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 204) {
                throw new RuntimeException("出现异常！" + response.getStatusLine().getReasonPhrase());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                httpclient.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

}

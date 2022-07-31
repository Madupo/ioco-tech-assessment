package com.ioco.iocoassessmentbackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;

import static com.ioco.iocoassessmentbackend.util.Constant.ROBOT_URL;

public class HttpHelper {
    public HttpEntity performGet(String url) throws IOException {
        CloseableHttpClient client = createClient();
        HttpGet httpGet = new HttpGet(ROBOT_URL);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        client.close();
        return entity;
    }

    public String performPost(String url,String payload){
//todo create helper classes for this
        return "";
    }

    public String performPatch(String url){
//todo create helper classes for this
        return "";
    }

    public String performDelete(String url){
    return "";
    }

    private CloseableHttpClient createClient(){
        CloseableHttpClient client = HttpClients.createDefault();
        return client;
    }

}

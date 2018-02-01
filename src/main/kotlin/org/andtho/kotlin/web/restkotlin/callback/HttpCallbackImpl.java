package org.andtho.kotlin.web.restkotlin.callback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class HttpCallbackImpl implements ICallback<Request, Response> {
    @Override
    public Response call(Request value) {

        String url = "https://selfsolve.apple.com/wcResults.do";

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("User-Agent", USER_AGENT);

            HttpEntity entity = EntityBuilder.create()
                    .setSerializable(value)
                    .build();

            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new Response();
    }

    public static void main(String[] args) {
        ICallback<Request, Response> httpCallback = new HttpCallbackImpl();
        Response result = httpCallback.call(new Request());
        System.out.println(result);
    }
}

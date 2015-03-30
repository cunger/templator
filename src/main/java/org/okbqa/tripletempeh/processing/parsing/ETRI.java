package org.okbqa.tripletempeh.processing.parsing;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author cunger
 */
public class ETRI implements Parser {
    
    String url;
    String boundaries;
    
    public ETRI() {
        
        url = "http://110.45.246.131:8080/myapp/service/post";
        boundaries = "\\.|\\?";
    }
    
    
    public List<String> getSentences(String text) {
        
        List<String> sentences = new ArrayList<>();
        
        // TODO Do something more sophisticated here!
        for (String sentence : text.split(boundaries)) {
            sentences.add(sentence);
        }
        
        return sentences;
    }
    
    public String parse(String text) {
        
        return request(url,text);
    }
    
    public String request(String url, String text) {
 
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.addHeader("Accept","text/plain");
            HttpEntity entity = new ByteArrayEntity(text.getBytes("UTF-8"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            String parse = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
                        
            return parse.trim();
        
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
}
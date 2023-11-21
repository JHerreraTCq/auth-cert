package com.culqi.certauthbcp.services;
import org.springframework.stereotype.Service;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import com.culqi.certauthbcp.models.AuthCertModel;
import com.culqi.certauthbcp.models.AuthCertRequestModel;

import java.io.IOException;

@Service
public class AuthCertService {
    public AuthCertModel getAuthUrl(AuthCertRequestModel url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpParams params = new BasicHttpParams();
        HttpContext localContext = new BasicHttpContext();
        params.setParameter("http.protocol.handle-redirects", false);

        HttpGet request = new HttpGet(url.getUrl());
        request.addHeader("User-Agent", "Mozilla/5.0");
        request.setParams(params);
        CloseableHttpResponse response = httpClient.execute(request, localContext);
        System.out.println("Request1: " + request);

        Header locationHeader = response.getFirstHeader("location");
        String redirectLocation1 = null;
        if (locationHeader != null) {
            redirectLocation1 = locationHeader.getValue();
            System.out.println("📘 - location1: " + redirectLocation1);
        }

        /************************************************************************/

        String url2 = redirectLocation1;
        HttpGet request2 = new HttpGet(url2);
        request2.addHeader("User-Agent", "Mozilla/5.0");
        request2.setParams(params);
        CloseableHttpResponse response2 = httpClient.execute(request2, localContext);

        System.out.println("Request2: " + request2);

        Header locationHeader2 = response2.getFirstHeader("location");
        String redirectLocation2 = null;
        if (locationHeader2 != null) {
            redirectLocation2 = locationHeader2.getValue();
            System.out.println("📘 - location2: " + redirectLocation2);
        }

        /************************************************************************/

        String url3 = redirectLocation2;
        HttpGet request3 = new HttpGet(url3);
        request3.addHeader("User-Agent", "Mozilla/5.0");
        request3.setParams(params);
        CloseableHttpResponse response3 = httpClient.execute(request3, localContext);

        System.out.println("Request3: " + request3);

        Header locationHeader3 = response3.getFirstHeader("location");
        String redirectLocation3 = null;
        if (locationHeader3 != null) {
            redirectLocation3 = locationHeader3.getValue();
            System.out.println("📘 - location3: " + redirectLocation3);
        }

        /************************************************************************/

        String url4 = redirectLocation3;
        HttpGet request4 = new HttpGet(url4);
        request4.addHeader("User-Agent", "Mozilla/5.0");
        request4.setParams(params);
        System.out.println("Todo el request: " + request4.getAllHeaders());
        CloseableHttpResponse response4 = httpClient.execute(request4, localContext);

        System.out.println("Request4: " + request4);

        Header locationHeader4 = response4.getFirstHeader("location");
        String redirectLocation4 = null;
        if (locationHeader4 != null) {
            redirectLocation4 = locationHeader4.getValue();
            System.out.println("📘 - location4: " + redirectLocation4);
        }

        String[] requestParams = redirectLocation4.split("\\?");
        String[] codeParam = requestParams[1].split("\\&");
        String[] exchangeCode = codeParam[0].split("=");

        System.out.println("📗 - Código de intercambio: " + exchangeCode[1]);
        AuthCertModel authCertModel = new AuthCertModel();
        authCertModel.setOcCode(exchangeCode[1]);
        return authCertModel;
    }
}
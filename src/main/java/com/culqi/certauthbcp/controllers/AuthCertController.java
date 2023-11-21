package com.culqi.certauthbcp.controllers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.culqi.certauthbcp.models.AuthCertModel;
import com.culqi.certauthbcp.models.AuthCertRequestModel;
import com.culqi.certauthbcp.services.AuthCertService;

@RestController
@RequestMapping("/auth-cert")
public class AuthCertController {
    @Autowired
    AuthCertService authCertService;

    @PostMapping()
    public AuthCertModel getAuthUrl(@RequestBody AuthCertRequestModel url) throws ClientProtocolException, IOException {
        return authCertService.getAuthUrl(url);
    }
}
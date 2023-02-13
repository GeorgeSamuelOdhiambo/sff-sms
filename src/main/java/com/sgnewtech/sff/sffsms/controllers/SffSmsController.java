package com.sgnewtech.sff.sffsms.controllers;

import com.sgnewtech.sff.sffsms.services.SffSmsServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SffSmsController {

    @Value("${clientUrl}")
    private String clientUrl;
    @Autowired
    SffSmsServices sffSmsServices;

    @RequestMapping(value = "/api/send-sms", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String processSendSms(@RequestBody String r) {

        return sffSmsServices.sendSMS(r);
    }
}

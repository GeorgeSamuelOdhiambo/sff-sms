package com.sgnewtech.sff.sffsms.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sgnewtech.sff.sffsms.payload.SmsRequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class SffSmsServices {

    @Value("${clientUrl}")
    private String clientUrl;

    @Value("${clientPass}")
    private String clientPass;

    Logger logger = LoggerFactory.getLogger(SffSmsServices.class);

    public String sendSMS(String r) {
        logger.info("Sending SMS ... ");
        Gson gson = new Gson();
        JsonObject jsonObj = new JsonObject();
        try {
            SmsRequestBody res = gson.fromJson(r, SmsRequestBody.class);
            jsonObj.addProperty("status", "SUCCESS");
            jsonObj.addProperty("telephone", res.getTelephone());
            jsonObj.addProperty("client", res.getClient());
            jsonObj.addProperty("message", res.getMessage());
            jsonObj.addProperty("clientPass", clientPass);

            //Post to gateway using curl
            @RequestMapping(value = ())
            System.out.println(clientUrl+"/pnumber="+res.getTelephone()+"&sender="+res.getClient()+"&message="+res.getMessage().toString()+".&PWD="+clientPass);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            jsonObj.addProperty("error", e.getMessage());
        }

        return jsonObj.toString();
    }
}
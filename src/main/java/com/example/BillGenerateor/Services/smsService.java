package com.example.BillGenerateor.Services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.math.BigDecimal;

@Service
public class smsService{
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "your twilio number";
    public static final String AUTH_TOKEN = "your twilio number";

    public void sendSms(String smsNumber, String smsMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber(smsNumber), new PhoneNumber("your twilio number"),smsMessage)
                .create();
        System.out.println(message.getSid());
    }

    public void sendWpMessage(String wpNumber, String wpMessage){
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:"+wpNumber),
                            new com.twilio.type.PhoneNumber("whatsapp:+your twilio number"),
                            wpMessage)
                    .create();

            System.out.println(message.getSid());
    }
}

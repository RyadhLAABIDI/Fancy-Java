/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

/**
 *
 * @author DELL
 */
public class SmS {

    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "ACa5d20f34a5c8ac4a180fcc6ba0f2333a";
    public static final String AUTH_TOKEN = "546c1b1c692d6deed053dab25c911ff8";

    public SmS() {
    }

    public void send_sms(String To, String Txt) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+21652532874"),
//                "Your message")
//                .create();
        Message message = Message.creator(new com.twilio.type.PhoneNumber(To),new com.twilio.type.PhoneNumber("+16076955499"), Txt).create();

        System.out.println(message.getSid());
    }
}

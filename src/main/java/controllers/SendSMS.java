package controllers;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SendSMS  {
    public static final String ACCOUNT_SID = System.getenv("AC3e7c4fb14b4c902709bc986f10f29f5c");
    public static final String AUTH_TOKEN = System.getenv("a734dc1ae9533b42e8031e036cc45278");
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(""),
                        new com.twilio.type.PhoneNumber("+13156586121"),
                        "Bonjour ' . ', ' " +
                                "Nous sommes heureux de vous informer que votre demande de virement  " +
                                "a été approuvée avec succès " +
                                "Cordialement, [ EFB]")
                .create();

        System.out.println(message.getSid());
    }
}

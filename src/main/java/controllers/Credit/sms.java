package controllers.Credit;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class sms {
    //
    final String ACCOUNT_SID = "ACc40b9e75de7514c2a72010d35f824f26";
    final String AUTH_TOKEN = "adf4869dd60ba8242df23846c144ca58";

    @FXML
    private TextField phonenumberfieled;

    @FXML
    private TextField textfieled;

    @FXML
    void senssms(MouseEvent event) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Remove this line -> Twilio.init(accountSid, authToken);

        // Phone number to send the SMS to
        String toPhoneNumber = phonenumberfieled.getText();

        // Text message to send
        String messageBody = textfieled.getText();

        // Send the SMS
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),  // to
                        new PhoneNumber("+13342928320"),  // from
                        messageBody)
                .create();

        System.out.println("SMS sent: " + messageBody);
    }
}

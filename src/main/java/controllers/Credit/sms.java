package controllers.Credit;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class sms {
    //
    final String ACCOUNT_SID = "AC2dc3c96e5eaecb94c5425ab65f689f6e";
    final String AUTH_TOKEN = "97895badcf378978038558e7d50dbc6f";

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
                        new PhoneNumber("+13395007652"),  // from
                        messageBody)
                .create();

        System.out.println("SMS sent: " + messageBody);
    }
}

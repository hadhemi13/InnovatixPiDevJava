//package yesserTest.otp;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.PhoneAuthProvider;
//import java.util.concurrent.TimeUnit;
//
//public class OTPGenerator {
//    public static void generateOTP(String phoneNumber) throws FirebaseAuthException {
//        FirebaseAuth.getInstance().setTenantId("your-tenant-id"); // Si vous utilisez des tenants
//
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber, // Numéro de téléphone
//                60, // Durée de validité de l'OTP en secondes
//                TimeUnit.SECONDS,
//                null, // Contexte optionnel
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    // Callbacks en cas de succès ou d'échec
//                }
//        );
//    }
//}

////package yesserTest;
////
////import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
////import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
////import com.google.api.client.http.ByteArrayContent;
////import com.google.api.client.http.HttpRequestInitializer;
////import com.google.api.client.http.javanet.NetHttpTransport;
////import com.google.api.client.json.JsonFactory;
////import com.google.api.services.drive.Drive;
////import com.google.api.services.drive.DriveScopes;
////import com.google.api.services.drive.model.File;
//////import java.io.File;
////
////import java.io.FileInputStream;
////import java.io.IOException;
////import java.security.GeneralSecurityException;
////import java.util.Collections;
////
////public class GoogleDriveUploader {
////
////    private static final String APPLICATION_NAME = "InnovatixPiDevJava";
////    private static final String CREDENTIALS_FILE_PATH = "/path/to/your/credentials.json";
////
////    public static void uploadPDF(String filePath) throws IOException, GeneralSecurityException {
////        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
////        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
////                .createScoped(Collections.singleton(DriveScopes.DRIVE));
////
////
////        Drive service = new Drive.Builder(HTTP_TRANSPORT, JsonFactory , setHttpTimeout(credential))
////                .setApplicationName(APPLICATION_NAME)
////                .build();
////
////        File fileMetadata = new File();
////        fileMetadata.setName("Your PDF File Name");
////
////        java.io.File pdfFile = new java.io.File(filePath);
////        ByteArrayContent content = new ByteArrayContent("application/pdf", pdfFile);
////
////        File uploadedFile = service.files().create(fileMetadata, content)
////                .setFields("id")
////                .execute();
////
////        System.out.println("File ID: " + uploadedFile.getId());
////    }
////
////    private static HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
////        return httpRequest -> {
////            requestInitializer.initialize(httpRequest);
////            httpRequest.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
////            httpRequest.setReadTimeout(3 * 60000);  // 3 minutes read timeout
////        };
////    }
////
////}
////
//package yesserTest;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.ByteArrayContent;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory; // Ajout de l'import pour JsonFactory
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//import com.google.api.services.drive.model.File;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//
//public class GoogleDriveUploader {
//
//    private static final String APPLICATION_NAME = "InnovatixPiDevJava";
//    private static final String CREDENTIALS_FILE_PATH = "/path/to/your/credentials.json";
//
//    public static void uploadPDF(String filePath) throws IOException, GeneralSecurityException {
//        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
//                .createScoped(Collections.singleton(DriveScopes.DRIVE));
//
//        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); // Création de l'instance de JsonFactory
//
//        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, setHttpTimeout(credential))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//
//        File fileMetadata = new File();
//        fileMetadata.setName("Your PDF File Name");
//
//        java.io.File pdfFile = new java.io.File(filePath);
//        ByteArrayContent content = new ByteArrayContent("application/pdf", pdfFile);
//
//        File uploadedFile = service.files().create(fileMetadata, content)
//                .setFields("id")
//                .execute();
//
//        System.out.println("File ID: " + uploadedFile.getId());
//    }
//
//    private static HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
//        return httpRequest -> {
//            requestInitializer.initialize(httpRequest);
//            httpRequest.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
//            httpRequest.setReadTimeout(3 * 60000);  // 3 minutes read timeout
//        };
//    }
//}
//

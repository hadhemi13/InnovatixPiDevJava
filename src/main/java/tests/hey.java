//package tests;
//
//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class hey extends Application {
////    sk-proj-LriDTtRiL9JAX7e7olRAT3BlbkFJMVk5lp4pqZYOwERmgSPS
//    @FXML
//    TextArea inputTextArea;
//    @FXML
//    TextArea outputTextArea;
//    @FXML
//    Button generateButton;
//    @Override
//
//    public void start(Stage primaryStage) {
//
//         generateButton.setText("Générer");
//
//        generateButton.setOnAction(e -> {
//            try {
//                String prompt = inputTextArea.getText();
//                String response = OpenAIApiClient.callOpenAI(prompt);
//                outputTextArea.setText(response);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//
//        VBox root = new VBox(10, inputTextArea, generateButton, outputTextArea);
//        Scene scene = new Scene(root, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Application OpenAI");
//        primaryStage.show();
//    }
//
//        public static void main(String[] args) {
//            launch(args);
//        }
//
//}

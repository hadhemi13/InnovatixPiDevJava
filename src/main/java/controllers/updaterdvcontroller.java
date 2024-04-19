package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.MyDatabase;

public class updaterdvcontroller {




        @FXML
        private Button btnsave;

        @FXML
        private HBox chartContainer;

        @FXML
        private Pane content_area;

        @FXML
        private DatePicker datedebutlabel;

        @FXML
        private TextField employename;

        @FXML
        private TextField heurelabel;

        @FXML
        private TextField idclientlabel;

        @FXML
        private ChoiceBox<Integer> idcreditchoise;

        @FXML
        private TextField methodelabel;

        @FXML
        private HBox navBarLogout;

        @FXML
        private Text navFullname;

        @FXML
        private HBox sideBarLogout;



    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    private int id;

    public void initDatardv(int rdv) {
        String req = "SELECT * FROM rdv WHERE id = ?";
        con = MyDatabase.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement(req)) {
            statement.setInt(1, rdv); // Set the value of the parameter
            ResultSet resultSet = statement.executeQuery();
            System.out.println("saadi");
            // Assuming idcreditchoise is a ChoiceBox<Integer> or similar
            ObservableList<Integer> items = FXCollections.observableArrayList();
            while (resultSet.next()) {
                items.add(resultSet.getInt("credit_id"));
                idclientlabel.setText(String.valueOf(resultSet.getInt("idclient")));
                heurelabel.setText(resultSet.getString("heure"));
                datedebutlabel.setValue(LocalDate.parse(resultSet.getString("daterdv")));
                methodelabel.setText(resultSet.getString("methode"));
                employename.setText(resultSet.getString("employename"));
        id=rdv;



            }
            idcreditchoise.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Timer timer;


    public void saverdv(ActionEvent actionEvent) {
        String update = "update rdv set  idclient = ?, heure = ?, daterdv = ?, methode = ?, employename = ? where id = ?" ;
        con=  MyDatabase.getInstance().getConnection();

        try {
            st=con.prepareStatement(update);
            st.setInt(1, Integer.parseInt(idclientlabel.getText())); // id_client

            String timeString = heurelabel.getText();
            String[] parts = timeString.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            java.sql.Time time = new java.sql.Time(hours, minutes, 0);

            st.setTime(2, time);
            st.setDate(3, java.sql.Date.valueOf(datedebutlabel.getValue())); // datedebut
            st.setString(4, methodelabel.getText());
            st.setString(5, employename.getText());
            st.setInt(6,id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}











package com.example.myfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class AfterLogin {
    @FXML
    private Button log;
    @FXML
    private Label mess;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField tfArtikelnummer;
    @FXML
    private TextField tfVarumarke;
    @FXML
    private TextField tfDesigner;
    @FXML
    private TextField tfFraktklass;
    @FXML
    private TextField tfFarg;
    @FXML
    private TextField tfHoj;
    @FXML
    private TextField tfMaterial;
    @FXML
    private TextField tfBredd;
    @FXML
    private TextField tfDjup;
    @FXML
    private TextField tfHojd;
    @FXML
    private TableView<Lager> tvLager;
    @FXML
    private TableColumn<Lager, Integer> tcArtikelnummer;
    @FXML
    private TableColumn<Lager, String> tbVarumarke;
    @FXML
    private TableColumn<Lager, String> tcDesigner;
    @FXML
    private TableColumn<Lager, String> tcFraktklass;
    @FXML
    private TableColumn<Lager, String> tcFarg;
    @FXML
    private TableColumn<Lager, String> tcHoj;
    @FXML
    private TableColumn<Lager, String>  tcMaterial;
    @FXML
    private TableColumn<Lager, String>  tcBredd;
    @FXML
    private TableColumn<Lager, String>  tcDjup;
    @FXML
    private TableColumn<Lager, String>  dcHojd;
    @FXML
    private Button btnAnslut;
    @FXML
    private Button btnLaggtill;
    @FXML
    private Button btnRadera;
    @FXML
    private Button btnUppdatera;
    @FXML
    private void UserLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }
    @FXML
    protected void clickMe() {
        // https://stackoverflow.com/questions/17423851/how-to-populate-text-fields-after-clicking-a-record-in-tableview-in-javafx
        try {
            System.out.println(tvLager.getSelectionModel().getSelectedItem().getId());
            tfArtikelnummer.setText(Integer.toString(tvLager.getSelectionModel().getSelectedItem().getId()));
            tfVarumarke.setText(tvLager.getSelectionModel().getSelectedItem().getVarumarke());
            tfDesigner.setText(tvLager.getSelectionModel().getSelectedItem().getDesigner());
            tfDesigner.setText(tvLager.getSelectionModel().getSelectedItem().getDesigner());
            tfFraktklass.setText(tvLager.getSelectionModel().getSelectedItem().getFraktklass());
            tfFarg.setText(tvLager.getSelectionModel().getSelectedItem().getFarg());
            tfHoj.setText(tvLager.getSelectionModel().getSelectedItem().getHoj());
            tfMaterial.setText(tvLager.getSelectionModel().getSelectedItem().getMaterial());
            tfBredd.setText(tvLager.getSelectionModel().getSelectedItem().getBredd());
            tfDjup.setText(tvLager.getSelectionModel().getSelectedItem().getDjup());
            tfHojd.setText(tvLager.getSelectionModel().getSelectedItem().getHojd());
        }
        catch (NullPointerException npe) {
            // UI
        }
    }
    static final String DB_URL = "jdbc:mysql://localhost:3306/LOL";
    static final String USER = "root";
    static final String PASS = "12345678";
    static final String SELECT = "SELECT * FROM Lager";

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        if(event.getSource() == btnAnslut){
            showLager();
            mess.setText("CONNECTED");

        }else if (event.getSource() == btnLaggtill){

            add();
            clearColums();
        }
        else if (event.getSource() == btnRadera){
            radera();
            clearColums();
        }
        else if (event.getSource() == btnUppdatera){
            radera();
            add();
            clearColums();
        }
    }
    public ObservableList<Lager> lagerSaldo(){
        ObservableList<Lager> lagerlista = FXCollections.observableArrayList();
        String Query = SELECT;
        try{
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);
            Lager lager;
            while(resultSet.next()){
                lager = new Lager
                        (resultSet.getInt("Artikelnummer"),
                                resultSet.getString("Varumarke"),
                                resultSet.getString("Designer"),
                                resultSet.getString("Fraktklass"),
                                resultSet.getString("Farg"),
                                resultSet.getString("Hoj"),
                                resultSet.getString("Material"),
                                resultSet.getString("Bredd"),
                                resultSet.getString("Djup"),
                                resultSet.getString("Hojd"));
                lagerlista.add(lager);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lagerlista;
    }

    public void showLager(){
        ObservableList<Lager> myList = lagerSaldo();



        tcArtikelnummer.setCellValueFactory(new PropertyValueFactory<Lager, Integer>("id"));
        tbVarumarke.setCellValueFactory(new PropertyValueFactory<Lager, String>("varumarke"));
        tcDesigner.setCellValueFactory(new PropertyValueFactory<Lager, String>("designer"));
        tcFraktklass.setCellValueFactory(new PropertyValueFactory<Lager, String>("fraktklass"));
        tcFarg.setCellValueFactory(new PropertyValueFactory<Lager, String>("farg"));
        tcHoj.setCellValueFactory(new PropertyValueFactory<Lager, String>("hoj"));
        tcMaterial.setCellValueFactory(new PropertyValueFactory<Lager, String>("material"));
        tcBredd.setCellValueFactory(new PropertyValueFactory<Lager, String>("bredd"));
        tcDjup.setCellValueFactory(new PropertyValueFactory<Lager, String>("djup"));
        dcHojd.setCellValueFactory(new PropertyValueFactory<Lager, String>("hojd"));
        tvLager.setItems(myList);
    }

    private void clearColums() {
        showLager();
        tfArtikelnummer.clear();
        tfVarumarke.clear();
        tfDesigner.clear();
        tfFraktklass.clear();
        tfHoj.clear();
        tfFarg.clear();
        tfMaterial.clear();
        tfBredd.clear();
        tfDjup.clear();
        tfHojd.clear();
    }

    private void add() {
        String Query = "INSERT INTO Lager (Artikelnummer, Varumarke, Designer, Fraktklass, Farg, Hoj, Material, Bredd, Djup, Hojd) VALUES " +
                "("+tfArtikelnummer.getText()+"," +
                "'"+tfVarumarke.getText()+"'," +
                "'"+tfDesigner.getText()+"'," +
                "'"+tfFraktklass.getText()+"'," +
                "'"+tfFarg.getText()+"'," +
                "'"+tfHoj.getText()+"'," +
                "'"+tfMaterial.getText()+"'," +
                "'"+tfBredd.getText()+"'," +
                "'"+tfDjup.getText()+"'," +
                "'"+tfHojd.getText()+"')";

        if (Query.equals("INSERT INTO Lager (Artikelnummer, Varumarke, Designer, Fraktklass, Farg, Hoj, Material, Bredd, Djup, Hojd) VALUES (,'','','','','','','','','')") ||
                Query.substring(0,117).equals("INSERT INTO Lager (Artikelnummer, Varumarke, Designer, Fraktklass, Farg, Hoj, Material, Bredd, Djup, Hojd) VALUES (,'")) {
            System.out.print("Ja");
        }

        else {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();
                statement.executeUpdate(Query);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void radera(){
        String Query = "DELETE FROM Lager WHERE Artikelnummer =" + tfArtikelnummer.getText() + "";

        if (Query.equals("DELETE FROM Lager WHERE Artikelnummer =")) {
            System.out.println("Lägg till varning");
        }
        else {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LOL", "root", "12345678");
                Statement statement = connection.createStatement();
                statement.executeUpdate(Query);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}

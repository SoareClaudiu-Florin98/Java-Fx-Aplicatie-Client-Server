package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proiect.FiguraGeometrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Connection c;
    private ControllerServer controlerServer;

    @Override
    public void init() throws Exception {
        c = DriverManager.getConnection("jdbc:sqlite:DBFiguriGeometrice.db");
        DatabaseMetaData metaData = c.getMetaData();
        try (ResultSet r = metaData.getTables(null, null, "FiguraGeometrica", new String[]{"TABLE"})) {
//            Verificare existenta tabela cu numele FiguraGeometrica
            if (!r.next()) {
                try (Statement s = c.createStatement()) {
                    String comandaCreare = "create table FiguraGeometrica (id number(6),culoare varchar(50),nume varchar(30))";
                    s.executeUpdate(comandaCreare);
                }
            } else {
                try (Statement s = c.createStatement()) {
                    s.executeUpdate("delete from FiguraGeometrica");
                }
            }
        }
        Main at = new Main();
        List<FiguraGeometrica> lista = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("figuri.txt")); //sau figuri.csv

        String linie;
        try {
            while ((linie = in.readLine()) != null) {
                FiguraGeometrica fig = new FiguraGeometrica();
                String[] t = linie.split(",");
                fig.setId(Integer.parseInt(t[0].trim()));
                fig.setCuloare(t[1].trim());
                fig.setNume(t[2].trim());
                lista.add(fig);
                try (Statement s = c.createStatement()) {

                    String comandaInserare = "insert into FiguraGeometrica values('" + fig.getId() + "','" + fig.getCuloare() + "','" +
                            fig.getNume() +"')";
                    s.executeUpdate(comandaInserare);
                }
            }

            } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        if(c != null){
            c.close();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL locatie = getClass().getResource("sample.fxml") ;
        FXMLLoader loader = new FXMLLoader(locatie) ;
        Parent root = loader.load() ;
        controlerServer = (ControllerServer) loader.getController() ;
        controlerServer.setC(c);
        primaryStage.setTitle("SERVER");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

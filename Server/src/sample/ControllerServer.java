package sample;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import proiect.FiguraGeometrica;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerServer {
    private Connection c ;

    private List<FiguraGeometrica> lista= new ArrayList<>() ;
    @FXML private TextArea tout ;
    private ServerSocket serverSocket;
    private boolean serverActiv = false;
    void procesareCerere(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            String nume = in.readObject().toString();
            List<FiguraGeometrica> listaRaspuns = lista.stream().filter(figuraGeometrica-> figuraGeometrica.getNume().contains(nume))
                    .collect(Collectors.toList());
            out.writeObject(listaRaspuns);

        } catch (Exception ex) {
            Platform.runLater(()->{
                tout.appendText(ex.toString()+"\n");
            });
        }
        finally {
            try{
                socket.close();
            }
            catch (Exception ex){}
        }
    }
    private void ascultare() {
        while (serverActiv) {
            try {
                Socket socket = serverSocket.accept();
                Thread firCerere = new Thread(() -> procesareCerere(socket));
                firCerere.start();
            } catch (Exception ex) {
            }
        }
    }
    @FXML private void stop() {
        if (!serverActiv) {
            return;
        }
        try {
            serverSocket.close();
        } catch (Exception ex) {
        }
        serverActiv = false;
        tout.appendText("Stop Server!\n");

    }
    @FXML private void start(){
        if (serverActiv) {
            tout.appendText("Server in functiune!\n");
            return;
        }
        try {
            serverSocket = new ServerSocket(2013);
            serverSocket.setSoTimeout(10000);
            tout.appendText("Server ul a fost pornit!\n");
            serverActiv = true;
            Thread firAscultare = new Thread(() -> ascultare());
            firAscultare.setDaemon(true);
            firAscultare.start();
        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR, ex.toString());
            err.showAndWait();
        }

    }
    public void setC(Connection c) {
        this.c = c;

        try (Statement s = c.createStatement();
             ResultSet r = s.executeQuery("select id,culoare,nume from FiguraGeometrica")) {
            while (r.next())
            {
                FiguraGeometrica figura = new FiguraGeometrica();
                figura.setId(r.getInt(1));
                figura.setCuloare(r.getString(2));
                figura.setNume(r.getString(3));

                lista.add(figura);
            }
        } catch (Exception ex) {
            Alert err = new Alert(Alert.AlertType.ERROR, ex.toString());
            err.showAndWait();
        }


    }
}



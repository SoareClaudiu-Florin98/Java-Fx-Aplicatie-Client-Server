package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import proiect.FiguraGeometrica;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ControllerClient {
    @FXML private TextField tNume ;
    @FXML private TextArea tout ;
    @FXML private TextArea txOut;
    @FXML private void cerere(){
        try(Socket socket = new Socket("localhost",2013)){
            try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                out.writeObject(tNume.getText().trim());
                List<FiguraGeometrica> lista = (List<FiguraGeometrica>) in.readObject();
                if(tNume.getText().length()>1) {
                    tout.clear();
                    lista.forEach(figuraGeometrica -> tout.appendText(figuraGeometrica.toString() + "\n"));
                }
                else{
                    tout.appendText("Introduceti o figura geometrica ! \n");
                }
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }


    }
    @FXML private void golesteLista()
    {
        txOut.clear();
    }

    @FXML
    private void all() {
        try(Socket socket = new Socket("localhost",2013)){
            try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                out.writeObject("");
                List<FiguraGeometrica> lista = (List<FiguraGeometrica>) in.readObject();
                txOut.clear();
                lista.forEach(figuraGeometrica -> txOut.appendText(figuraGeometrica.toString()+"\n"));
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }

    }

}

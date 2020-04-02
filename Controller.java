import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.regex.*;
import java.util.Stack;

public class Controller {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtEntrada;

    @FXML
    private ListView<String> printPila;

    Stack<String> pila;

    ObservableList<String> dataPila = FXCollections.observableArrayList();

    String entrada;
    String[] arrayEntrada;
    int contador = 0;

    @FXML
    public void initialize() {
        pila = new Stack<String>();
    }

    @FXML
    void onClickLimpiar(MouseEvent event) {
        txtEntrada.setText("");
        for (int i = 0; i < pila.size(); i++) {
            pila.pop();
        }
        printPila.getItems().clear();

    }

    @FXML
    void onClickSalir(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void onClickAceptar(MouseEvent event) {

        entrada = txtEntrada.getText();
        arrayEntrada = entrada.split("\\s");
        pila.push("S");
        dataPila.add("Pila: " + pila);
        printPila.setItems(dataPila);
        try {
            if (arrayEntrada[0].equals("function")) {
                contador++;
                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                pila.push("}");
                pila.push("RestoCuerpo");
                pila.push("cuerpo");
                pila.push("{");
                pila.push(")");
                pila.push("(");
                pila.push("primary");
                pila.push("function");
                System.out.println(pila);
                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                reservadasContinuacion();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Cadena no aceptada");
            alert.showAndWait();
        }
    }

    @FXML
    void reservadasContinuacion() {
        if (arrayEntrada.length > 0) {
            if (arrayEntrada[contador].equals("primary()")) {
                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                System.out.println(pila);
                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);


                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                System.out.println(pila);
                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);

                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                System.out.println(pila);
                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                contador++;

                if (arrayEntrada[contador].equals("{")) {
                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);
                    System.out.println(pila);
                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila); // [}, RestoCuerpo, cuerpo]
                    contador++;
                    cuerpo();
                }

            }
        }
    }

    @FXML
    void cuerpo() {
        if (arrayEntrada[contador].equals("displayData")) {
            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            pila.push(";");
            pila.push(")");
            pila.push("data");
            pila.push("(");
            pila.push("displayData");
            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);

            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);
            System.out.println(pila);
            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            contador++;
            data();
        }


    }

    @FXML
    void data() {
        dataPila.add("Sale: " + pila.pop());
        printPila.setItems(dataPila);
    }

}

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

    // @FXML
    // void onClickLimpiar(MouseEvent event) {
    // txtEntrada.setText("");
    // for (int i = 0; i < pila.size(); i++) {
    // pila.pop();
    // }
    // printPila.getItems().clear();
    // }

    @FXML
    void onClickSalir(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void onClickAceptar(MouseEvent event) {

        entrada = txtEntrada.getText();
        arrayEntrada = entrada.split("\\s");

        try {
            pila.push("S");
            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Cadena no aceptada");
            alert.showAndWait();
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

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);
            contador++;
            data();
        } else if (arrayEntrada[contador].equals("var")) {
            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            pila.push(";");
            pila.push(")");
            pila.push("tipo");
            pila.push("(");
            pila.push("takeData");
            pila.push("=");
            pila.push("name");
            pila.push("tipo");
            pila.push("var");

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);

            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);
            contador++;
            tipo();
        }
    }

    @FXML
    void data() {
        if (arrayEntrada[contador].equals("(")) {
            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);
            contador++; // contador de la discordia

            Pattern p = Pattern.compile("'[a-zA-Z]+'");
            Matcher m = p.matcher(arrayEntrada[contador]);

            Pattern p2 = Pattern.compile("[a-zA-Z]+");
            Matcher m2 = p2.matcher(arrayEntrada[contador]);

            if (m.matches()) {
                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                contador++;

                if (arrayEntrada[contador].equals("+")) {
                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    pila.push("name");
                    pila.push("+");
                    pila.push("'texto'");

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    pila.push("Resto");
                    pila.push("Letra");

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);

                    pila.push("Resto");
                    pila.push("Letra");

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);
                    contador++;

                    if (m2.find()) {
                        dataPila.add("Sale: " + pila.pop());
                        printPila.setItems(dataPila);

                        dataPila.add("Pila: " + pila);
                        printPila.setItems(dataPila);
                        System.out.println(pila);
                        contador++;

                        if (arrayEntrada[contador].equals(");")) {
                            dataPila.add("Sale: " + pila.pop());
                            printPila.setItems(dataPila);

                            dataPila.add("Pila: " + pila);
                            printPila.setItems(dataPila);
                            System.out.println(pila);

                            dataPila.add("Sale: " + pila.pop());
                            printPila.setItems(dataPila);

                            dataPila.add("Pila: " + pila);
                            printPila.setItems(dataPila);
                            System.out.println(pila);

                            contador++;
                            restoCuerpo();
                        }
                    }

                } else {

                    pila.push("'texto'");

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop()); // sale 'texto'
                    printPila.setItems(dataPila);

                    pila.push("Resto");
                    pila.push("Letra");

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop()); // sale letra
                    printPila.setItems(dataPila);

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop()); // sale resto
                    printPila.setItems(dataPila);

                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);
                    contador++;

                    if (arrayEntrada[contador].equals(");")) {
                        dataPila.add("Sale: " + pila.pop());
                        printPila.setItems(dataPila);

                        dataPila.add("Pila: " + pila);
                        printPila.setItems(dataPila);
                        System.out.println(pila);

                        dataPila.add("Sale: " + pila.pop());
                        printPila.setItems(dataPila);

                        dataPila.add("Pila: " + pila);
                        printPila.setItems(dataPila);
                        System.out.println(pila);
                        contador++;
                        restoCuerpo();
                    }
                }
            }
        }
    }

    @FXML
    void tipo() {
        if (arrayEntrada[contador].equals("string") || arrayEntrada[contador].equals("int")) {
            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);
            contador++;
            name();
        }
    }

    @FXML
    void name() {
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(arrayEntrada[contador]);

        dataPila.add("Sale: " + pila.pop());
        printPila.setItems(dataPila);

        pila.push("Resto");
        pila.push("Letra");

        dataPila.add("Pila: " + pila);
        printPila.setItems(dataPila);
        System.out.println(pila);

        if (m.find()) {
            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);

            dataPila.add("Sale: " + pila.pop());
            printPila.setItems(dataPila);

            dataPila.add("Pila: " + pila);
            printPila.setItems(dataPila);
            System.out.println(pila);

            contador++;

            if (arrayEntrada[contador].equals("=")) {
                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);
                contador++;

                if (arrayEntrada[contador].equals("takeData")) {
                    dataPila.add("Pila: " + pila);
                    printPila.setItems(dataPila);
                    System.out.println(pila);

                    dataPila.add("Sale: " + pila.pop());
                    printPila.setItems(dataPila);
                    contador++;

                    if (arrayEntrada[contador].equals("(")) {
                        dataPila.add("Pila: " + pila);
                        printPila.setItems(dataPila);
                        System.out.println(pila);

                        dataPila.add("Sale: " + pila.pop());
                        printPila.setItems(dataPila);
                        contador++;

                        if (arrayEntrada[contador].equals("string") || arrayEntrada[contador].equals("int")) {
                            dataPila.add("Pila: " + pila);
                            printPila.setItems(dataPila);
                            System.out.println(pila);

                            dataPila.add("Sale: " + pila.pop());
                            printPila.setItems(dataPila);
                            contador++;

                            if (arrayEntrada[contador].equals(");")) {
                                dataPila.add("Pila: " + pila);
                                printPila.setItems(dataPila);
                                System.out.println(pila);

                                dataPila.add("Sale: " + pila.pop());
                                printPila.setItems(dataPila);

                                dataPila.add("Pila: " + pila);
                                printPila.setItems(dataPila);
                                System.out.println(pila);

                                dataPila.add("Sale: " + pila.pop());
                                printPila.setItems(dataPila);

                                dataPila.add("Pila: " + pila);
                                printPila.setItems(dataPila);
                                System.out.println(pila);
                                contador++;

                                restoCuerpo();
                            }
                        }
                    }
                }

            }
        }
    }

    @FXML
    void restoCuerpo() {
        if (arrayEntrada[contador].equals("displayData") || arrayEntrada[contador].equals("var")) {
            cuerpo();
            contador++;

            if (arrayEntrada[contador].equals("}")) {

                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);

                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                System.out.println(pila);

                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);

                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                System.out.println(pila);
                contador++;
                if (pila.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Cadena aceptada");
                    alert.showAndWait();
                }
            }
        } else {
            if (arrayEntrada[contador].equals("}")) {

                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);

                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                System.out.println(pila);

                dataPila.add("Sale: " + pila.pop());
                printPila.setItems(dataPila);

                dataPila.add("Pila: " + pila);
                printPila.setItems(dataPila);
                System.out.println(pila);
                contador++;
                if (pila.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Cadena aceptada");
                    alert.showAndWait();
                }
            }
        }

    }
}

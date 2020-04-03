** COMANDOS PARA EJECUTAR **

javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml *.java

java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml Principal


// - - EJEMPLOS DE CADENAS -- \\

○ CADENAS ACEPTADAS
    function primary() { var int contador = takeData ( int ); }
    function primary() { displayData ( 'edad' + edad ); }

○ CADENAS NO ACEPTADAS
    function primary() { displayData ( 'Bienvenido' }


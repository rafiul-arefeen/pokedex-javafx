module org.example.pokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.pokedex to javafx.fxml;
    exports org.example.pokedex;
}
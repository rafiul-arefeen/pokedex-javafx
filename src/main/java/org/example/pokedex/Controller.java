package org.example.pokedex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private GridPane tileGrid;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    ScrollPane scrollPane = new ScrollPane();
    @FXML
    private Button favouritesButton;
    private static Controller instance;
    private List<PokemonData> dataList;
    private ArrayList<Integer> searchArray = new ArrayList<>();


    @FXML
    public void initialize() {
        loadPokedex();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterItems(newValue);
        });
    }

    public void loadPokedex(){
        DatabaseController dbController = new DatabaseController();
        dataList = dbController.getAllData();

        if (tileGrid != null) {
            tileGrid.getChildren().clear();
        } else {
            tileGrid = new GridPane();
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 16; i++) {
                tileGrid.setHgap(5);
                tileGrid.setVgap(5);
                tileGrid.setAlignment(Pos.CENTER);

                PokemonData pokemon = dataList.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokemonCard.fxml"));
                StackPane stackPane = fxmlLoader.load();
                stackPane.setStyle("-fx-background-color: " + colourBackground(pokemon.getType1()) + ";");
                CardController cardController = fxmlLoader.getController();
                cardController.setData(pokemon, this);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                tileGrid.add(stackPane, column++, row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scrollPane.setContent(tileGrid);
    }

    public void loadSearchPokedex(){
        if (tileGrid != null) {
            tileGrid.getChildren().clear();
        } else {
            tileGrid = new GridPane();
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 16; i++) {
                if (!searchArray.contains(i + 1)) {
                    continue;
                }

                tileGrid.setHgap(5);
                tileGrid.setVgap(5);
                tileGrid.setAlignment(Pos.CENTER);

                PokemonData pokemon = dataList.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokemonCard.fxml"));
                StackPane stackPane = fxmlLoader.load();
                stackPane.setStyle("-fx-background-color: " + colourBackground(pokemon.getType1()) + ";");
                CardController cardController = fxmlLoader.getController();
                cardController.setData(pokemon, this);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                tileGrid.add(stackPane, column++, row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scrollPane.setContent(tileGrid);
    }

    private String colourBackground(String type){
        String colour;
        if(type.equals("Bug"))
            colour = "#94BC4A";
        else if(type.equals("Dark"))
            colour = "#736C75";
        else if(type.equals("Dragon"))
            colour = "#6A7BAF";
        else if(type.equals("Electric"))
            colour = "#E5C531";
        else if(type.equals("Fairy"))
            colour = "#E397D1";
        else if(type.equals("Fighting"))
            colour = "#CB5F48";
        else if(type.equals("Fire"))
            colour = "#EA7A3C";
        else if(type.equals("Flying"))
            colour = "#7DA6DE";
        else if(type.equals("Ghost"))
            colour = "#846AB6";
        else if(type.equals("Grass"))
            colour = "#71C558";
        else if(type.equals("Ground"))
            colour = "#CC9F4F";
        else if(type.equals("Ice"))
            colour = "#70CBD4";
        else if(type.equals("Poison"))
            colour = "#B468B7";
        else if(type.equals("Psychic"))
            colour = "#E5709B";
        else if(type.equals("Rock"))
            colour = "#B2A061";
        else if(type.equals("Steel"))
            colour = "#89A1B0";
        else if(type.equals("Water"))
            colour = "#539AE2";
        else
            colour = "#AAB09F";

        return colour;
    }

    @FXML
    private String searchButtonClicked(ActionEvent event) {
        if (searchField.getText().isEmpty()){
            return null;
        }
        return searchField.getText();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    @FXML
    private void favouritesButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) favouritesButton.getScene().getWindow();
        currentStage.close();
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FavouritesScreen.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterItems(String searchText){
        searchArray.clear();

        if(searchText.isEmpty()){
            loadPokedex();
        }
        else{
            for (PokemonData pokemon : dataList) {
                if (pokemon.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    searchArray.add(pokemon.getId());
                }
            }
            loadSearchPokedex();
        }
    }
}
package org.example.pokedex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FavouritesController {
    @FXML
    private GridPane tileGrid;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    ScrollPane scrollPane = new ScrollPane();
    @FXML
    private Button backButton;
    private static FavouritesController instance;
    private List<PokemonData> dataList;
    private ArrayList<Integer> searchArray = new ArrayList<>();


    @FXML
    public void initialize() {
        loadPokedex();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterItems(newValue);
        });
    }

    public static FavouritesController getInstance() {
        if (instance == null) {
            instance = new FavouritesController();
        }
        return instance;
    }

    public void loadPokedex(){
        DatabaseController dbController = new DatabaseController();
        dataList = dbController.getAllData();
        tileGrid = new GridPane();

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 16; i++) {
                tileGrid.setHgap(5);
                tileGrid.setVgap(5);
                tileGrid.setAlignment(Pos.CENTER);

                PokemonData pokemon = dataList.get(i);
                if(pokemon.getFavourite().equals("FALSE"))
                    continue;

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

    public void loadSearchFavouritesPokedex(){
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

                if (pokemon.getFavourite().equals("FALSE") || !searchArray.contains(i + 1)) {
                    continue;
                }

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

    @FXML
    private String searchButtonClicked(ActionEvent event) {
        if (searchField.getText().isEmpty()){
            return null;
        }
        return searchField.getText();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Pokedex");
        stage.setScene(scene);
        stage.show();
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
            loadSearchFavouritesPokedex();
        }
    }
}

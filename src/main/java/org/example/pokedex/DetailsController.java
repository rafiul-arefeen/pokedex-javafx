package org.example.pokedex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {

    @FXML
    private Label attack;

    @FXML
    private Button backButton;

    @FXML
    private Label defense;

    @FXML
    private Label generation;

    @FXML
    private Label hp;

    @FXML
    private Label legendary;

    @FXML
    private Button makeFavouriteButton;

    @FXML
    private Label pokemonID;

    @FXML
    private ImageView pokemonImage;

    @FXML
    private Label pokemonName;

    @FXML
    private Label sp_attack;

    @FXML
    private Label sp_defense;

    @FXML
    private Label speed;

    @FXML
    private Label total;

    @FXML
    private Label type1;

    @FXML
    private Label type2;
    @FXML
    private AnchorPane backgroundAnchorPane;
    private Controller mainController;
    private FavouritesController favController;

    public void setData(PokemonData pokemon, Image image, Controller mainController){
        String formattedID = String.format("%03d", pokemon.getId());
        pokemonID.setText("#" + formattedID);
        pokemonName.setText(pokemon.getName());
        type1.setText(pokemon.getType1());
        type2.setText(pokemon.getType2());
        total.setText(pokemon.getTotal());
        hp.setText(pokemon.getHp());
        attack.setText(pokemon.getAttack());
        defense.setText(pokemon.getDefense());
        sp_attack.setText(pokemon.getSp_attack());
        sp_defense.setText(pokemon.getSp_defense());
        speed.setText(pokemon.getSpeed());
        generation.setText(pokemon.getGeneration());
        backgroundAnchorPane.setStyle("-fx-background-color: " + colourBackground(pokemon.getType1()) + ";");

        if(pokemon.getLegendary().equals("FALSE"))
            legendary.setText("No");
        else
            legendary.setText("Yes");

        if(pokemon.getFavourite().equals("FALSE"))
            makeFavouriteButton.setText("Favourite");
        else
            makeFavouriteButton.setText("Unfavourite");

        pokemonImage.setImage(image);
        this.mainController = mainController;

        backButton.setOnAction(event -> {
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
        });

        makeFavouriteButton.setOnAction(event -> {
            if(pokemon.getFavourite().equals("FALSE")) {
                DatabaseController.updateFavouriteColumn(pokemon.getId(), "TRUE");
                makeFavouriteButton.setText("Unfavourite");
            }
            else {
                DatabaseController.updateFavouriteColumn(pokemon.getId(), "FALSE");
                makeFavouriteButton.setText("Favourite");
            }
        });
    }

    public void setData(PokemonData pokemon, Image image, FavouritesController favController){
        String formattedID = String.format("%03d", pokemon.getId());
        pokemonID.setText("#" + formattedID);
        pokemonName.setText(pokemon.getName());
        type1.setText(pokemon.getType1());
        type2.setText(pokemon.getType2());
        total.setText(pokemon.getTotal());
        hp.setText(pokemon.getHp());
        attack.setText(pokemon.getAttack());
        defense.setText(pokemon.getDefense());
        sp_attack.setText(pokemon.getSp_attack());
        sp_defense.setText(pokemon.getSp_defense());
        speed.setText(pokemon.getSpeed());
        generation.setText(pokemon.getGeneration());
        backgroundAnchorPane.setStyle("-fx-background-color: " + colourBackground(pokemon.getType1()) + ";");

        if(pokemon.getLegendary().equals("FALSE"))
            legendary.setText("No");
        else
            legendary.setText("Yes");

        if(pokemon.getFavourite().equals("FALSE"))
            makeFavouriteButton.setText("Favourite");
        else
            makeFavouriteButton.setText("Unfavourite");

        pokemonImage.setImage(image);
        this.favController = favController;

        backButton.setOnAction(event -> {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
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
        });

        makeFavouriteButton.setOnAction(event -> {
            if(pokemon.getFavourite().equals("FALSE")) {
                DatabaseController.updateFavouriteColumn(pokemon.getId(), "TRUE");
                makeFavouriteButton.setText("Unfavourite");
            }
            else {
                DatabaseController.updateFavouriteColumn(pokemon.getId(), "FALSE");
                makeFavouriteButton.setText("Favourite");
            }
        });
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
}

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
import javafx.stage.Stage;

import java.io.IOException;

public class CardController {

    @FXML
    private Label pokemonID;

    @FXML
    private ImageView pokemonImage;

    @FXML
    private Label pokemonName;

    @FXML
    private Label type1;

    @FXML
    private Label type2;

    @FXML
    private Button viewDetailsButton;
    private Controller mainController;
    private FavouritesController favController;

    public void setData(PokemonData pokemon, Controller mainController) {
        this.mainController = mainController;
        String formattedID = String.format("%03d", pokemon.getId());
        pokemonID.setText("#" + formattedID);
        pokemonName.setText(pokemon.getName());
        type1.setText(pokemon.getType1());
        type2.setText(pokemon.getType2());

        if(pokemon.getType2().equals(""))
            type2.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");

        String imagePath = "/images/pokemon/" + pokemon.getName().toLowerCase() + ".png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        pokemonImage.setImage(image);

        viewDetailsButton.setOnAction(event -> {
            System.out.println("Button Clicked");

            try {
                // Load the FXML file
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokemonInfo.fxml"));
                Parent root = fxmlLoader.load();
                DetailsController detailsController = fxmlLoader.getController();
                detailsController.setData(pokemon, image, this.mainController);

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
    }

    public void setData(PokemonData pokemon, FavouritesController favController) {
        this.favController = favController;
        String formattedID = String.format("%03d", pokemon.getId());
        pokemonID.setText("#" + formattedID);
        pokemonName.setText(pokemon.getName());
        type1.setText(pokemon.getType1());
        type2.setText(pokemon.getType2());

        String imagePath = "/images/pokemon/" + pokemon.getName().toLowerCase() + ".png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        pokemonImage.setImage(image);

        viewDetailsButton.setOnAction(event -> {
            System.out.println("Button Clicked");

            try {
                // Load the FXML file
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokemonInfo.fxml"));
                Parent root = fxmlLoader.load();
                DetailsController detailsController = fxmlLoader.getController();
                detailsController.setData(pokemon, image, this.mainController);

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
    }
}
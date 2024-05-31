package org.example.pokedex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    static Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public List<PokemonData> getAllData() {
        List<PokemonData> dataList = new ArrayList<>();

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PokemonDB", "root", "password");

            // Create SQL statement
            statement = connection.createStatement();

            // Execute SQL query
            resultSet = statement.executeQuery("SELECT * FROM PokemonDB.pokemon");

            // Iterate through the result set and map each row to an object
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type1 = resultSet.getString("type1");
                String type2 = resultSet.getString("type2");
                String total = resultSet.getString("total");
                String hp = resultSet.getString("hp");
                String attack = resultSet.getString("attack");
                String defense = resultSet.getString("defense");
                String sp_attack = resultSet.getString("sp_attack");
                String sp_defense = resultSet.getString("sp_defense");
                String speed = resultSet.getString("speed");
                String generation = resultSet.getString("generation");
                String legendary = resultSet.getString("legendary");
                String favourite = resultSet.getString("favourite");

                // Create an object and add it to the list
                PokemonData data = new PokemonData(id, name, type1, type2, total,hp, attack, defense, sp_attack, sp_defense, speed, generation,legendary, favourite);
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }

    public static void updateFavouriteColumn(int pokemonId, String newFavouriteValue) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE pokemon SET favourite = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters
            preparedStatement.setString(1, newFavouriteValue);
            preparedStatement.setInt(2, pokemonId);

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

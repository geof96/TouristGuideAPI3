package com.example.touristguideapi3.repository;

import com.example.touristguideapi3.connectionManager.ConnectionManager;
import com.example.touristguideapi3.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepositoryDB {

    @Value("${url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String name;
    @Value("${spring.datasource.password}")
    private String pwd;


    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> touristAttractions = new ArrayList<>();
        String SQL = "SELECT NAME, DESCRIPTION, CITY FROM attractions\n" +
                "JOIN city USING(city_id);";
        Connection con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
        //Connection con = ConnectionManager.getConnection(db_url, name, pwd);
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String touristAttractionName = rs.getString("NAME");
                String touristAttractionDescription = rs.getString("DESCRIPTION");
                String touristAttractionCity = rs.getString("CITY");

                touristAttractions.add(new TouristAttraction(touristAttractionName, touristAttractionDescription, touristAttractionCity));
            }
            return touristAttractions;

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
        if (touristAttraction != null) {
            String insertQuery = "INSERT INTO attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID) VALUES (?, ?, ?, ?)";
            Connection con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
            try (PreparedStatement pStmnt = con.prepareStatement(insertQuery)) {
                pStmnt.setString(1, touristAttraction.getName());
                pStmnt.setString(2, touristAttraction.getDescription());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

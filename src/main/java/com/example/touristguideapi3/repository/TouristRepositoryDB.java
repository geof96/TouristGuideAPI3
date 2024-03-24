package com.example.touristguideapi3.repository;

import com.example.touristguideapi3.connectionManager.ConnectionManager;
import com.example.touristguideapi3.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.touristguideapi3.connectionManager.ConnectionManager.getConnection;

@Repository
public class TouristRepositoryDB {


    private String db_url = "jdbc:mysql://localhost:3306/tourist_guide";

    private String name = "root";

    private String pwd = "123456";


    public List<TouristAttraction> getAllAttractions() {


        List<TouristAttraction> touristAttractions = new ArrayList<>();
        String SQL = "SELECT NAME, DESCRIPTION, CITY FROM attractions\n" +
                "JOIN city USING(city_id);";
        Connection conn = ConnectionManager.getConnection(db_url, name, pwd);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String touristAttractionName = rs.getString("NAME");
                String touristAttractionDescription = rs.getString("DESCRIPTION");
                String touristAttractionCity = rs.getString("CITY");

                touristAttractions.add(new TouristAttraction(touristAttractionName, touristAttractionDescription, touristAttractionCity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return touristAttractions;
    }

    public void addAttraction(TouristAttraction touristAttraction, List<String> tags) {
        String attractionSQL = "INSERT INTO attractions (NAME, DESCRIPTION, CITY_ID) VALUES (?, ?, (SELECT CITY_ID FROM CITY WHERE CITY = ?))";
        String tagSQL = "INSERT INTO tags (TAG) SELECT * FROM (SELECT ?) AS tmp WHERE NOT EXISTS (SELECT TAG FROM tags WHERE TAG = ?) LIMIT 1";
        String attractionTagSQL = "INSERT INTO attraction_tags (ATTRACTION_ID, TAG_ID) VALUES (?, (SELECT TAG_ID FROM tags WHERE TAG = ?))";
        Connection con = null;
        try {
            con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
            con.setAutoCommit(false); // Start transaction

            // Insert the attraction
            try (PreparedStatement attractionStmt = con.prepareStatement(attractionSQL, Statement.RETURN_GENERATED_KEYS)) {
                attractionStmt.setString(1, touristAttraction.getName());
                attractionStmt.setString(2, touristAttraction.getDescription());
                attractionStmt.setString(3, touristAttraction.getCity());
                attractionStmt.executeUpdate();
                ResultSet rs = attractionStmt.getGeneratedKeys();
                if (rs.next()) {
                    long attractionId = rs.getLong(1);

                    // Insert tags and link them to the attraction
                    try (PreparedStatement tagStmt = con.prepareStatement(tagSQL);
                         PreparedStatement attractionTagStmt = con.prepareStatement(attractionTagSQL)) {
                        for (String tag : tags) {
                            // Ensure the tag exists
                            tagStmt.setString(1, tag);
                            tagStmt.setString(2, tag);
                            tagStmt.executeUpdate();

                            // Link attraction to tag
                            attractionTagStmt.setLong(1, attractionId);
                            attractionTagStmt.setString(2, tag);
                            attractionTagStmt.executeUpdate();
                        }
                    }
                }
                con.commit(); // Commit transaction
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Reset auto-commit to true
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean updateAttraction(TouristAttraction attraction) {
        String sql = "UPDATE attractions SET DESCRIPTION = ?, CITY_ID = (SELECT CITY_ID FROM CITY WHERE CITY = ?) WHERE NAME = ?";
        try (Connection con = getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, attraction.getDescription());
            ps.setString(2, attraction.getCity());
            ps.setString(3, attraction.getName());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAttraction(String name) {
        String sql = "DELETE FROM attractions WHERE NAME = ?";
        try (Connection con = getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getTagsForAttraction(String attractionName) {
        String sql = "SELECT t.TAG FROM tags t JOIN attraction_tags at ON t.TAG_ID = at.TAG_ID JOIN attractions a ON at.ATTRACTION_ID = a.ID WHERE a.NAME = ?";
        List<String> tags = new ArrayList<>();
        try (Connection con = getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, attractionName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tags.add(rs.getString("TAG"));
                }
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllTags() {
        String sql = "SELECT TAG FROM tags";
        List<String> tags = new ArrayList<>();
        try (Connection con = getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tags.add(rs.getString("TAG"));
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllCities() {
        String sql = "SELECT CITY FROM city";
        List<String> cities = new ArrayList<>();
        try (Connection con = getConnection("jdbc:mysql://localhost:3306/tourist_guide", "root", "123456");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cities.add(rs.getString("TAG"));
            }
            return cities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}

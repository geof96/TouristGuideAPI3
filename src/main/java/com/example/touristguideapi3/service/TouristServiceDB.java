package com.example.touristguideapi3.service;

import com.example.touristguideapi3.model.TouristAttraction;
import com.example.touristguideapi3.repository.TouristRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristServiceDB {

    private TouristRepositoryDB touristRepositoryDB = new TouristRepositoryDB();

    public List<TouristAttraction> getAllTouristAttractions() {
        return touristRepositoryDB.getAllAttractions();
    }

    public void addTouristAttraction(TouristAttraction touristAttraction, List<String> tags) {
        touristRepositoryDB.addAttraction(touristAttraction, tags);
    }

    public boolean updateTouristAttraction(TouristAttraction attraction) {
        return touristRepositoryDB.updateAttraction(attraction);
    }

    public boolean deleteTouristAttraction(String name) {
        return touristRepositoryDB.deleteAttraction(name);
    }

    public List<String> getTagsForTouristAttraction(String attractionName) {
        return touristRepositoryDB.getTagsForAttraction(attractionName);
    }

    public List<String> getAllTags() {
        return touristRepositoryDB.getAllTags();
    }

    public List<String> getAllCities() {
        return touristRepositoryDB.getAllCities();
    }
}

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
}

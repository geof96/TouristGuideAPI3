package com.example.touristguideapi3.service;

import com.example.touristguideapi3.model.TouristAttraction;
import com.example.touristguideapi3.repository.TouristRepository;
import com.example.touristguideapi3.repository.TouristRepositoryDB;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristService {

    private TouristRepository touristRepository = new TouristRepository();


    public List<TouristAttraction> getAllTouristAttractions() {
        return touristRepository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttractionById(String name) {
        return touristRepository.getTouristAttractionById(name);
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        return touristRepository.addTouristAttraction(touristAttraction);
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttraction) {
        return touristRepository.updateTouristAttraction(touristAttraction);
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        return touristRepository.deleteTouristAttraction(name);
    }

    public List<String> getTagList() {
        return touristRepository.getTags();
    }

    public List<String> getTagList2() {
        return touristRepository.singleTags();
    }

    public List<String> getCityList() {
        return touristRepository.getCities();
    }

    public List<String> getAttractionsTags(String name){
        return touristRepository.getAttractionsTags(name);
    }
}

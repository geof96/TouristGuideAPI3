package com.example.touristguideapi3.repository;

import com.example.touristguideapi3.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRepository {

    private List<TouristAttraction> touristAttractions = new ArrayList<>(List.of(
            new TouristAttraction("Mall of America", "City of Somalis", "Minnesota",
                    List.of("Mall", "Museum")),
            new TouristAttraction("Las Vegas Boulevard", "What happens in vegas, stays in Vegas", "Nevada",
                    List.of("Casino", "Museum")),
            new TouristAttraction("Los Angeles", "Home of hollywood", "California",
                    List.of("Beaches", "Parks"))
    ));


    public List<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getTouristAttractionById(String name) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equals(name)) {
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        if (touristAttraction != null) {
            touristAttractions.add(touristAttraction);
        }
        return touristAttraction;
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction updatedTouristAttraction) {
        int i = 0;
        TouristAttraction attraction = touristAttractions.get(i);
        for (i = 0; i < touristAttractions.size(); i++) {
            if (attraction.getName().equals(updatedTouristAttraction.getName())) {
                attraction.setName(updatedTouristAttraction.getName());
                attraction.setDescription(updatedTouristAttraction.getDescription());
                attraction.setCity(updatedTouristAttraction.getCity());
                attraction.setTagList(updatedTouristAttraction.getTagList());

            }
        }
        return attraction;
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        TouristAttraction deleteAttraction = null;
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                deleteAttraction = touristAttraction;
                break;
            }
        }
        touristAttractions.remove(deleteAttraction);
        return deleteAttraction;
    }

    public List<String> getTags() {
        List<String> allTags = new ArrayList<>(List.of("Museum", "Park", "Beach", "Cinema", "Paddel", "Stadium"));
        return allTags;
    }

    public List<String> getCities() {
        List<String> cityList = new ArrayList<>();
        for (TouristAttraction touristAttraction : touristAttractions) {
            cityList.add(touristAttraction.getCity());
        }
        return cityList;
    }


}

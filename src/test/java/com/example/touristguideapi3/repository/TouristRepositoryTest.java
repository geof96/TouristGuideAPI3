package com.example.touristguideapi3.repository;

import com.example.touristguideapi3.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TouristRepositoryTest {


    TouristRepository touristRepository = new TouristRepository();
    TouristAttraction testTouristAttraction = new TouristAttraction("Anas", "Faisal", "Said", List.of("Museum", "Park"));

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllTouristAttractions() {
        //Arrange
        List<TouristAttraction> actualList = touristRepository.getAllTouristAttractions();

        //Act
        int expectedSize = 3;

        //Assert
        assertEquals(expectedSize, actualList.size());
    }

    @Test
    void getTouristAttractionById() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void addTouristAttraction() {
        //Arrange
        touristRepository.addTouristAttraction(testTouristAttraction);

        //Act
        List<TouristAttraction> actualList = touristRepository.getAllTouristAttractions();
        //Assert
        int expected = 4;

        assertEquals(expected, actualList.size());
    }

    @Test
    void updateTouristAttraction() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void deleteTouristAttraction() {
        //Arrange

        //Act

        //Assert
    }
}
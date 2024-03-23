package com.example.touristguideapi3.model;


import java.util.List;

public class TouristAttraction {

    private String name;
    private String description;
    private String city;
    private List<String> tagList;


    public TouristAttraction(String name, String description, String city, List<String> tagList) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tagList = tagList;
    }

    public TouristAttraction(String name, String description, String city) {
        this.name = name;
        this.description = description;
        this.city = city;
    }

    public TouristAttraction() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

}

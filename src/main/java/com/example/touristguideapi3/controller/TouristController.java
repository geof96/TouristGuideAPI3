package com.example.touristguideapi3.controller;

import com.example.touristguideapi3.model.TouristAttraction;
import com.example.touristguideapi3.service.TouristService;
import com.example.touristguideapi3.service.TouristServiceDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("attractions")
public class TouristController {

    //From Stub-methods
    private TouristService touristService;
    //From Database
    private TouristServiceDB touristServiceDB;


    public TouristController(TouristService touristService, TouristServiceDB touristServiceDB) {
        this.touristService = touristService;
        this.touristServiceDB = touristServiceDB;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        model.addAttribute("attractions", touristServiceDB.getAllTouristAttractions());
        return "attractionList";
    }

    @GetMapping("searchAttraction/{name}")
    public String getAttractionById(@PathVariable String name, Model model) {
        model.addAttribute("id", touristService.getTouristAttractionById(name));
        return "id";
    }


    @GetMapping("/add")
    public String addAttraction(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("tags", touristService.getTagList());
        model.addAttribute("city", touristService.getCityList());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String addedAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        model.addAttribute("add", touristService.addTouristAttraction(touristAttraction));
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        model.addAttribute("attraction", touristService.getTouristAttractionById(name));
        model.addAttribute("city", touristService.getCityList());
        model.addAttribute("tags", touristService.getTagList2());


        return "updateAttraction";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        model.addAttribute("editedAttraction", touristService.updateTouristAttraction(touristAttraction));
        return "redirect:/attractions";
    }

    @GetMapping("{name}/deleteAttraction")
    public String deleteAttraction(@PathVariable String name, Model model) {
        if (touristService.getAllTouristAttractions() != null) {
            model.addAttribute("delete", touristService.deleteTouristAttraction(name));
            return "redirect:/attractions";
        }
        return "redirect/attractions";
    }

    @GetMapping("/{name}/tags")
    public String getTags(@PathVariable String name, Model model) {
        model.addAttribute("tags", touristService.getAttractionsTags(name));
        model.addAttribute("attractions", touristService.getAllTouristAttractions());
        model.addAttribute("name", name);
        return "tags";
    }
}

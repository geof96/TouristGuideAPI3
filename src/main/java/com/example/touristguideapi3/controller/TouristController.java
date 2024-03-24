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


    @GetMapping("/add")
    public String addAttraction(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("tags", touristServiceDB.getAllTags());
        model.addAttribute("city", touristServiceDB.getAllCities());
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
        model.addAttribute("city", touristServiceDB.getAllCities());
        model.addAttribute("tags", touristServiceDB.getAllTags());


        return "updateAttraction";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        model.addAttribute("editedAttraction", touristServiceDB.updateTouristAttraction(touristAttraction));
        return "redirect:/attractions";
    }

    @GetMapping("{name}/deleteAttraction")
    public String deleteAttraction(@PathVariable String name, Model model) {
        if (touristService.getAllTouristAttractions() != null) {
            model.addAttribute("delete", touristServiceDB.deleteTouristAttraction(name));
            return "redirect:/attractions";
        }
        return "redirect/attractions";
    }

    @GetMapping("/{name}/tags")
    public String getTags(@PathVariable String name, Model model) {
        model.addAttribute("tags", touristServiceDB.getTagsForTouristAttraction(name));
        model.addAttribute("attractions", touristServiceDB.getAllTouristAttractions());
        model.addAttribute("name", name);
        return "tags";
    }
}

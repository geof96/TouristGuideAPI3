package com.example.touristguideapi3.controller;

import com.example.touristguideapi3.model.TouristAttraction;
import com.example.touristguideapi3.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("attractions")
public class TouristController {

    private TouristService touristService;


    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAllTouristAttractions());
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

    @GetMapping
    public String editAttraction(Model model){
        model.addAttribute("attraction", new TouristAttraction());
        return "editHtml";
    }

    @PostMapping("updateAttraction")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        model.addAttribute(touristService.updateTouristAttraction(touristAttraction));
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
    public String getTags(Model model) {
        model.addAttribute("tags", touristService.getTagList());
        return "tags";
    }
}

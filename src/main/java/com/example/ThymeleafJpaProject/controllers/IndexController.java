package com.example.ThymeleafJpaProject.controllers;

import com.example.ThymeleafJpaProject.models.RadioStation;
import com.example.ThymeleafJpaProject.repositories.RadioStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @Autowired
    private RadioStationRepo repo;

//    @GetMapping("/")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("listStation", repo.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("station", new RadioStation());
        return "createForm";
    }

    @PostMapping("/save")
    public String saveStations(@ModelAttribute("radioStation") RadioStation radioStation) {
        repo.save(radioStation);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("editForm");
        RadioStation radioStation = repo.findById(id).get();
        mav.addObject("editStation", radioStation);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}

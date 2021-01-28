package com.example.ThymeleafJpaProject.controllers;

import com.example.ThymeleafJpaProject.models.RadioStation;
import com.example.ThymeleafJpaProject.repositories.RadioStationRepo;

import org.apache.commons.io.FileUtils;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;


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

    @RequestMapping(value="/generate", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF() {
        List<RadioStation> radioStations = repo.findAll();

        File exportFile = new File("welcome.docx");
        byte[] contents;
        try {

            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            mainDocumentPart.addStyledParagraphOfText("Title", "Document №");
            for(var item : radioStations){
                mainDocumentPart.addParagraphOfText(item.getRadiostation_name() + " - " +
                        item.getCount());
            }

            wordPackage.save(exportFile);

            contents = FileUtils.readFileToByteArray(new File("C:\\Users\\Дмитрий\\Desktop\\projectSpringBootApp\\welcome.docx"));
            HttpHeaders headers = new HttpHeaders();

            // Here you have to set the actual filename of your pdf
            String filename = "output.docx";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
            return response;
        }catch (Exception e){
            return new ResponseEntity("Err", HttpStatus.NOT_ACCEPTABLE);
        }
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

package com.birichani.code.pharmacysystem.web.dto;

import com.birichani.code.pharmacysystem.model.Pharmacist;
import com.birichani.code.pharmacysystem.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PharmacistController {
    @Autowired
    private PharmacistService pharmacistService;

    // display list of employees
    @GetMapping("/pharmacist")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewPharmacistForm")
    public String showNewPharmacistForm(Model model) {
        // create model attribute to bind form data
        Pharmacist pharmacist = new Pharmacist();
        model.addAttribute("pharmacist", pharmacist);
        return "new_pharmacist";
    }

    @PostMapping("/savePharmacist")
    public String savePharmacist(@ModelAttribute("pharmacist") Pharmacist pharmacist) {
        // save pharmacist to database
        pharmacistService.savePharmacist(pharmacist);
        return "redirect:/pharmacist";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get pharmacist from the service
        Pharmacist pharmacist = pharmacistService.getPharmacistById(id);

        // set pharmacist as a model attribute to pre-populate the form
        model.addAttribute("pharmacist", pharmacist);
        return "update_pharmacist";
    }

    @GetMapping("/deletePharmacist/{id}")
    public String deletePharmacist(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.pharmacistService.deletePharmacistById(id);
        return "redirect:/pharmacist";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Pharmacist> page = pharmacistService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Pharmacist> listPharmacists = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPharmacists", listPharmacists);
        return "index_pharmacist";
    }
}

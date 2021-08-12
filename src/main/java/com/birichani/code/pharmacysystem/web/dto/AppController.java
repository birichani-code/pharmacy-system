package com.birichani.code.pharmacysystem.web.dto;

import java.util.List;

import com.birichani.code.pharmacysystem.model.Medicine;
import com.birichani.code.pharmacysystem.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private MedicineService service;
	
	@RequestMapping("/medicine")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Medicine> listMedicines = service.listAll(keyword);
		model.addAttribute("listMedicines", listMedicines);
		model.addAttribute("keyword", keyword);
		
		return "index-medicine";
	}
	
	@RequestMapping("/new")
	public String showNewMedicineForm(Model model) {
		Medicine medicine = new Medicine();
		model.addAttribute("medicine", medicine);
		
		return "new_medicine";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
		service.save(medicine);
		
		return "redirect:/medicine";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditMedicineForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_medicine");
		
		Medicine medicine = service.get(id);
		mav.addObject("medicine", medicine);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteMedicine(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/medicine";
	}
}

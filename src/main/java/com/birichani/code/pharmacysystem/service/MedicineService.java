package com.birichani.code.pharmacysystem.service;

import java.util.List;

import com.birichani.code.pharmacysystem.model.Medicine;
import com.birichani.code.pharmacysystem.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
	@Autowired
	private MedicineRepository repo;
	
	public List<Medicine> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}
	
	public void save(Medicine product) {
		repo.save(product);
	}
	
	public Medicine get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}

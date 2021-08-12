package com.birichani.code.pharmacysystem.repository;

import java.util.List;

import com.birichani.code.pharmacysystem.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ " OR p.brand LIKE %?1%"
//			+ " OR p.madein LIKE %?1%"
//			+ " OR CONCAT(p.price, '') LIKE %?1%")
	@Query("SELECT p FROM Medicine p WHERE CONCAT(p.name, ' ', p.category, ' ', p.company, ' ', p.price) LIKE %?1%")
	public List<Medicine> search(String keyword);
}

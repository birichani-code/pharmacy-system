package com.birichani.code.pharmacysystem.repository;

import com.birichani.code.pharmacysystem.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist,Long> {
}

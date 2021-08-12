package com.birichani.code.pharmacysystem.service;

import com.birichani.code.pharmacysystem.model.Pharmacist;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacistService {
    List<Pharmacist> getAllPharmacist();
    void savePharmacist(Pharmacist pharmacist);
    Pharmacist getPharmacistById(long id);
    void deletePharmacistById(long id);
    Page<Pharmacist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

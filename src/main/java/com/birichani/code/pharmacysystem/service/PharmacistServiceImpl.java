package com.birichani.code.pharmacysystem.service;

import com.birichani.code.pharmacysystem.model.Pharmacist;
import com.birichani.code.pharmacysystem.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacistServiceImpl implements PharmacistService {
    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public List<Pharmacist> getAllPharmacist() {
        return pharmacistRepository.findAll();
    }

    @Override
    public void savePharmacist(Pharmacist pharmacist) {
        this.pharmacistRepository.save(pharmacist);
    }

    @Override
    public Pharmacist getPharmacistById(long id) {
        Optional<Pharmacist> optional = pharmacistRepository.findById(id);
        Pharmacist pharmacist = null;
        if (optional.isPresent()) {
            pharmacist = optional.get();
        } else {
            throw new RuntimeException(" Pharmacist not found for id :: " + id);
        }
        return pharmacist;
    }

    @Override
    public void deletePharmacistById(long id) {
        this.pharmacistRepository.deleteById(id);
    }

    @Override
    public Page<Pharmacist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.pharmacistRepository.findAll(pageable);
    }
}

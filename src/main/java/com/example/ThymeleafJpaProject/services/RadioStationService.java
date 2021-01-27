package com.example.ThymeleafJpaProject.services;

import com.example.ThymeleafJpaProject.models.RadioStation;
import com.example.ThymeleafJpaProject.repositories.RadioStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioStationService {

    @Autowired
    private RadioStationRepo repo;

    public List<RadioStation> listAll() {
        return repo.findAll();
    }

    public void save(RadioStation radioStation) {
        repo.save(radioStation);
    }

    public RadioStation get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

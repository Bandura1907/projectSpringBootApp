package com.example.ThymeleafJpaProject.repositories;

import com.example.ThymeleafJpaProject.models.RadioStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadioStationRepo extends JpaRepository<RadioStation, Integer> {
}

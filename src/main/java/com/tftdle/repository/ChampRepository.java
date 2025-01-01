package com.tftdle.repository;

import com.tftdle.model.ChampModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampRepository extends JpaRepository<ChampModel, Long> {
}

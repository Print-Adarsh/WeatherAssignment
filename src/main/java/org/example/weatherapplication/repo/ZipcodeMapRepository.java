package org.example.weatherapplication.repo;

import org.example.weatherapplication.entities.ZipcodeMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipcodeMapRepository extends JpaRepository<ZipcodeMap, String> {}
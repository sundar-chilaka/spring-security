package com.sundar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sundar.entity.Weather;
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long>{

	Optional<Weather> findByCity(String city);

	List<Weather> findByStatus(String status);

//	void save(pojoRequst req);

	
}

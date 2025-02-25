package com.sundar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sundar.entity.Weather;
import com.sundar.repository.WeatherRepository;

@Service
public class weatherScheduling {

	@Autowired
	WeatherRepository weatherRepository;
	
	@Scheduled(fixedRate = 5000)
	public void processPendingWeather() {
		List<Weather> weathers = weatherRepository.findByStatus("PENDING");
		weathers.forEach(weather ->{
			weather.setStatus("COMPLETED");
		});
		System.out.println("processed {} pending orders."+weathers.size());
	}
}

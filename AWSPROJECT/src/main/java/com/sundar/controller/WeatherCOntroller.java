package com.sundar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.entity.Weather;
import com.sundar.repository.WeatherRepository;
import com.sundar.service.CacheInspectionService;
import com.sundar.service.WeatherService;

@RestController
@RequestMapping("/api")
public class WeatherCOntroller {

	
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	CacheInspectionService cacheInspectionService;
	
	@GetMapping("/getweatherByCity")
	public String getWeather(@RequestParam String city) {
		String weatherCity=weatherService.getWeatherByCity(city);
		return weatherCity;
	}
	@PostMapping
	public Weather createWeather(@RequestBody Weather weather) {
		return weatherRepository.save(weather);
	}
	@GetMapping
	public List<Weather> getAllWeather() {
		return weatherRepository.findAll();
	}
	
	@GetMapping("/cacheData")
	public void cacheData() {
		cacheInspectionService.printCacheContext("weather");;
	}
	
	@PutMapping("/{city}")
	public String updateWeather(@PathVariable String city,@PathVariable String update) {
		return weatherService.updateWeather(city, update);
	}
}

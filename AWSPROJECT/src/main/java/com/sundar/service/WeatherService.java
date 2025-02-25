package com.sundar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sundar.entity.Weather;
import com.sundar.entity.pojoRequst;
import com.sundar.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	@Cacheable("weather")
	public String getWeatherByCity(String city) {
		System.out.println("Fetching data form  DB  for city: " + city);
		Optional<Weather> weather = weatherRepository.findByCity(city);
		return weather.map(Weather::getForecast).orElse("Weather data is not available...!");
	}

	@CachePut(value = "weather",key ="#city")
	public String updateWeather(String city, String updateedWeather) {
		weatherRepository.findByCity(city).ifPresent(weather -> {
			weather.setForecast(updateedWeather);
			weatherRepository.save(weather);
		});
		return updateedWeather;
	}
	@CacheEvict(value = "weather",key ="#city")
	public void deleteWeather(String city) {
		weatherRepository.findByCity(city).ifPresent(weather ->{
			weather.getCity();
			weatherRepository.delete(weather);
		});
	}
	
	public Weather cretaeWeather(pojoRequst req) {
		Weather weather = new Weather();
		weather.setCity(req.getCity());
		
		return weatherRepository.save(weather);
	}

}

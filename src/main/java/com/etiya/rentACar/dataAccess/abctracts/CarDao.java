package com.etiya.rentACar.dataAccess.abctracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer>{
	List<Car> getByModelYear(double  modelYear);//getBy yada findBy ile başlayabilir JpaRepository de.
	List<Car> getByModelYearIn(List<Double> modelYears);
	List<Car> getByModelYearAndDailyPrice(double modelYear, double dailyPrice);
	List<Car> getByDescriptionContains(String description);
	
	List<Car> getByCityId(int cityId);
}
package com.etiya.rentACar.business.concretes;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.RentalDao;
import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.Rental;

@Service
public class RentalManager implements RentalService{
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private AdditionalServiceService additionalServiceService;
	
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService,  AdditionalServiceService additionalServiceService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	public DataResult<RentalDto> getById(int rentalId) {
		Rental rental =this.rentalDao.getById(rentalId);
		RentalDto response = this.modelMapperService.forDto().map(rental, RentalDto.class);
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		List<Rental> rentals = this.rentalDao.findAll();
		List<ListRentalDto> response = rentals.stream()
				.map(rental -> this.modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		int carId=createRentalRequest.getCarId();
		
		carService.checkIfCarStates(carId);
		
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		this.rentalDao.save(rental);
		
		carService.updateCarState(carId, CarStates.Rented);
		
		return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_ADDED);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		this.rentalDao.deleteById(deleteRentalRequest.getId());
		return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_DELETED);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_UPDATED);
	}
	
	//Hesaplama kodları payment'da olacak
	private int calculateDay(CreateRentalRequest createRentalRequest) {
		Period diff = Period.between(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate());
		if (diff.getDays() ==0) {
			return 1;
		}
		return diff.getDays();
	}
	
	private double calculateTotalPrice(CreateRentalRequest createRentalRequest, List<Integer> additionalServiceId) {
		CarDto car = this.carService.getById(createRentalRequest.getCarId()).getData();
		double dailyPrice = car.getDailyPrice();
		int dayCount = calculateDay(createRentalRequest);
		double dayPrice = dayCount * dailyPrice;
		double citySame = this.checkIfCitiesSame(createRentalRequest);
		double additionalServicesTotalPrice = dayCount * this.checkIfAdditionalServices(additionalServiceId);
		
		return (dayPrice + citySame + additionalServicesTotalPrice);
	}
	
	private double checkIfCitiesSame(CreateRentalRequest createRentalRequest) {
		if(createRentalRequest.getRentCityId() != createRentalRequest.getReturnedCityId()) {
			 return 750;
		}
		return 0;
	}
	
	private double checkIfAdditionalServices(List<Integer> listAdditionalServiceId) {
		double additionalServicesTotalPrice = 0;
		
		for (int i = 0; i < listAdditionalServiceId.size(); i++) {
			additionalServicesTotalPrice += this.additionalServiceService.getById(listAdditionalServiceId.get(i)).getData().getDailyPrice();
		}

		return additionalServicesTotalPrice;
	}
}

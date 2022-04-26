package com.etiya.rentACar.business.concretes;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.entities.Car;
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
	
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
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

		CarDto car = carService.getById(createRentalRequest.getCarId().getId()).getData();
		//carService.checkIfCarStates(car.getId());

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setDailyPrice(car.getDailyPrice());//indirim gelirse burada indirimle çarp
		this.rentalDao.save(rental);
		
		//carService.updateCarState(car.getId(), CarStates.Rented);
		
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

}

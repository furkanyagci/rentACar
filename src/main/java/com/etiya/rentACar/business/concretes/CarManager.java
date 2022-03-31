package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.CarDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarStates;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;
		
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<CarDto> getById(int id) {
		Car car =this.carDao.getById(id);
		CarDto response = this.modelMapperService.forDto().map(car, CarDto.class);
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		List<Car> cars= this.carDao.findAll();
		List<ListCarDto> response = cars.stream().map(car ->this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList()); 
		return new SuccessDataResult<>(response, BusinessMessages.CarMessages.CAR_LISTED);
	}
	
	@Override
	public DataResult<List<ListCarDto>> getByCityId(int cityId) {
		List<Car> cars = this.carDao.getByCityId(cityId);
		List<ListCarDto> response = cars.stream().map(car ->this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList()); 
		return new SuccessDataResult<List<ListCarDto>>(response);
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAllByModelYear(double modelYear) {
		List<Car> cars= this.carDao.getByModelYear(modelYear);
		List<ListCarDto> response = cars.stream().map(car ->this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList()); 
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		List<Car> cars = this.carDao.findAll(pageable).getContent();
		
		List<ListCarDto> response = cars.stream().map(car ->this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList()); 
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
		
		List<Car> cars = this.carDao.findAll(sort);
		
		List<ListCarDto> response = cars.stream().map(car ->this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList()); 
		return new SuccessDataResult<>(response);
	}
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		 /* Color color = new Color(); color.setId(createCarRequest.getColorId());
		 * 
		 * Brand brand = new Brand(); brand.setId(createCarRequest.getBrandId());
		 * 
		 //buradaki işlemleri kolaylaştırmak için modelmapper kullanacağız 
		 * Car car = new Car(); car.setModelYear(createCarRequest.getModelYear());
		 * car.setDailyPrice(createCarRequest.getDailyPrice());
		 * car.setDescription(createCarRequest.getDescription()); car.setColor(color);
		  car.setBrand(brand);*/
		 
		//aşağıdaki işlem modelmapper işlemi yukarıdaki gibi alanları tek tek atamamıza gerek kalmadı
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(BusinessMessages.CarMessages.CAR_ADDED);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		this.carDao.deleteById(deleteCarRequest.getId());
		return new SuccessResult(BusinessMessages.CarMessages.CAR_DELETED);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarIdExists(updateCarRequest.getId());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(BusinessMessages.CarMessages.CAR_UPDATED);
	}
	
	private void checkIfCarIdExists(int carId) {
		if(this.carDao.getById(carId)==null) {
			throw new BusinessException("Bu isimde bir marka yok");
		}
	}

	@Override
	public void updateCarState(int carId, CarStates carStates) {
		Car car=this.carDao.getById(carId);
		UpdateCarRequest updateCarRequest2=this.modelMapperService.forRequest().map(car, UpdateCarRequest.class);
		updateCarRequest2.setCarStates(carStates);

		car = this.modelMapperService.forRequest().map(updateCarRequest2, Car.class);
		this.carDao.save(car);
	}
	
	@Override
	public void checkIfCarStates(int carId) {
		DataResult<CarDto> car= this.getById(carId);
		if (car.getData().getCarStates()==CarStates.UnderMaintanance) {
			throw new BusinessException(BusinessMessages.CarMessages.CAR_UNDERMAINTANANCE);
		}else if(car.getData().getCarStates()==CarStates.Rented){
			throw new BusinessException(BusinessMessages.CarMessages.CAR_RENTED);
		}
	}

	
}
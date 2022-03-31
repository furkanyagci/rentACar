package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintananceService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintananceRequest;
import com.etiya.rentACar.business.responses.maintananceResponses.ListMaintananceDto;
import com.etiya.rentACar.business.responses.maintananceResponses.MaintananceDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.MaintananceDao;
import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.Maintanance;

@Service
public class MaintananceManager implements MaintananceService {

	private MaintananceDao maintananceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	public MaintananceManager(MaintananceDao maintananceDao, ModelMapperService modelMapperService, CarService carService) {
		this.maintananceDao = maintananceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}
	
	@Override
	public DataResult<MaintananceDto> getById(int id) {
		Maintanance maintanance =this.maintananceDao.getById(id);
		MaintananceDto response = this.modelMapperService.forDto().map(maintanance, MaintananceDto.class);
		return new SuccessDataResult<MaintananceDto>(response);
	}

	@Override
	public DataResult<List<ListMaintananceDto>> getAll() {
		List<Maintanance> maintanances = this.maintananceDao.findAll();
		List<ListMaintananceDto> response = maintanances.stream()
				.map(maintanance -> this.modelMapperService.forDto().map(maintanance, ListMaintananceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListMaintananceDto>>(response);
	}

	@Override
	public DataResult<List<ListMaintananceDto>> getByCarId(int carId) {
		List<Maintanance> maintanances = maintananceDao.getByCarId(carId);
		List<ListMaintananceDto> response = maintanances.stream()
				.map(maintanance -> this.modelMapperService.forDto().map(maintanance, ListMaintananceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListMaintananceDto>>(response);
	}
	
	@Override
	public Result add(CreateMaintananceRequest createMaintananceRequest) {
		int carId= createMaintananceRequest.getCarId();
		this.carService.checkIfCarStates(carId);
		Maintanance maintanance = this.modelMapperService.forRequest().map(createMaintananceRequest, Maintanance.class);
		this.maintananceDao.save(maintanance);
		this.carService.updateCarState(carId, CarStates.UnderMaintanance);
		return new SuccessResult(BusinessMessages.MaintananceMessages.MAINTANANCE_ADDED);
	}
	
	@Override
	public Result update(UpdateMaintananceRequest updateMaintananceRequest) {
		Maintanance maintanance = this.modelMapperService.forRequest().map(updateMaintananceRequest, Maintanance.class);
		this.maintananceDao.save(maintanance);
		return new SuccessResult(BusinessMessages.MaintananceMessages.MAINTANANCE_UPDATED);
	}

	@Override
	public Result delete(DeleteMaintananceRequest deleteMaintananceRequest) {
		this.maintananceDao.deleteById(deleteMaintananceRequest.getId());
		return new SuccessResult(BusinessMessages.MaintananceMessages.MAINTANANCE_DELETED);
	}
}

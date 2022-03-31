package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.responses.additionalServiceResponses.AdditionalServiceDto;
import com.etiya.rentACar.business.responses.additionalServiceResponses.ListAdditionalServiceDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.AdditionalServiceDao;
import com.etiya.rentACar.entities.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
		
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<AdditionalServiceDto> getById(int cityId) {
		AdditionalService additionalService = this.additionalServiceDao.getById(cityId);
		AdditionalServiceDto response = this.modelMapperService.forDto()
				.map(additionalService, AdditionalServiceDto.class);
		return new SuccessDataResult<AdditionalServiceDto>(response);
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		List<AdditionalService> additionalServices = this.additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = additionalServices.stream()
				.map(additionalService -> this.modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		checkIfAdditionalServiceNameExists(createAdditionalServiceRequest.getName());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(BusinessMessages.AdditionalServiceMessages.ADDITIONALSERVICE_ADDED);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getId());
		return new SuccessResult(BusinessMessages.AdditionalServiceMessages.ADDITIONALSERVICE_DELETED);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(BusinessMessages.AdditionalServiceMessages.ADDITIONALSERVICE_UPDATED);
	}
	
	private void checkIfAdditionalServiceNameExists(String AdditionalServiceName) {
		if (additionalServiceDao.existsByNameIgnoreCase(AdditionalServiceName)) {
			throw new BusinessException(BusinessMessages.AdditionalServiceMessages.ADDITIONALSERVICE_REGISTERED_NAME);
		}
	}
}

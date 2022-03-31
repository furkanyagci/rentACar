package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface DamageService {
	DataResult<DamageDto> getById(int id);
	
	DataResult<List<ListDamageDto>> getAll();
	DataResult<List<ListDamageDto>> getByCarId(int id);
	DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<ListDamageDto>> getAllSorted(String sorted, String field);
	
	Result add(CreateDamageRequest createDamageRequest);
	Result update(UpdateDamageRequest updateDamageRequest);
	Result delete(DeleteDamageRequest deleteDamageRequest);
}

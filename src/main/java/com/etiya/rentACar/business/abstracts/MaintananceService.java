package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintananceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintananceRequest;
import com.etiya.rentACar.business.responses.maintananceResponses.ListMaintananceDto;
import com.etiya.rentACar.business.responses.maintananceResponses.MaintananceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface MaintananceService {
	DataResult<MaintananceDto> getById(int id);
	
	DataResult<List<ListMaintananceDto>> getAll();
	DataResult<List<ListMaintananceDto>> getByCarId(int carId);

	Result add(CreateMaintananceRequest createMaintananceRequest);
	Result update(UpdateMaintananceRequest updateMaintananceRequest);
	Result delete(DeleteMaintananceRequest deleteMaintananceRequest);
}

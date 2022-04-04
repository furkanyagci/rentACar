package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.CreateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.DeleteRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.UpdateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses.ListRentalAdditionalServiceDetailDto;
import com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses.RentalAdditionalServiceDetailDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface RentalAdditionalServiceDetailService {
    DataResult<RentalAdditionalServiceDetailDto> getById(int rentalAdditionalServiceDetailId);

    DataResult<List<ListRentalAdditionalServiceDetailDto>> getAll();
    DataResult<List<ListRentalAdditionalServiceDetailDto>> getByRentalId(int rentalId);
    DataResult<List<ListRentalAdditionalServiceDetailDto>> getByAdditionalServiceId(int additionalServiceId);

    Result add(CreateRentalAdditionalServiceDetailRequest createRentalAdditionalServiceDetailRequest);
    Result delete(DeleteRentalAdditionalServiceDetailRequest deleteListRentalAdditionalServiceDetailRequest);
    Result update(UpdateRentalAdditionalServiceDetailRequest updateListRentalAdditionalServiceDetailRequest);
}

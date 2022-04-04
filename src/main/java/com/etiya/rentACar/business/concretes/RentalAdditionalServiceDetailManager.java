package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.RentalAdditionalServiceDetailService;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.CreateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.DeleteRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.requests.rentalAdditionalServiceDetailRequests.UpdateRentalAdditionalServiceDetailRequest;
import com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses.ListRentalAdditionalServiceDetailDto;
import com.etiya.rentACar.business.responses.rentalAdditionalServiceDetailResponses.RentalAdditionalServiceDetailDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.RentalAdditionalServiceDetailDao;
import com.etiya.rentACar.entities.RentalAdditionalServiceDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalAdditionalServiceDetailManager implements RentalAdditionalServiceDetailService {

    private RentalAdditionalServiceDetailDao rentalAdditionalServiceDetailDao;
    private ModelMapperService modelMapperService;

    public RentalAdditionalServiceDetailManager(RentalAdditionalServiceDetailDao rentalAdditionalServiceDetailDao, ModelMapperService modelMapperService) {
        this.rentalAdditionalServiceDetailDao = rentalAdditionalServiceDetailDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<RentalAdditionalServiceDetailDto> getById(int rentalAdditionalServiceDetailId) {
        RentalAdditionalServiceDetail rentalAdditionalServiceDetail =this.rentalAdditionalServiceDetailDao
                .getById(rentalAdditionalServiceDetailId);
        RentalAdditionalServiceDetailDto response = this.modelMapperService.forDto()
                .map(rentalAdditionalServiceDetail, RentalAdditionalServiceDetailDto.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<ListRentalAdditionalServiceDetailDto>> getAll() {
        List<RentalAdditionalServiceDetail> rentalAdditionalServiceDetails = this.rentalAdditionalServiceDetailDao
                .findAll();
        List<ListRentalAdditionalServiceDetailDto> response = rentalAdditionalServiceDetails.stream()
                .map(rentalAdditionalServiceDetail -> this.modelMapperService.forDto()
                        .map(rentalAdditionalServiceDetails, ListRentalAdditionalServiceDetailDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalAdditionalServiceDetailDto>>(response);
    }

    @Override
    public DataResult<List<ListRentalAdditionalServiceDetailDto>> getByRentalId(int rentalId) {
        List<RentalAdditionalServiceDetail> rentalAdditionalServiceDetails = this.rentalAdditionalServiceDetailDao
                .getByRentalId(rentalId);
        List<ListRentalAdditionalServiceDetailDto> response = rentalAdditionalServiceDetails.stream()
                .map(rentalAdditionalServiceDetail -> this.modelMapperService.forDto()
                        .map(rentalAdditionalServiceDetails, ListRentalAdditionalServiceDetailDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalAdditionalServiceDetailDto>>(response);
    }

    @Override
    public DataResult<List<ListRentalAdditionalServiceDetailDto>> getByAdditionalServiceId(int additionalServiceId) {
        List<RentalAdditionalServiceDetail> rentalAdditionalServiceDetails = this.rentalAdditionalServiceDetailDao
                .getByAdditionalServiceId(additionalServiceId);
        List<ListRentalAdditionalServiceDetailDto> response = rentalAdditionalServiceDetails.stream()
                .map(rentalAdditionalServiceDetail -> this.modelMapperService.forDto()
                        .map(rentalAdditionalServiceDetails, ListRentalAdditionalServiceDetailDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalAdditionalServiceDetailDto>>(response);
    }

    @Override
    public Result add(CreateRentalAdditionalServiceDetailRequest createRentalAdditionalServiceDetailRequest) {
        RentalAdditionalServiceDetail rentalAdditionalServiceDetail = this.modelMapperService.forRequest()
                .map(createRentalAdditionalServiceDetailRequest, RentalAdditionalServiceDetail.class);
        this.rentalAdditionalServiceDetailDao.save(rentalAdditionalServiceDetail);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteRentalAdditionalServiceDetailRequest deleteListRentalAdditionalServiceDetailRequest) {
        this.rentalAdditionalServiceDetailDao.deleteById(deleteListRentalAdditionalServiceDetailRequest.getId());
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateRentalAdditionalServiceDetailRequest updateListRentalAdditionalServiceDetailRequest) {
        RentalAdditionalServiceDetail rentalAdditionalServiceDetail = this.modelMapperService.forRequest()
                .map(updateListRentalAdditionalServiceDetailRequest, RentalAdditionalServiceDetail.class);
        this.rentalAdditionalServiceDetailDao.save(rentalAdditionalServiceDetail);
        return new SuccessResult();
    }
}

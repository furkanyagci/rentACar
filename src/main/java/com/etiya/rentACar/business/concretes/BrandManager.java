package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.BrandDto;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.BrandDao;
import com.etiya.rentACar.entities.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListBrandDto>> getAll() {
		List<Brand> brands = this.brandDao.findAll();
		List<ListBrandDto> response = brands.stream()
				.map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(response);
	}
	/*
	 * stream() = stream api bir datayı bir listeyi dolaşmaya yarıyor. foreach
	 * yapmaya yarıyor. .map maple demek. brand -> her brand'i
	 * this.modelMapperService.forDto() medtodunu kullanarak maple neyi maple?
	 * .map(brand, LisrBrandDto.class) şimdiki brandi ListBrandDto classı oluştur
	 * ona map'le
	 */
	@Override
	public DataResult<BrandDto> getById(int brandId) {
		Brand brand =this.brandDao.getById(brandId);
		BrandDto response = this.modelMapperService.forDto().map(brand, BrandDto.class);
		return new SuccessDataResult<>(response);
	}
	
	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		// Db ye gönderilen nesne Brand olmak zorunda o yüzden bu işlemi yapmalıyız
		// Brand brand = new Brand();
		// brand.setName(createBrandRequest.getName());

		/*
		 * modelmaaper ile daha kolay yapabiliyoruz gelen nesneyi Brand nesnesine
		 * atadık.Yukarıdaki gibi alanları tektek atamamıza gerek kalmadı
		 */
		
		checkIfBrandNameExists(createBrandRequest.getName());

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		 /* map(createBrandRequest, Brand.class); bu kod createBrandRequest nesnesini bir
		 * Brand nesnesi oluştur ona map'le demek*/
		 
		this.brandDao.save(brand);
		return new SuccessResult(BusinessMessages.BrandMessages.BRAND_ADDED);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		this.brandDao.deleteById(deleteBrandRequest.getId());
		return new SuccessResult(BusinessMessages.BrandMessages.BRAND_DELETED);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		//checkIfBrandIdExists(updateBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(BusinessMessages.BrandMessages.BRAND_UPDATED);
	}

	private void checkIfBrandNameExists(String brandName) {
		if (brandDao.existsByNameIgnoreCase(brandName)) {
			throw new BusinessException(BusinessMessages.BrandMessages.BRAND_ALREADY_EXISTS);
		}
	}
	
	/*private void checkIfBrandIdExists(int brandId) {
		if(this.brandDao.getById(brandId)==null) {
			throw new BusinessException("Bu isimde bir marka yok");
		}
	}*/
}

package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.requests.colorRequests.DeleteColorRequest;
import com.etiya.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.etiya.rentACar.business.responses.colorResponses.ColorDto;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abctracts.ColorDao;
import com.etiya.rentACar.entities.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {

		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		List<Color> colors = this.colorDao.findAll();
		List<ListColorDto> response = colors.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response);
	}

	@Override
	public DataResult<ColorDto> getById(int colorId) {
		Color color =this.colorDao.getById(colorId);
		ColorDto response = this.modelMapperService.forDto().map(color, ColorDto.class);
		return new SuccessDataResult<ColorDto>(response);
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorNameExists(createColorRequest.getName());
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(BusinessMessages.ColorMessages.COLOR_ADDED);
	}
	
	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		this.colorDao.deleteById(deleteColorRequest.getId());
		return new SuccessResult(BusinessMessages.ColorMessages.COLOR_DELETED);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(BusinessMessages.ColorMessages.COLOR_UPDATED);
	}

	
	private void checkIfColorNameExists(String colorName) {
		if (colorDao.existsByNameIgnoreCase(colorName)) {
			throw new BusinessException(BusinessMessages.ColorMessages.COLOR_IS_ALREADY_SAVED);
		}
	}
}

package com.etiya.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service//Hata bunu yazmadığım için olmuş
public class ModelMapperManager implements ModelMapperService{
	
	private ModelMapper modelMapper;//ModelMapper bir class

	public ModelMapperManager(ModelMapper modelMapper) {//Buradaki referans alma işlemi için "@Bean hakkında.text" notunu oku
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
		/*setAmbiguityIgnored(true) aynı veriler varsa ignore et id'ler gibi*/
	}

	@Override
	public ModelMapper forDto() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}
	
	
	
}

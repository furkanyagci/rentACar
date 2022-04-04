package com.etiya.rentACar.business.responses.carResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {
	
	private int id;
	
	private double dailyPrice;

	private String description;
	
	private double modelYear;

	private double kilometer;
	
	private String colorName;/*Burada isimlendirme çok önemli modelmapper'ın anlaması için. Car da colorName fieldı yok.
	Car da color var Color classında da name alanı var bu ikisini birleştirerek isim veriyoruz modelmappin Car'a bakıyor color
	var color classına bakıyor name var bunu istiyor diyip bunu bu alana atıyor. */
	
	private String brandName;
	
	private String carStates;
	
	private String cityName;
}

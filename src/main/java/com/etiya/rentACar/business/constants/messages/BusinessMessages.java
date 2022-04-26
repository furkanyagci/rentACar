package com.etiya.rentACar.business.constants.messages;

public class BusinessMessages {
	public class MaintananceMessages{
		public static final String MAINTANANCE_ADDED="MAINTANANCE_ADDED";
		public static final String MAINTANANCE_DELETED="MAINTANANCE_DELETED";
		public static final String MAINTANANCE_UPDATED="MAINTANANCE_UPDATED";
	}
	
	public class BrandMessages{
		public static final String BRAND_ADDED="BRAND_ADDED";
		public static final String BRAND_DELETED="BRAND_DELETED";
		public static final String BRAND_UPDATED="BRAND_UPDATED";
		public static final String BRAND_ALREADY_EXISTS="BRAND_ALREADY_EXISTS";

	}
	
	public class CarMessages{
		public static final String CAR_ADDED="CAR_ADDED";/*CAR_ADDED db de bir key'e karşılık geliyor
		daha sonra translation tablosunda kullanmak için key'imiz.tablodan hangi dilde karşılığını isterse çekeceğiz
		*/
		public static final String CAR_DELETED="CAR_DELETED";
		public static final String CAR_UPDATED="CAR_UPDATED";
		public static final String CAR_LISTED = "CAR_LISTED";
		
		public static final String CAR_UNDERMAINTANANCE="CAR_UNDERMAINTANANCE";
		public static final String CAR_RENTED="CAR_RENTED";
	}
	
	public class ColorMessages{
		public static final String COLOR_ADDED="COLOR_ADDED";
		public static final String COLOR_DELETED="COLOR_DELETED";
		public static final String COLOR_UPDATED="COLOR_UPDATED";
		public static final String COLOR_IS_ALREADY_SAVED="COLOR_IS_ALREADY_SAVED";

	}
	
	public class DamageMessages{
		public static final String DAMAGE_ADDED="DAMAGE_ADDED";
		public static final String DAMAGE_DELETED="DAMAGE_DELETED";
		public static final String DAMAGE_UPDATED="DAMAGE_UPDATED";
	}
	
	public class RentalMessages{
		public static final String RENTAL_ADDED="RENTAL_ADDED";
		public static final String RENTAL_DELETED="RENTAL_DELETED";
		public static final String RENTAL_UPDATED="RENTAL_UPDATED";
	}
	
	public class CustomerMessages{
		public static final String CUSTOMER_ADDED="CUSTOMER_ADDED";
		public static final String CUSTOMER_DELETED="CUSTOMER_DELETED";
		public static final String CUSTOMER_UPDATED="CUSTOMER_UPDATED";
		public static final String CUSTOMER_REGISTERED_NATIONALITYNUMBER="CUSTOMER_REGISTERED_NATIONALITYNUMBER";
	}
	
	public class CityMessages{
		public static final String CITY_ADDED="CITY_ADDED";
		public static final String CITY_DELETED="CITY_DELETED";
		public static final String CITY_UPDATED="CITY_UPDATED";
		public static final String CITY_REGISTERED_NAME="CITY_REGISTERED_NAME";
	}
	
	public class AdditionalServiceMessages{
		public static final String ADDITIONALSERVICE_ADDED="ADDITIONALSERVICE_ADDED";
		public static final String ADDITIONALSERVICE_DELETED="ADDITIONALSERVICE_DELETED";
		public static final String ADDITIONALSERVICE_UPDATED="ADDITIONALSERVICE_UPDATED";
		public static final String ADDITIONALSERVICE_REGISTERED_NAME="ADDITIONALSERVICE_REGISTERED_NAME";
	}

	public class InvoiceMessages{
		public static final String INVOICE_ADDED="INVOICE_ADDED";
		public static final String INVOICE_DELETED="INVOICE_DELETED";
		public static final String INVOICE_UPDATED="INVOICE_UPDATED";
	}

	public class PaymentMessages{
		public static final String PAYMENT_ADDED="PAYMENT_ADDED";
		public static final String PAYMENT_DELETED="PAYMENT_DELETED";
		public static final String PAYMENT_UPDATED="PAYMENT_UPDATED";
		public static final String PAYMENT_ERROR="PAYMENT_ERROR";
	}
}

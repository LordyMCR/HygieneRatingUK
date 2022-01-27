package ap_Lab11;

import java.time.LocalDate;

public class Restaurant {
	
	int id;
	String BusinessName;
	String AddressLine1;
	String AddressLine2;
	String AddressLine3;
	String PostCode;
	int RatingValue;
	LocalDate RatingDate;
	String Latitude;
	String Longitude;
	
	public Restaurant() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusinessName() {
		return BusinessName;
	}
	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return AddressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return AddressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		AddressLine3 = addressLine3;
	}
	public String getPostCode() {
		return PostCode;
	}
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}
	public int getRatingValue() {
		return RatingValue;
	}
	public void setRatingValue(int ratingValue) {
		RatingValue = ratingValue;
	}
	public LocalDate getRatingDate() {
		return RatingDate;
	}
	public void setRatingDate(LocalDate ratingDate) {
		RatingDate = ratingDate;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\n BusinessName: " + BusinessName + "\n AddressLine1: " + AddressLine1
				+ "\n AddressLine2: " + AddressLine2 + "\n AddressLine3: " + AddressLine3 + "\n PostCode: " + PostCode
				+ "\n RatingValue: " + RatingValue + "\n RatingDate: " + RatingDate + "\n Latitude: " + Latitude
				+ "\n Longitude: " + Longitude + "\n\n";
	}

}

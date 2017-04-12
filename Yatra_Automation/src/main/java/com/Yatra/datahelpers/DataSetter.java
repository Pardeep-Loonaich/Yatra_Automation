package com.Yatra.datahelpers;

public class DataSetter {

	String source;
	String destination;
	String departureDate;
	String returnDate;
	String passengerInfo;
	String passengerClass;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getPassengerInfo() {
		return passengerInfo;
	}

	public void setPassengerInfo(String passengerInfo) {
		this.passengerInfo = passengerInfo;
	}

	public String getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(String passengerClass) {
		this.passengerClass = passengerClass;
	}

	public DataSetter setValues(DataSetter dataSetter) {

		this.source = this.source != null ? this.getSource() : "Delhi";
		this.destination = this.destination != null ? this.getDestination()
				: "Bangalore";
		this.departureDate = this.departureDate != null ? this
				.getDepartureDate() : "4";
		this.returnDate = this.returnDate != null ? this.getReturnDate() : "5";
		this.passengerClass = this.passengerClass != null ? this
				.getPassengerClass() : "Business";
		this.passengerInfo = this.passengerInfo != null ? this
				.getPassengerInfo() : "SharmaJi";

		return this;
	}

}

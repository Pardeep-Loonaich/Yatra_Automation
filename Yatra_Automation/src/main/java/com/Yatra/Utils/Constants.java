package com.Yatra.Utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	// >>> Flights Trip type
	public static final String C_ONEWAY = "ONEWAY";
	public static final String C_ROUNDTRIP = "ROUNDTRIP";
	public static final String C_MULTICITY = "MULTICITY";
	public static final String C_EXPLORE = "EXPLORE";
	
	// '>>> Flights Passengers class types
	public static final String C_ECONOMY = "Economy";
	public static final String C_PREMIUM_ECONOMY = "Premium Economy";
	public static final String C_BUSINESS = "Business";
	public static final String C_FIRST_CLASS = "First Class";		
	// <<<<<<<< #end region
	
	// >>>> Page Constants Begins>>>>
	public static final int C_Page_Top = -1000;
	public static final int C_Page_Bottom = 2500;
	
	// >>> Alert Messages
	public static final String C_FareAlert_Title = "Set A Fare Alert";
	public static final String C_FareAlert_Message = "Your fare alert has been set! You'll get an e-mail soon!";
	public static final String C_ShareItinerary_Message = "Your itinerary has been mailed successfully.";
	public static final String C_FlightDestination_ErrorMessage = "Please enter different from and to city.";	
	
	// >>> Filter Titles in SRP
	public static final String C_Price = "PRICE";
	public static final String C_DepartTime = "DEPART TIME";
	public static final String C_Stops = "STOPS";
	public static final String C_FareType = "FARE TYPE";
	public static final String C_Airlines = "AIRLINES";

	public static List<String> performanceData=new ArrayList<String>();
	
	// >>> URL Parameters
	public static final String c_Params = "air-search-ui";
	public static final String c_TriggerType = "type"; //trigger?type
	public static final String c_Origin = "origin";
	public static final String c_OriginCountry= "originCountry";
	public static final String c_Destination = "destination";
	public static final String c_DestinationCountry = "destinationCountry";
	public static final String c_NonStop = "non_stop";

	// >>>> Page Constants End>>>>
}

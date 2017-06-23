package com.Yatra.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
	
	/**
	 Page load time:
	 */
	public static final int Home_Page_Load_Time=10;
	public static final int Search_Result_Page_Load_Time=30;
	public static final int Bus_Search_Result_Page_Load_Time=30;

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

	public static Map<String,String> performanceData=new LinkedHashMap<String,String>();
	
	// >>> URL Parameters
	public static final String c_Params = "air-search-ui";
	public static final String c_TriggerType = "type"; //trigger?type
	public static final String c_Origin = "origin";
	public static final String c_OriginCountry= "originCountry";
	public static final String c_Destination = "destination";
	public static final String c_DestinationCountry = "destinationCountry";
	public static final String c_NonStop = "non_stop";
	
	// >>> URL Parameters for Multicity	
	public static final String c_Origin_0 = "origin_0";
	public static final String c_OriginCountry_0 = "originCountry_0";
	public static final String c_Destination_0 = "destination_0";
	public static final String c_DestinationCountry_0 = "destinationCountry_0";	
	public static final String c_Origin_1 = "origin_1";
	public static final String c_OriginCountry_1 = "originCountry_1";
	public static final String c_Destination_1 = "destination_1";
	public static final String c_DestinationCountry_1 = "destinationCountry_1";
	// >>>> Page Constants End>>>>
}

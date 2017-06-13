package com.Yatra.Utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	// >>> Flights Trip type
	public static final String C_ONEWAY = "ONEWAY";
	public static final String C_ROUNDTRIP = "ROUNDTRIP";
	public static final String C_MULTICITY = "MULTICITY";
	
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
	
	public static List<Double> performanceData=new ArrayList<Double>();
	
			
		
	// >>>> Page Constants End>>>>
}
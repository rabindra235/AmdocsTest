
/*
 * 
 * @author: Rabindra Sah
 * @Date: 11/06/2017
 * 
 */
public class ParkingCost {
	
	

	
	
	public static void main(String [] args)
	
	
	{
		

		
		String E = "21:00"; //test value
		String L = "00:30"; //test value

		// splitting the string with reference to ":"
		String strEntrance[] = E.split(":");
		String strLeaving []= L.split(":");
		//total parking cost
		int TotalParking = 0;
		int entryCost = 2;
		int firstHourCost = 3;
		
		//System.out.println(strEntrance[0]);
		//converting the String value to integer
		int entranceHour= Integer.parseInt(strEntrance[0]);
		int entranceMinute = Integer.parseInt(strEntrance[1]);
		int leavingHour= Integer.parseInt(strLeaving[0]);
		
		//checking if the leaving hour is midnight 00:00 HRS
		//then the hour hand is 24 based on 24HR clock
		if(leavingHour==0)
		{
			leavingHour=24;
		}
		int leavingMinute = Integer.parseInt(strLeaving[1]);
				
		int minSpend = leavingMinute-entranceMinute;
		
		
		
		//calculate the cost if parking space was left without completing another hour of stay i.e without completing the partial hour stay
		if(minSpend<0)
		{
			int hour = leavingHour-entranceHour;
			int hourSpend = hour-1;
			//int partialHour = 1;
			int partialHourCost = 1*4;
			int totalHourAfterFirstHour = hourSpend-1;
			int totalCostAfterFirstHour = totalHourAfterFirstHour*4;
			 TotalParking = entryCost+firstHourCost+totalCostAfterFirstHour+partialHourCost;
		}
		
		//calculate the cost if parking space was left after completing another hour of stay and with partial hour addition
		else if (minSpend>0)
		{
			
			int hour = leavingHour-entranceHour;
			int hourSpend = hour+1;
			int totalHourAfterFirstHour = (hourSpend-1);
			int totalCostAfterFirstHour = totalHourAfterFirstHour*4;
			 TotalParking = entryCost+firstHourCost+totalCostAfterFirstHour;
			
		}
		//calculate the cost if there was no additional partial stay
		else if(minSpend==0)
		{
			int hour = leavingHour-entranceHour;
			int hourSpend = hour;
			int totalHourAfterFirstHour = (hourSpend-1);
			int totalCostAfterFirstHour = totalHourAfterFirstHour*4;
			 TotalParking = entryCost+firstHourCost+totalCostAfterFirstHour;
		}
		
		System.out.println(TotalParking);
	
		
	}


}

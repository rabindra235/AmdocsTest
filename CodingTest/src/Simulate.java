import java.util.Scanner;

/*
 * @author :rabindra sah
 * created :16/12/2015
 * contact :rabindra235@gmail.com
 */

public class Simulate 
{
	private int xCoordinate;
	private int yCoordinate;
	private String directionFacing;
	private boolean initExecutedOnce;
	/*
	 * this method provides the menu option to user
	 */
		public void menu()
    {			
		System.out.println("====== Welcome to Car Test Drive =======");
		System.out.println("====== Select form Menu =======\n");
    	System.out.println(" 1. INIT");
    	System.out.println(" 2. FORWARD");
    	System.out.println(" 3. TURN_LEFT");
    	System.out.println(" 4. TURN_RIGHT");
    	System.out.println(" 5. GPS_REPORT");
    	System.out.println(" 6. EXIT");
    	@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
    	while(!scr.hasNextInt())
    	{
    		System.out.println("kindly select between 1 to 6 from the menu.");
    		menu();
    		scr.next();
    	}
		int menuOption=scr.nextInt();
		performAction(menuOption);
    }
		/*
		 * this method performs the action based on option choosen from menu
		 * @param  menuOption the interger value opted from menu
		 */
	public void performAction(int menuOption)
	{
		if(menuOption<0 || menuOption>6)
		{
			System.out.println("kindly select between 1 to 6 from the menu.");
			menu();
		}
		
		else
		{
				switch(menuOption)
				{
				case 1: initializePosition();
						break;
				case 2:checkValidInitExecutedFirst(menuOption); 
						//forward();
						break;
				case 3: checkValidInitExecutedFirst(menuOption); 
						//turnLeft();
						break;
				case 4: checkValidInitExecutedFirst(menuOption); 
						//turnRight();
						break;
				case 5: checkValidInitExecutedFirst(menuOption); 
						//gpsReport();
						break;
				case 6: exit();
						break;		
				}
		}		
				
		
	}
	/*
	 * this method prompts user to provide the input in a specific format
	 */
	public String showUserInput()
    {
		int x,y=0;
		String face ="";
		String inputCoordinate="";
    	
    	System.out.println("\n-Intialize the position of car.\n"
				+ "-Provide x,y coordinate to start with.\n"
				+ "-Use the letter E,W,N,S to face the car either in East,West,North or South direction.\n"
				+ "-e.g 0,0,E\n"
				+ "-You can choose to quit the init process.");
    	
    	
    	
    	System.out.println("\nDo you wish to continue with init process. Press 'y' to continue or 'n' to quit.\n");
    	@SuppressWarnings("resource")
    	Scanner scr = new Scanner(System.in);
    	String input = scr.next();
    	//checks if user pressed y or n to continue.User is prompted again if input is not y or n.
    	while(!(input.equalsIgnoreCase("y")|| input.equalsIgnoreCase("n")))
    	{
    		System.out.println("\nInvalid choice. Please provide your input again,press y or n only.\n");
    		 input = scr.next();
    		
    	}
    	if(input.equalsIgnoreCase("n"))
    	{
    		menu();
    		scr.next();
    	}
    	 if(input.equalsIgnoreCase("y"))
    	{
    		System.out.println("Enter X-cordinate for pisitioning car.");
    		@SuppressWarnings("resource")
        	Scanner scr1 = new Scanner(System.in);
    	while(!scr1.hasNextInt())
    	{
    		System.out.println("Invalid input for X-cordinate.Please provide numeric value.");
    		 scr1.next();
    	}
		 x=scr1.nextInt();
		scr1 = new Scanner(System.in);
		System.out.println("Enter Y-cordinate for pisitioning car.");
		while(!scr1.hasNextInt())
    	{
    		System.out.println("Invalid input for Y-cordinate.Please provide numeric value.");
    		 scr1.next();
    	}
		y=scr1.nextInt();
		scr1 = new Scanner(System.in);
		System.out.println("Enter facing direction, it can only be east,west,north or south.");
		while(!scr1.hasNext())
    	{
    		System.out.println("Invalid input for facing.Please provide alphabets only.");
    		 scr1.next();
    	}
		face=scr1.next();
		if(face.length()>1)
		{
			face = face.substring(0, 1);
		}
		
		inputCoordinate = Integer.toString(x)+","+Integer.toString(y)+","+face;
		
    	}
    	
    	return inputCoordinate;
    }
	/*
	 * this method places the car at its intial position
	 */
	public void initializePosition()
	{
		String initialValue = showUserInput();
		boolean userInput = validatePosition(initialValue);
		
		while(userInput == false)
		{
			System.out.println("Invalid input."
					+ "Input should contian positive x & y coordinate and should not exit 5*5 gird,\n"
					+ "provide an appropriate facing direction.");
			System.out.println("Your Input: "+initialValue);
			initialValue=showUserInput();
			userInput = validatePosition(initialValue);
			setInitExecutedOnce(false);
		}
		initializeGPS(initialValue);
		setInitExecutedOnce(true);
		System.out.println("\n Thankyou. Car position is initiliazed you can now proceed by choosing from menu.\n");
		menu();
	}
	
	
	
	/*
	 * this method checks if the initial position places the car inside 5*5 grid
	 * @param position the input from the user as initial position of car
	 * @return  true or false condition for the position
	 */
	
	  public boolean validatePosition(String position)
	    {
	    	boolean xaxis = false ;
	    	boolean yaxis = false;
	    	boolean direction= false;
	    		    	
	    	String positionArray[]=position.split(",");
	    	
	    	// User input should only contain 3 values ie <x>,<y>,<F>
	    	//check if there is more than or less than 3 input values 
	    	if(positionArray.length>3||positionArray.length<3)
	    	{
	    		return false;
	    	}
	    	else
	    	{
	    		int xAxisValue = Integer.parseInt(positionArray[0]);
	    		int yAxisValue = Integer.parseInt(positionArray[1]);
	    		String facingDirection=positionArray[2];
	    		//considering only the first letter of direction facing
	    		
	    		if(xAxisValue>=0 && xAxisValue<=5)
	    		{
	    			xaxis = true;
	    		}
	    		if(yAxisValue>=0 && yAxisValue<=5)
	    		{
	    			yaxis = true;
	    		}
	    		
	    		if(		facingDirection.equalsIgnoreCase("E")||
	    				facingDirection.equalsIgnoreCase("W")||
	    				facingDirection.equalsIgnoreCase("N")||
	    				facingDirection.equalsIgnoreCase("S")
	    				)
	    		
	    		{
	    			direction = true;
	    		}
	    		
	    	}
	    	if((xaxis && yaxis && direction)==true)
	    	{
	    		return true;
	    	}
	    	else
	    		return false;
	    	
	    }
	  
	  	/*
		 * this method initialize the gps as soon as the position of car is initialized
		 * @param position the input from the user as initial position of car
		 
		 */
	  public void initializeGPS(String position)
	  {
			String positionArray[]=position.split(",");
			setxCoordinate(Integer.parseInt(positionArray[0])); 
			setyCoordinate(Integer.parseInt(positionArray[1]));
			setDirectionFacing(positionArray[2]);
			
	  }
	  
	  /*
	   * this method first check if the valid init is executed then only other movement command is allowed.
	   * @param caseValue it is the menu option from 2-5
	   */
	  public void checkValidInitExecutedFirst(int caseValue)
	  {
		  if(isInitExecutedOnce()==true)
		  {
			  switch(caseValue)
				{
				
				case 2:forward();
						break;
				case 3:turnLeft();
						break;
				case 4:turnRight();
						break;
				case 5:gpsReport();
						break;
				}
		  }
		  else
		  {
			  System.out.println("A valid INIT is not executed,before proceeding from step 2-5 step 1 must be completed.\n");
			  menu();
		  }
	  }
	  
	  	/*
		 * this method moves the car one unit in direction it is facing
		*/
	public void forward()
	{
		int xAxis = getxCoordinate();
		int yAxis = getyCoordinate();
		String direction = getDirectionFacing().toUpperCase();
		boolean boundaryValidation = validateBoundryMovement(xAxis,yAxis,direction);
		if (boundaryValidation==false)
			{
				// no movement possible in forward direction with this intial value
				infoMessage();
				menu();
			}
		else
		{
			switch(direction.toUpperCase())
			{
			case "E": moveEast(xAxis,yAxis,direction.toUpperCase());
					break;
			case "W": moveWest(xAxis,yAxis,direction.toUpperCase());
					break;
					
			case "N": moveNorth(xAxis,yAxis,direction.toUpperCase());
					break;
					
			case "S": moveSouth(xAxis,yAxis,direction.toUpperCase());
					break;
				
			}
		}
		
		
		menu();
		
	}
	/*
	 * this method checks forward movement leads the car outside 5*5 grid
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 * @return  true or false for movement considering boundary of grid
	 */
	public boolean validateBoundryMovement(int xAxis,int yAxis,String direction)
	{
		if((xAxis==0 && yAxis == 0) && (direction.equalsIgnoreCase("W")||direction.equalsIgnoreCase("S")) )
		{
			return false;
		}
		else if((xAxis==0 && yAxis == 5) && (direction.equalsIgnoreCase("W")||direction.equalsIgnoreCase("N")) )
		{
			return false;
		}
		else if((xAxis==5 && yAxis == 0) && (direction.equalsIgnoreCase("E")||direction.equalsIgnoreCase("S")) )
		{
			return false;
		}
		else if((xAxis==5 && yAxis == 5) && (direction.equalsIgnoreCase("E")||direction.equalsIgnoreCase("N")) )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/*
	 * this method moves the car one unit in East direction.
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 */
	public void moveEast(int x, int y,String facing)
	{
		//check if movement exits the grid
		if((getxCoordinate()+1)>5)
		{
			infoMessage();
			menu();
		}
		else
		{
			setxCoordinate(getxCoordinate()+1);
			updateGPS(getxCoordinate(),y,facing);
		}
	}
	/*
	 * this method moves the car one unit in West direction.
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 */
	public void moveWest(int x, int y,String facing)
	{
		//check if movement exits the grid
		
		if((getxCoordinate()-1)<0)
		{
			infoMessage();
			menu();
		}
		else
		{
			setxCoordinate(getxCoordinate()-1);
			updateGPS(getxCoordinate(),y,facing);
		}
	}
	/*
	 * this method moves the car one unit in North direction.
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 */
	public void moveNorth(int x, int y,String facing)
	{
		//check if movement exits the grid
		if((getyCoordinate()+1)>5)
		{
			infoMessage();
			menu();
		}
		else
		{
			setyCoordinate(getyCoordinate()+1);
			updateGPS(x,getyCoordinate(),facing);
		}
	}
	/*
	 * this method moves the car one unit in South direction.
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 */
	public void moveSouth(int x, int y,String facing)
	{
		//check if movement exits the grid
		if((getyCoordinate()-1)<0)
			{
				infoMessage();
				menu();
			}
		else
			{
				setyCoordinate(getyCoordinate()-1);
				updateGPS(x,getyCoordinate(),facing);
			}
		
	}
	public void infoMessage()
	{
		System.out.println("\nSorry forward movement will cause car to exits 5*5 grid.");
		System.out.println("Your Current Position is: "+getxCoordinate()+","+getyCoordinate()+","+getDirectionFacing()+"\n");
		
	}
	/*
	 * this method updates current gps position of car when a valid movement is carried out .
	 * @param xAxis x-coordinate from initial position of car
	 * @param yAxis y-coordinate from initial position of car
	 * @param direction direction facing from initial position of car
	 */
	public void updateGPS(int x, int y, String facing)
	{
		setxCoordinate(x);
		setyCoordinate(y); 
		setDirectionFacing(facing);
	}
	
	/*
	 * this method turns the car facing to its left without changing coordinates .
	 */
	
	public void turnLeft()
	{
		int currentXCoordinate = getxCoordinate();
		int currentYCoordiante = getyCoordinate();
		String changedFacing ="";
		if(getDirectionFacing().equalsIgnoreCase("E"))
		{
			changedFacing= "N";
		}
		else if(getDirectionFacing().equalsIgnoreCase("N"))
		{
			changedFacing= "W";
		}
		else if(getDirectionFacing().equalsIgnoreCase("W"))
		{
			changedFacing = "S";
		}
		else if(getDirectionFacing().equalsIgnoreCase("S"))
		{
			changedFacing = "E";
		}
		updateGPS(currentXCoordinate, currentYCoordiante, changedFacing);
		menu();
	}
	
	/*
	 * this method turns the car facing to its right without changing coordinates .
	 */
	public void turnRight()
	{
		int currentXCoordinate = getxCoordinate();
		int currentYCoordiante = getyCoordinate();
		String changedFacing ="";
		if(getDirectionFacing().equalsIgnoreCase("E"))
		{
			changedFacing = "S";
		}
		else if(getDirectionFacing().equalsIgnoreCase("N"))
		{
			changedFacing = "E";
		}
		else if(getDirectionFacing().equalsIgnoreCase("W"))
		{
			changedFacing = "N";
		}
		else if(getDirectionFacing().equalsIgnoreCase("S"))
		{
			changedFacing = "W";
			
		}
		updateGPS(currentXCoordinate, currentYCoordiante, changedFacing);
		menu();
	}
	/*
	 * this method gives the gps position of the car.
	 */
	public void gpsReport()
	{
		String facing = getDirectionFacing();
		String direction ="";
		switch(facing.toUpperCase())
		{
		case "E": direction="EAST";
				break;
		case "W": direction="WEST";
				break;
		case "N": direction="NORTH";
				break;
		case "S": direction="SOUTH";
				break;
		}
		StringBuilder gps_Report=new StringBuilder();
		gps_Report.append(String.valueOf(getxCoordinate())).append(","); 
		gps_Report.append(String.valueOf(getyCoordinate())).append(",");
		gps_Report.append(direction);
		System.out.println("\n Output:"+gps_Report.toString()+"\n"); 
		menu();
	}
	
	/*
	 * this method exits the program.
	 */
	public void exit()
	{
		System.out.println("Thankyou for tesitng the car movement");
		System.exit(0);
	}
	
	// getter and setter for x,y coordinate and facing 
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String getDirectionFacing() {
		return directionFacing;
	}

	public void setDirectionFacing(String directionFacing) {
		this.directionFacing = directionFacing;
	}
	public boolean isInitExecutedOnce() {
		return initExecutedOnce;
	}
	public void setInitExecutedOnce(boolean initExecutedOnce) {
		this.initExecutedOnce = initExecutedOnce;
	}
    

}

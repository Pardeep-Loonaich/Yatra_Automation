package com.Yatra.Utils;

/**
 * Purpose: this class contains all to capture time <br>
 * for specific action<b>
 * status.. not completed need to work more on it ..
 * @author harveer.singh
 *
 */
public class ExecutionTimer 
{
	private int start;
	  private int end;

	  public ExecutionTimer() 
	  {
	    reset();
	   
	  }

	  public void end() 
	  {
	    end = (int)((System.currentTimeMillis()/1000)%3600);
	    
	  }
	  public int start()
	  {
		  
		  start=(int)((System.currentTimeMillis()/1000)%3600);
		  return start;
	  }

	  public int duration()
	  {
	    return (end-start);
	  }

	  public void reset() 
	  {
	    start = 0;  
	    end   = 0;
	  }
}

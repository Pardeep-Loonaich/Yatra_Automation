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
	private long start;
	  private long end;

	  public ExecutionTimer() {
	    reset();
	    start = System.currentTimeMillis();
	  }

	  public void end() {
	    end = System.currentTimeMillis();
	  }

	  public long duration(){
	    return (end-start);
	  }

	  public void reset() {
	    start = 0;  
	    end   = 0;
	  }
}

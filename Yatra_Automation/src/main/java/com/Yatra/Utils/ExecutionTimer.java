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

	public ExecutionTimer() 
	{
		reset();

	}

	/**
	 * Description: end timer
	 */
	public long end() 
	{
		end = System.currentTimeMillis();
		return end;
	}
	public long start()
	{

		start=System.currentTimeMillis();
		return start;
	}

	/**
	 * calculate total time 
	 * @return
	 */
	public double duration()
	{
		return (end-start);
	}
	/**
	 * Desc:reset start and end to zero
	 */
	public void reset() 
	{
		start = 0;  
		end   = 0;
	}
}

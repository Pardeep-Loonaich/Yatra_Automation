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

	public void end() 
	{
		end = (long)(System.currentTimeMillis());

	}
	public long start()
	{

		start=(long)(System.currentTimeMillis());
		return start;
	}

	public long duration()
	{
		return (end-start);
	}

	public void reset() 
	{
		start = 0;  
		end   = 0;
	}
}

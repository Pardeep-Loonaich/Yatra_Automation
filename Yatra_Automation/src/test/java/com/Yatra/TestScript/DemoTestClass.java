package com.Yatra.TestScript;

import org.testng.annotations.Test;

import com.Yatra.Utils.Log;

public class DemoTestClass
{
  @Test
  public void Test01() 
  {
	  if (1==1)
	  {
		  Log.pass("test case has passed ");
		  
	  }
  }
  
  @Test
  public void Test02() 
  {
	  if (1==2)
	  {
		  Log.pass("test case has passed ");
		  
	  }
	  else
	  {
		  
		  Log.fail("Test case has been failed check log for details!!");
	  }
  }
}

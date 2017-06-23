package com.Yatra.Utils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarPage;
import net.lightbody.bmp.proxy.CaptureType;

/**
 * Desc:this class have all required method for Browser MobProxy<br>
 * we use browser mob proxy to measure application performance
 * @author harveer.singh
 *
 */
@SuppressWarnings("unused")
public class BrowserMob

{
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
	public final  static BrowserMobProxy mobProxy=new BrowserMobProxyServer();


	public Proxy startBrowseyMobProxy()
	{

		if("true".equalsIgnoreCase(configProperty.getProperty("isBrowserMobProxy").trim()));
		{
			mobProxy.start();
			
			mobProxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT,CaptureType.RESPONSE_CONTENT);//set content type
			mobProxy.newHar("Yatra.com");//label for har file
			return ClientUtil.createSeleniumProxy(mobProxy);


		}
	}

	public static void getHarData(BrowserMobProxy mobProxy)
	{
		Har har = mobProxy.getHar();
		BrowserMob.printHarData(har);
		String fileName="D:/YatraOnline01.har";
		// Write HAR Data in a File
		File harFile = new File(fileName);
		try {
			har.writeTo(harFile);

		} catch (IOException ex) {
			Log.message(ex.getMessage());;
			Log.message("Could not find file " + fileName);
		}
		finally
		{
			mobProxy.stop();
		}
	}
	/**
	 * To print the Har Summary details
	 * 
	 * @param har
	 */
	public static void printHarData(Har har) 
	{
		HarLog log = har.getLog();

		List<HarEntry> harEntries = log.getEntries();
		Long totalSize = 0L;
		int callCount = 0;
		long totalTime = 0;
		List<HarPage> pages=log.getPages();
		for (HarEntry entry : harEntries) {
			callCount++;

			if (entry.getResponse() == null) {
				continue;
			}
			// System.out.println( "entry:" + entry.getRequest().getUrl() );
			totalSize += entry.getResponse().getBodySize();
			totalTime += entry.getTime(TimeUnit.MILLISECONDS);

		}
		Log.message("Total page size is: "+totalSize);
		Log.message("total time is: "+totalTime);
		/*HarSummary summary = new HarSummary((double) totalSize / 1024, callCount, totalTime);
		Log.message("#################<b>PERF DATA</b>###################");
		Log.message("<br>");
		Log.message("Call count : " + summary.getCallCount());
		Log.message("Size : " + summary.getTotalPayloadSize() / 1024 + " MB");
		Log.message("Total load time : " + summary.getTotalLoadTime() / 1000 + " seconds");*/

	}

}

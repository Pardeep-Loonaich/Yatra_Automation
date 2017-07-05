package com.Yatra.dbinsert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class DBInserter {

	public static void insertIntoDB(LinkedHashMap<String, String> failedTests,
			LinkedHashMap<String, String> brokenTests,
			LinkedHashMap<String, String> skippedTests,
			LinkedHashMap<String, String> passedTests, String buildNumber)
			throws Exception {

		Statement stmt = null;
		Connection connection;
		try {
			connection = DBUtil.sqlConnectionForDBInserter("automation_dashboard");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt = DBUtil.connection.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (failedTests.size() > 0) {
			for (Entry<String, String> failed : failedTests.entrySet()) {
				String[] testCaseName = new String[2];
				String arr[] = failed.getKey().split(":");
				System.out.println("array is" + Arrays.toString(arr));
				testCaseName[0] = arr[0];
				System.out.println("Size of testCaseName:- "
						+ testCaseName.length);
				System.out.println("Test Class Name:- " + testCaseName[0]);
				// remove the below line when testMethodName in ReportUtil is
				// not description
				testCaseName[1] = arr[1];
				System.out.println("testName:" + testCaseName[1]);

			}

		}

		if (brokenTests.size() > 0) {

		}

		if (passedTests.size() > 0) {

			System.out.println("came to passed");
			for (Entry<String, String> passed : passedTests.entrySet()) {
				String[] testCaseName = new String[2];
				String arr[] = passed.getKey().split(":");
				System.out.println("array is" + arr);
				testCaseName[0] = arr[0];
				System.out.println("Size of testCaseName:- "
						+ testCaseName.length);
				System.out.println("Name:- " + testCaseName[0]);
				// remove the below line when testMethodName in ReportUtil is
				// not description
				testCaseName[1] = arr[1];
				System.out.println("testName:" + arr[1]);
				String testResult = "Pass";
				String query = "INSERT INTO run_results (testname, testresult, testclassname) VALUES('"
						+ testCaseName[1]
						+ "','"
						+ testResult
						+ "','"
						+ testCaseName[0] + "');";
				System.out.println(query);
				try {
					stmt.executeUpdate(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		if (skippedTests.size() > 0) {

		}
	}
}

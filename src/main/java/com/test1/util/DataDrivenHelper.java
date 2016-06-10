package com.test1.util;

// DataDrivenHelper class to get the test data sets from excel file
// This class inherits from ExcelHelper class to reuse all code in ExcelHelper class
public class DataDrivenHelper extends ExcelHelper 
{
	// Specify test name column
	// We are using a variable for test name column so that if column changes in future, we just need to change this variable only
	public final static int testNameColumn = 1;
	
	// Specify test data start column
	// We are using a variable for test data start column so that if column changes in future, we just need to change this variable only
	public final static int testDataStartColumn = 2;

	// DataDrivenHelper constructor which takes Excel file path and then passes to super class ExcelHelper
	public DataDrivenHelper(String filePath) {
		super(filePath);
	}
	
	// Returns 2 dimensional object array representing test data sets when sheet and test names are provided
	public Object[][] getTestCaseDataSets(String sheetName, String testName)
	{
		// Get test case row number and test data start row number
		int testRowNumber = getRowNumber(sheetName, testNameColumn, testName);
		int testDataStartRow = testRowNumber +1;
		
		// Calculate the test data row count
		int testDataRows = 0;
		for(int i=testDataStartRow; getCellData(sheetName, testNameColumn, i).equals(testName); i++ )
		{
			testDataRows++;
		}
		
		// Calculate the test data column count
		int testDataCols = getCellCount(sheetName, testRowNumber) - testDataStartColumn+1;
		
		// to define a 2 dimensional object array to hold test data sets
		Object[][] testCaseDataSets = new Object[testDataRows][testDataCols];
		
		// to read test data cells from Excel file and assign into Object[][] testCaseDataSets
		for(int i=0;i<testDataRows;i++)
		{
			for(int j=0;j<testDataCols;j++)
			{
				testCaseDataSets[i][j] = getCellData(sheetName, testDataStartColumn+j, testDataStartRow+i);
			}
		}
		
		return testCaseDataSets;
		
	}
	
}
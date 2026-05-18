package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = ".\\testData\\Opencart_LoginData2.xlsx"; // taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating object for XLUtility

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String logindata[][] = new String[totalrows][totalcols]; // created 2D array

        for (int i = 1; i <= totalrows; i++) // read data from xl
        {
            for (int j = 0; j < totalcols; j++) // i = rows, j = cols
            {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return logindata; // returning 2D array
    }
}
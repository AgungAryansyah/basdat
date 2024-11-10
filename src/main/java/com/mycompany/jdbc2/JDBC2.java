package com.mycompany.jdbc2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JDBC2 {

    public static void main(String[] args) throws ClassNotFoundException {
//        String hostname = "localhost";
//        String sqlInstanceName = "sql1"; // SQL Server instance name
//        String sqlDatabase = "JDBC";  // Database name
//        String sqlUser = "sa";
//        String sqlPassword = "Arifsql1"; // Password for sa account
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String connectURL = "jdbc:sqlserver://" + hostname + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase + ";encrypt=true;trustServerCertificate=true";
//        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword); Statement stmt = conn.createStatement()) {
//            String selects = "SELECT * FROM ExampleTable";
//            ResultSet resultSet = stmt.executeQuery(selects);
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                System.out.printf("ID: %d, Name: %s\n", id, name);
//            }
//            System.out.println("Query executed successfully.");
//            System.out.println("Connected to the database successfully!");
//        } catch (SQLException e) {
//            System.err.println("SQL Error: " + e.getMessage());
//            e.printStackTrace();
//        }
        String filePath = "src/main/resources/OfficeSales.xlsx";
        File file = new File(filePath);
        int i = 0;
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Print the cell value based on the type
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unsupported cell type\t");
                            break;
                    }
                }
                i++;
                System.out.println(); 
                if (i == 10) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

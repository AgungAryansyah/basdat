package com.mycompany.jdbc2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JDBC2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String hostname = "localhost";
        String sqlInstanceName = "sql1"; // SQL Server instance name
        String sqlDatabase = "JDBC";  // Database name
        String sqlUser = "sa";
        String sqlPassword = "Arifsql1"; // Password for sa account
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectURL = "jdbc:sqlserver://" + hostname + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase + ";encrypt=true;trustServerCertificate=true";
        String filePath = "src/main/resources/CoreData.xlsx";
        File file = new File(filePath);
        //Insert Customer
//        String filePaths = "/home/jbe/Documents/Customer.txt";
//        try (FileWriter writer = new FileWriter(filePaths); FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis); Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
//            Sheet sheet = workbook.getSheetAt(0);
//            int x = 0;
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) {
//                    continue;
//                }
//                if (x == 996) {
//                    break;
//                }
//                int CKeys = (int) row.getCell(0).getNumericCellValue();
//                String CKey = String.valueOf(CKeys);
//                String CName = row.getCell(1).getStringCellValue();
//                String States = row.getCell(2).getStringCellValue();
//                String Region = row.getCell(4).getStringCellValue();
//                String Country = row.getCell(3).getStringCellValue();
//                String Market = row.getCell(5).getStringCellValue();
//                String BusinessType = row.getCell(6).getStringCellValue();
//                String temp = String.format("INSERT INTO Customer (CKey, CName, States, Region, Country, Market, BusinessType) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", CKey, CName, States, Region, Country, Market, BusinessType);
//                writer.write(temp + "\n");
//                x++;
//            }
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Insert Product
        String filePaths = "/home/jbe/Documents/Product.txt";
        try (FileWriter writer = new FileWriter(filePaths); FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis); Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            Sheet sheet = workbook.getSheetAt(3);
            HashSet<String> set = new HashSet<>();
            int x = 0;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                if (x == 1750) {
                    break;
                }
                x++;
                String PKey = row.getCell(0).getStringCellValue();
                if (set.contains(PKey)) {
                    continue;
                }
                set.add(PKey);
                String Category = row.getCell(1).getStringCellValue();
                String SubCategory = row.getCell(2).getStringCellValue();
                String PName = row.getCell(3).getStringCellValue();
                String Information = row.getCell(4).getStringCellValue();
                double Price = (double) row.getCell(5).getNumericCellValue();

                String temp = String.format("INSERT INTO Product (PKey, Category, SubCategory, PName, Information, Price) VALUES ('%s', '%s', '%s', '%s', '%s', %.2f)", PKey, Category, SubCategory, PName, Information, Price);
                writer.write(temp + "\n");
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Insert Transaction
//        String filePaths = "/home/jbe/Documents/Transaction.txt";
//        HashSet<String> set = new HashSet<>();
//        try (FileWriter writer = new FileWriter(filePaths); FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis); Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
//            Sheet sheet = workbook.getSheetAt(1);
//            int x = 0;
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) {
//                    continue;
//                }
//                if (x == 1750) {
//                    break;
//                }
//                x++;
//                String SON = row.getCell(0).getStringCellValue();
//                if (set.contains(SON)) {
//                    continue;
//                }
//                set.add(SON);
//                int CKeys = (int) row.getCell(1).getNumericCellValue();
//                String CKey = String.valueOf(CKeys);
//                Date ordertemp = row.getCell(2).getDateCellValue();
//                Date deliverytemp = row.getCell(3).getDateCellValue();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String order = dateFormat.format(ordertemp);
//                String delivery = dateFormat.format(deliverytemp);
//                String ship = row.getCell(4).getStringCellValue();
//                String temp = String.format("INSERT INTO Transactions (SalesOrderNumber, CKey, OrderDate, DeliveryDate, ShipMode) VALUES ('%s', '%s', '%s', '%s', '%s')", SON, CKey, order, delivery, ship);
//                writer.write(temp + "\n");
//            }
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Insert SalesDetail
//        String filePaths = "/home/jbe/Documents/Order.txt";
//        try (FileWriter writer = new FileWriter(filePaths); FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis); Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
//            Sheet sheet = workbook.getSheetAt(2);
//            int x = 0;
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) {
//                    continue;
//                }
//                if (x == 1750) {
//                    break;
//                }
//                String sales = row.getCell(0).getStringCellValue();
//                String PKey = row.getCell(1).getStringCellValue();
//                int quantity = (int) row.getCell(2).getNumericCellValue();
//                double discount = (double) row.getCell(3).getNumericCellValue();
//                double cost = (double) row.getCell(4).getNumericCellValue();
//                String prior = row.getCell(5).getStringCellValue();
//
//                String temp = String.format("INSERT INTO Orders (SalesOrderNumber, PKey, OrderQuantity, Discount, ShippingCost, OrderPriority) VALUES ('%s', '%s', %d, %.1f, %.2f,'%s')", sales, PKey, quantity, discount, cost, prior);
//                writer.write(temp + "\n");
//                x++;
//            }
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

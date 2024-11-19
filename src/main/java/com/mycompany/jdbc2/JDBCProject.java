package com.mycompany.jdbc2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JDBCProject extends javax.swing.JFrame {

    String hostname = "localhost";
    String sqlInstanceName = "sql1"; // SQL Server instance name
    String sqlDatabase = "JDBC";  // Database name
    String sqlUser = "sa";
    String sqlPassword = "....."; // Password for sa account
    String connectURL = "jdbc:sqlserver://" + hostname + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase + ";encrypt=true;trustServerCertificate=true";
    /**
     * Creates new form JDBCProject
     */
    public JDBCProject() throws SQLException, ClassNotFoundException {
        initComponents();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        loadCustomerTable();
        loadOrderTable();
        loadProductTable();
        loadTransactionTable();

        //Menambah Fungsi Tombol Costumer
        CustomerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = CustomerTable.getSelectedRow();
                if (selectedRow != -1) {
                    String customerKey = CustomerTable.getValueAt(selectedRow, 0).toString();
                    String CName = CustomerTable.getValueAt(selectedRow, 1).toString();
                    String State = CustomerTable.getValueAt(selectedRow, 2).toString();
                    String Region = CustomerTable.getValueAt(selectedRow, 3).toString();
                    String Country = CustomerTable.getValueAt(selectedRow, 4).toString();
                    String Market = CustomerTable.getValueAt(selectedRow, 5).toString();
                    String bt = CustomerTable.getValueAt(selectedRow, 6).toString();
                    CustomerText.setText(customerKey);
                    CNameText.setText(CName);
                    StateText.setText(State);
                    RegionText.setText(Region);
                    CountryText.setText(Country);
                    MarketText.setText(Market);
                    BTText.setText(bt);
                }
            }
        });

        //Menambah Fungsi Tombol Product
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = ProductTable.getSelectedRow();
                if (selectedRow != -1) {
                    String PKey = ProductTable.getValueAt(selectedRow, 0).toString();
                    String C = ProductTable.getValueAt(selectedRow, 1).toString();
                    String SC = ProductTable.getValueAt(selectedRow, 2).toString();
                    String PName = ProductTable.getValueAt(selectedRow, 3).toString();
                    String i = ProductTable.getValueAt(selectedRow, 4).toString();
                    String price = ProductTable.getValueAt(selectedRow, 5).toString();
                    PKeyText.setText(PKey);
                    CategoryText.setText(C);
                    SCText.setText(SC);
                    PNameText.setText(PName);
                    InfoText.setText(i);
                    PriceText.setText(price);
                }
            }
        });

        //Menambah Fungsi Tombol Order
        OrderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = OrderTable.getSelectedRow();
                if (selectedRow != -1) {
                    String SON = OrderTable.getValueAt(selectedRow, 0).toString();
                    String PKey = OrderTable.getValueAt(selectedRow, 1).toString();
                    String OQ = OrderTable.getValueAt(selectedRow, 2).toString();
                    String Dis = OrderTable.getValueAt(selectedRow, 3).toString();
                    String Ship = OrderTable.getValueAt(selectedRow, 4).toString();
                    String OP = OrderTable.getValueAt(selectedRow, 5).toString();
                    SONText.setText(SON);
                    PKey2Text.setText(PKey);
                    OQText.setText(OQ);
                    DisText.setText(Dis);
                    ShipText.setText(Ship);
                    OPText.setText(OP);
                }
                
            }
        });

        //Menambah Fungsi Tombol Transaction
        TransactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = TransactionTable.getSelectedRow();
                if (selectedRow != -1) {
                    String SON = TransactionTable.getValueAt(selectedRow, 0).toString();
                    String CKey = TransactionTable.getValueAt(selectedRow, 1).toString();
                    String OD = TransactionTable.getValueAt(selectedRow, 2).toString();
                    String DD = TransactionTable.getValueAt(selectedRow, 3).toString();
                    String SM = TransactionTable.getValueAt(selectedRow, 4).toString();
                    SON2Text.setText(SON);
                    CKey2Text.setText(CKey);
                    ODText.setText(OD);
                    DDText.setText(DD);
                    SMText.setText(SM);
                }
                
            }
        });
        
        //Menambah Fungsi Tombol Tambah
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (halamanDibuka.equals("CustomerPanel")){
                    try{
                        tambahCustomer();
                        loadCustomerTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("ProductPanel")){
                    try{
                        tambahProduct();
                        loadProductTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("OrderPanel")){
                    try{
                        tambahOrder();
                        loadOrderTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("TransactionPanel")){
                    try{
                        tambahTransaction();
                        loadTransactionTable();
                    } catch (Exception e){

                    }
                }
            }
        });

        tombolHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (halamanDibuka.equals("CustomerPanel")){
                    try{
                        hapusCustomer();
                        loadCustomerTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("ProductPanel")){
                    try{
                        hapusProduct();
                        loadProductTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("OrderPanel")){
                    try{
                        hapusOrder();
                        loadOrderTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("TransactionPanel")){
                    try{
                        hapusTransaction();
                        loadTransactionTable();
                    } catch (Exception e){

                    }
                }
            }
        });

        tombolUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (halamanDibuka.equals("CustomerPanel")){
                    try{
                        ubahCustomer();
                        loadCustomerTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("ProductPanel")){
                    try{
                        ubahProduct();
                        loadProductTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("OrderPanel")){
                    try{
                        ubahOrder();
                        loadOrderTable();
                    } catch (Exception e){

                    }
                } else if (halamanDibuka.equals("TransactionPanel")){
                    try{
                        ubahTransaction();
                        loadTransactionTable();
                    } catch (Exception e){

                    }
                }
            }
        });

        c_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cs_input = CustomerInput.getText();
                String cs_inputType = (String) c_stype.getSelectedItem();
                try{
                    cariCustomer(cs_input, cs_inputType);
                } catch (Exception e){
                    System.out.println("gagal");
                }
                
            }
        });

        p_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String ps_input = ProductInput.getText();
                String ps_inputType = (String) p_stype.getSelectedItem();
                try{
                    cariProduct(ps_input, ps_inputType);
                } catch (Exception e){
                    System.out.println("gagal");
                }
                
            }
        });

        o_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String os_input = OrderInput.getText();
                String os_inputType = (String) o_stype.getSelectedItem();
                try{
                    cariOrder(os_input, os_inputType);
                } catch (Exception e){
                    System.out.println("gagal 1");
                }
                
            }
        });

        t_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String ts_input = TransactionInput.getText();
                String ts_inputType = (String) t_stype.getSelectedItem();
                try{
                    cariTransaction(ts_input, ts_inputType);
                } catch (Exception e){
                    System.out.println("gagal");
                }
                
            }
        });

        
    }

    private void tambahCustomer() throws SQLException, ClassNotFoundException{
        String c_customerKey = CustomerText.getText();
        String c_customerName = CNameText.getText();
        String c_state = StateText.getText();
        String c_region = RegionText.getText();
        String c_country = CountryText.getText();
        String c_market = MarketText.getText();
        String c_bt = BTText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "insert into Customer values(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, c_customerKey);
            statement.setString(2, c_customerName);
            statement.setString(3, c_state);
            statement.setString(4, c_region);
            statement.setString(5, c_country);
            statement.setString(6, c_market);
            statement.setString(7, c_bt);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 2627){
                JOptionPane.showMessageDialog(null, "Tidak bisa memasukkan data, Custumer key '" + c_customerKey + "' sudah ada pada database", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void hapusCustomer() throws SQLException, ClassNotFoundException{
        String c_customerKey = CustomerText.getText();
        String c_customerName = CNameText.getText();
        String c_state = StateText.getText();
        String c_region = RegionText.getText();
        String c_country = CountryText.getText();
        String c_market = MarketText.getText();
        String c_bt = BTText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "delete from Customer where Ckey = ? and CName = ? and States = ? and Region = ? and Country = ? and Market = ? and BusinessType = ?");
            statement.setString(1, c_customerKey);
            statement.setString(2, c_customerName);
            statement.setString(3, c_state);
            statement.setString(4, c_region);
            statement.setString(5, c_country);
            statement.setString(6, c_market);
            statement.setString(7, c_bt);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 547){
                JOptionPane.showMessageDialog(null, "Tidak bisa menghapus data, Terdapat konfilk pada Foreign Key Customer key '" + c_customerKey + "' pada tabel Transaction", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void ubahCustomer() throws SQLException, ClassNotFoundException{
        String c_customerKey = CustomerText.getText();
        String c_customerName = CNameText.getText();
        String c_state = StateText.getText();
        String c_region = RegionText.getText();
        String c_country = CountryText.getText();
        String c_market = MarketText.getText();
        String c_bt = BTText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "update Customer set CName = ?, States = ?, Region = ?, Country = ?, Market = ?, BusinessType = ? where Ckey = ?");
            statement.setString(7, c_customerKey);
            statement.setString(1, c_customerName);
            statement.setString(2, c_state);
            statement.setString(3, c_region);
            statement.setString(4, c_country);
            statement.setString(5, c_market);
            statement.setString(6, c_bt);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
            if (rowsInserted == 0){
                JOptionPane.showMessageDialog(null, "Tidak bisa mengubah data, Terdapat konfilk pada Foreign Key Customer key '" + c_customerKey + "' pada tabel Transaction", "Error", JOptionPane.ERROR_MESSAGE);    
            } 
        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void tambahProduct() throws SQLException, ClassNotFoundException{
        String p_pkey = PKeyText.getText();
        String p_c = CategoryText.getText();
        String p_sc = SCText.getText();
        String p_pname = PNameText.getText();
        String p_i = InfoText.getText();
        String p_price = PriceText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "insert into Product values(?, ?, ?, ?, ?, ?)");
            statement.setString(1, p_pkey);
            statement.setString(2, p_c);
            statement.setString(3, p_sc);
            statement.setString(4, p_pname);
            statement.setString(5, p_i);
            statement.setString(6, p_price);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 2627){
                JOptionPane.showMessageDialog(null, "Tidak bisa memasukkan data, Product key '" + p_pkey + "' sudah ada pada database", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void hapusProduct() throws SQLException, ClassNotFoundException{
        String p_pkey = PKeyText.getText();
        String p_c = CategoryText.getText();
        String p_sc = SCText.getText();
        String p_pname = PNameText.getText();
        String p_i = InfoText.getText();
        String p_price = PriceText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "delete from Product where PKey = ? and Category = ? and SubCategory = ? and PName = ? and Information = ? and Price = ?");
            statement.setString(1, p_pkey);
            statement.setString(2, p_c);
            statement.setString(3, p_sc);
            statement.setString(4, p_pname);
            statement.setString(5, p_i);
            statement.setString(6, p_price);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 547){
                JOptionPane.showMessageDialog(null, "Tidak bisa menghapus data, Terdapat konfilk pada Foreign Key Product key '" + p_pkey + "' pada tabel Order", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void ubahProduct() throws SQLException, ClassNotFoundException{
        String p_pkey = PKeyText.getText();
        String p_c = CategoryText.getText();
        String p_sc = SCText.getText();
        String p_pname = PNameText.getText();
        String p_i = InfoText.getText();
        String p_price = PriceText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "update Product set Category = ?, SubCategory = ?, PName = ?, Information = ?, Price = ? where PKey = ?");
            statement.setString(6, p_pkey);
            statement.setString(1, p_c);
            statement.setString(2, p_sc);
            statement.setString(3, p_pname);
            statement.setString(4, p_i);
            statement.setString(5, p_price);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    }

    private void tambahOrder() throws SQLException, ClassNotFoundException{
        String o_son = SONText.getText();
        String o_pkey = PKey2Text.getText();
        String o_oq = OQText.getText();
        String o_dis = DisText.getText();
        String o_ship = ShipText.getText();
        String o_op = OPText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "insert into Orders values(?, ?, ?, ?, ?, ?)");
            statement.setString(1, o_son);
            statement.setString(2, o_pkey);
            statement.setString(3, o_oq);
            statement.setString(4, o_dis);
            statement.setString(5, o_ship);
            statement.setString(6, o_op);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 2627){
                JOptionPane.showMessageDialog(null, "Tidak bisa memasukkan data, Sales Order Number '" + o_son + "' sudah ada pada database", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void hapusOrder() throws SQLException, ClassNotFoundException{
        String o_son = SONText.getText();
        String o_pkey = PKey2Text.getText();
        String o_oq = OQText.getText();
        String o_dis = DisText.getText();
        String o_ship = ShipText.getText();
        String o_op = OPText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "delete from Orders where SalesOrderNumber = ? and Pkey = ? and OrderQuantity = ? and Discount = ? and ShippingCost = ? and OrderPriority = ? ");
            statement.setString(1, o_son);
            statement.setString(2, o_pkey);
            statement.setString(3, o_oq);
            statement.setString(4, o_dis);
            statement.setString(5, o_ship);
            statement.setString(6, o_op);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void ubahOrder() throws SQLException, ClassNotFoundException{
        String o_son = SONText.getText();
        String o_pkey = PKey2Text.getText();
        String o_oq = OQText.getText();
        String o_dis = DisText.getText();
        String o_ship = ShipText.getText();
        String o_op = OPText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "update Orders set OrderQuantity = ?, Discount = ?, ShippingCost = ?, OrderPriority = ? where SalesOrderNumber = ? and Pkey = ?");
            statement.setString(5, o_son);
            statement.setString(6, o_pkey);
            statement.setString(1, o_oq);
            statement.setString(2, o_dis);
            statement.setString(3, o_ship);
            statement.setString(4, o_op);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void tambahTransaction() throws SQLException, ClassNotFoundException{
        String t_son = SON2Text.getText();
        String t_ckey = CKey2Text.getText();
        String t_od = ODText.getText();
        String t_dd = DDText.getText();
        String t_sm = SMText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "insert into Transactions values(?, ?, ?, ?, ?)");
            statement.setString(1, t_son);
            statement.setString(2, t_ckey);
            statement.setString(3, t_od);
            statement.setString(4, t_dd);
            statement.setString(5, t_sm);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 2627){
                JOptionPane.showMessageDialog(null, "Tidak bisa memasukkan data, Sales Order Number '" + t_son + "' sudah ada pada database", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void hapusTransaction() throws SQLException, ClassNotFoundException{
        String t_son = SON2Text.getText();
        String t_ckey = CKey2Text.getText();
        String t_od = ODText.getText();
        String t_dd = DDText.getText();
        String t_sm = SMText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "delete from Transactions where SalesOrderNumber = ? and CKey = ? and OrderDate = ? and DeliveryDate = ? and ShipMode = ? ");
            statement.setString(1, t_son);
            statement.setString(2, t_ckey);
            statement.setString(3, t_od);
            statement.setString(4, t_dd);
            statement.setString(5, t_sm);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (SQLException e){
            int errorCode = e.getErrorCode();
            System.out.println(errorCode);

            if (errorCode == 547){
                JOptionPane.showMessageDialog(null, "Tidak bisa menghapus data, Terdapat konfilk pada Foreign Key Sales Order Number '" + t_son + "' pada tabel Order", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void ubahTransaction() throws SQLException, ClassNotFoundException{
        String t_son = SON2Text.getText();
        String t_ckey = CKey2Text.getText();
        String t_od = ODText.getText();
        String t_dd = DDText.getText();
        String t_sm = SMText.getText();
        
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)){
            PreparedStatement statement = conn.prepareStatement(
                "update Transactions set CKey = ?, OrderDate = ?, DeliveryDate = ?, ShipMode = ? where SalesOrderNumber = ?");
            statement.setString(5, t_son);
            statement.setString(1, t_ckey);
            statement.setString(2, t_od);
            statement.setString(3, t_dd);
            statement.setString(4, t_sm);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted);
        } catch (Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }
    } 

    private void loadCustomerTable() throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel User 
            DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();
            model.setRowCount(0);
            String query = "SELECT * FROM Customer";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String CKey = resultSet.getString("CKey");
                String CName = resultSet.getString("CName");
                String States = resultSet.getString("States");
                String Region = resultSet.getString("Region");
                String Country = resultSet.getString("Country");
                String Market = resultSet.getString("Market");
                String BusinessType = resultSet.getString("BusinessType");
                model.addRow(new Object[]{CKey, CName, States, Region, Country, Market, BusinessType});
            }
        }
    }

    private void cariCustomer(String cs_input, String cs_inputType) throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel User 
            DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();
            model.setRowCount(0);
            String sqlInput = cs_inputType;
            switch (cs_inputType) {
                case "Customer Key":
                    sqlInput = "CKey";
                    break;
                case "Customer Name":
                    sqlInput = "CName";
                    break;
                case "Business Type":
                    sqlInput = "BusinessType";
                    break;
                default:
                    break;
            }
            String query = "SELECT * FROM Customer where " + sqlInput + " like '%" + cs_input + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String CKey = resultSet.getString("CKey");
                String CName = resultSet.getString("CName");
                String States = resultSet.getString("States");
                String Region = resultSet.getString("Region");
                String Country = resultSet.getString("Country");
                String Market = resultSet.getString("Market");
                String BusinessType = resultSet.getString("BusinessType");
                model.addRow(new Object[]{CKey, CName, States, Region, Country, Market, BusinessType});
            }
        }
    }

    private void loadProductTable() throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel Product
            DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
            model.setRowCount(0);
            String query = "SELECT * FROM Product";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String PKey = resultSet.getString("PKey");
                String Category = resultSet.getString("Category");
                String SubCategory = resultSet.getString("SubCategory");
                String PName = resultSet.getString("PName");
                String Information = resultSet.getString("Information");
                String Price = resultSet.getString("Price");
                model.addRow(new Object[]{PKey, Category, SubCategory, PName, Information, Price});
            }
        }
    }
    
    private void cariProduct(String ps_input, String ps_inputType) throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel Product
            DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
            model.setRowCount(0);
            String sqlInput = ps_inputType;
            switch (ps_inputType) {
                case "Product Key":
                    sqlInput = "PKey";
                    break;
                case "Product Name":
                    sqlInput = "PName";
                    break;
                default:
                    break;
            }
            String query = "SELECT * FROM Product where " + sqlInput + " like '%" + ps_input + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String PKey = resultSet.getString("PKey");
                String Category = resultSet.getString("Category");
                String SubCategory = resultSet.getString("SubCategory");
                String PName = resultSet.getString("PName");
                String Information = resultSet.getString("Information");
                String Price = resultSet.getString("Price");
                model.addRow(new Object[]{PKey, Category, SubCategory, PName, Information, Price});
            }
        }
    }

    private void loadOrderTable() throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilikan Tabel Orders
            DefaultTableModel model = (DefaultTableModel) OrderTable.getModel();
            model.setRowCount(0);
            String query = "SELECT * FROM Orders";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String SON = resultSet.getString("SalesOrderNumber");
                String PKey = resultSet.getString("PKey");
                int oq = resultSet.getInt("OrderQuantity");
                double dis = resultSet.getDouble("Discount");
                double sc = resultSet.getDouble("ShippingCost");
                String op = resultSet.getString("OrderPriority");
                model.addRow(new Object[]{SON, PKey, oq, dis, sc, op});
            }
        }
    }

    private void cariOrder(String os_input, String os_inputType) throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilikan Tabel Orders
            DefaultTableModel model = (DefaultTableModel) OrderTable.getModel();
            model.setRowCount(0);
            String sqlInput = os_inputType;
            switch (os_inputType) {
                case "Sales Order Number":
                    sqlInput = "SalesOrderNumber";
                    break;
                case "Product Key":
                    sqlInput = "PKey";
                    break;
                case "Order Quantity":
                    sqlInput = "OrderQuantity";
                    break;
                case "Shipping Cost":
                    sqlInput = "ShippingCost";
                    break;
                case "Order Priority":
                    sqlInput = "Order Priority";
                    break;
                default:
                    break;
            }
            String query = "SELECT * FROM Orders where " + sqlInput + " like '%" + os_input + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String SON = resultSet.getString("SalesOrderNumber");
                String PKey = resultSet.getString("PKey");
                int oq = resultSet.getInt("OrderQuantity");
                double dis = resultSet.getDouble("Discount");
                double sc = resultSet.getDouble("ShippingCost");
                String op = resultSet.getString("OrderPriority");
                model.addRow(new Object[]{SON, PKey, oq, dis, sc, op});
            }
        }
    }

    private void loadTransactionTable() throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel Transactions 
            DefaultTableModel model = (DefaultTableModel) TransactionTable.getModel();
            model.setRowCount(0);
            String query = "SELECT * FROM Transactions";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String SON = resultSet.getString("SalesOrderNumber");
                String CKey = resultSet.getString("CKey");
                Date od = resultSet.getDate("OrderDate");
                Date dd = resultSet.getDate("DeliveryDate");
                String sm = resultSet.getString("ShipMode");
                model.addRow(new Object[]{SON, CKey, od, dd, sm});
            }
        }
    }

    private void cariTransaction(String ts_input, String ts_inputType) throws SQLException, ClassNotFoundException{
        try (Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword)) {
            //Tampilkan Tabel Transactions 
            DefaultTableModel model = (DefaultTableModel) TransactionTable.getModel();
            model.setRowCount(0);
            String sqlInput = ts_inputType;
            switch (ts_inputType) {
                case "Sales Order Number":
                    sqlInput = "SalesOrderNumber";
                    break;
                case "Customer Key":
                    sqlInput = "CKey";
                    break;
                case "Order Date":
                    sqlInput = "OrderDate";
                    break;
                case "Delivery Date":
                    sqlInput = "DeliveryDate";
                    break;
                case "Shipping Mode":
                    sqlInput = "ShipMode";
                    break;
                default:
                    break;
            }
            String query = "SELECT * FROM Transactions where " + sqlInput + " like '%" + ts_input + "%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String SON = resultSet.getString("SalesOrderNumber");
                String CKey = resultSet.getString("CKey");
                Date od = resultSet.getDate("OrderDate");
                Date dd = resultSet.getDate("DeliveryDate");
                String sm = resultSet.getString("ShipMode");
                model.addRow(new Object[]{SON, CKey, od, dd, sm});
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        CorePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ProductPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        ProductInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PKeyText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CategoryText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SCText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        InfoText = new javax.swing.JTextField();
        PNameText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        PriceText = new javax.swing.JTextField();
        p_search = new javax.swing.JButton();
        p_stype = new javax.swing.JComboBox<>();
        OrderPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        OrderTable = new javax.swing.JTable();
        OrderInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ShipText = new javax.swing.JTextField();
        DisText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        OPText = new javax.swing.JTextField();
        SONText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        PKey2Text = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        OQText = new javax.swing.JTextField();
        o_search = new javax.swing.JButton();
        o_stype = new javax.swing.JComboBox<>();
        TransactionPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TransactionTable = new javax.swing.JTable();
        TransactionInput = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        SON2Text = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CKey2Text = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ODText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        SMText = new javax.swing.JTextField();
        DDText = new javax.swing.JTextField();
        t_search = new javax.swing.JButton();
        t_stype = new javax.swing.JComboBox<>();
        CustomerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();
        CustomerInput = new javax.swing.JTextField();
        CustomerLabel = new javax.swing.JLabel();
        CustomerText = new javax.swing.JTextField();
        CNameLabel = new javax.swing.JLabel();
        CNameText = new javax.swing.JTextField();
        StateLabel = new javax.swing.JLabel();
        StateText = new javax.swing.JTextField();
        RegionLabel = new javax.swing.JLabel();
        RegionText = new javax.swing.JTextField();
        CountryLabel = new javax.swing.JLabel();
        CountryText = new javax.swing.JTextField();
        MarketLabel = new javax.swing.JLabel();
        MarketText = new javax.swing.JTextField();
        BTLabel = new javax.swing.JLabel();
        BTText = new javax.swing.JTextField();
        c_search = new javax.swing.JButton();
        c_stype = new javax.swing.JComboBox<>();
        CustomerButton = new javax.swing.JButton();
        ProductButton = new javax.swing.JButton();
        OrderButton = new javax.swing.JButton();
        TransactionButton = new javax.swing.JButton();
        tombolTambah = new javax.swing.JButton();
        tombolUbah = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();

        jLabel14.setText("jLabel14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CorePanel.setBackground(new java.awt.Color(255, 255, 255));
        CorePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 0, 36)); // NOI18N
        jLabel1.setText("Sistem Kelola Penjualan Manufaktur");

        jPanel1.setLayout(new java.awt.CardLayout());
        cardLayout = (CardLayout) jPanel1.getLayout();

        ProductPanel.setBackground(new java.awt.Color(255, 102, 102));

        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductKey", "Category", "SubCategory", "ProductName", "Information", "Price"
            }
        ));
        jScrollPane2.setViewportView(ProductTable);

        ProductInput.setText("");
        ProductInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductInputActionPerformed(evt);
            }
        });

        jLabel2.setText("Product Key");

        jLabel3.setText("Category");

        jLabel4.setText("SubCategory");

        SCText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCTextActionPerformed(evt);
            }
        });

        jLabel5.setText("Product Name");

        jLabel6.setText("Information");

        InfoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoTextActionPerformed(evt);
            }
        });

        jLabel7.setText("Price");

        p_search.setText("Cari");

        p_stype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Key", "Category", "SubCategory", "Product Name", "Information", "Price" }));

        javax.swing.GroupLayout ProductPanelLayout = new javax.swing.GroupLayout(ProductPanel);
        ProductPanel.setLayout(ProductPanelLayout);
        ProductPanelLayout.setHorizontalGroup(
            ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addComponent(p_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProductInput, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_search)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(ProductPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SCText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CategoryText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PriceText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InfoText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(PNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        ProductPanelLayout.setVerticalGroup(
            ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_search)
                    .addComponent(p_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(PKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(CategoryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SCText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(PNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(InfoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(214, Short.MAX_VALUE))
        );

        jPanel1.add(ProductPanel, "ProductPanel");

        OrderPanel.setBackground(new java.awt.Color(255, 102, 102));

        OrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SalesOrderNumber", "ProductKey", "OrderQuantity", "Discount", "ShippingCost", "OrderPriority"
            }
        ));
        jScrollPane3.setViewportView(OrderTable);

        OrderInput.setText("");
        OrderInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderInputActionPerformed(evt);
            }
        });

        jLabel8.setText("Discount");

        jLabel9.setText("ShippingCost");

        ShipText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShipTextActionPerformed(evt);
            }
        });

        DisText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisTextActionPerformed(evt);
            }
        });

        jLabel10.setText("OrderPriority");

        jLabel11.setText("SON");

        jLabel12.setText("Product Key");

        jLabel13.setText("OrderQuantity");

        OQText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OQTextActionPerformed(evt);
            }
        });

        o_search.setText("Cari");
        o_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_searchActionPerformed(evt);
            }
        });

        o_stype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales Order Number", "Product Key", "Order Quantitiy", "Discount", "Shipping Cost", "Order Priority" }));

        javax.swing.GroupLayout OrderPanelLayout = new javax.swing.GroupLayout(OrderPanel);
        OrderPanel.setLayout(OrderPanelLayout);
        OrderPanelLayout.setHorizontalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(o_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OrderInput, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(o_search)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OQText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PKey2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SONText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OPText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ShipText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DisText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        OrderPanelLayout.setVerticalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OrderInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o_search)
                    .addComponent(o_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(SONText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(PKey2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OQText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(DisText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(ShipText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jPanel1.add(OrderPanel, "OrderPanel");

        TransactionPanel.setBackground(new java.awt.Color(255, 102, 102));

        TransactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SalesOrderNumber", "CustomerKey", "OrderDate", "DeliveryDate", "ShipMode"
            }
        ));
        jScrollPane4.setViewportView(TransactionTable);

        TransactionInput.setText("");
        TransactionInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionInputActionPerformed(evt);
            }
        });

        jLabel15.setText("SON");

        jLabel16.setText("Customer Key");

        jLabel17.setText("Order Date");

        jLabel18.setText("Delivery Date");

        ODText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODTextActionPerformed(evt);
            }
        });

        jLabel19.setText("ShipMode");

        SMText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMTextActionPerformed(evt);
            }
        });

        DDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DDTextActionPerformed(evt);
            }
        });

        t_search.setText("Cari");

        t_stype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales Order Number", "Customer Key", "Order Date", "Delivery Date", "Shipping Mode" }));

        javax.swing.GroupLayout TransactionPanelLayout = new javax.swing.GroupLayout(TransactionPanel);
        TransactionPanel.setLayout(TransactionPanelLayout);
        TransactionPanelLayout.setHorizontalGroup(
            TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addComponent(t_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(TransactionInput, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_search)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(TransactionPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ODText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CKey2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SON2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SMText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DDText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        TransactionPanelLayout.setVerticalGroup(
            TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TransactionInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_search)
                    .addComponent(t_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(SON2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(CKey2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ODText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(TransactionPanelLayout.createSequentialGroup()
                        .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(DDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(SMText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jPanel1.add(TransactionPanel, "TransactionPanel");

        CustomerPanel.setBackground(new java.awt.Color(255, 102, 102));
        CustomerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerKey", "CustomerName", "States", "Region", "Country", "Market", "BusinessType"
            }
        ));
        jScrollPane1.setViewportView(CustomerTable);

        CustomerInput.setText("");
        CustomerInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerInputActionPerformed(evt);
            }
        });

        CustomerLabel.setText("Customer Key");

        CNameLabel.setText("Customer Name");

        StateLabel.setText("States");

        RegionLabel.setText("Region");

        CountryLabel.setText("Country");

        MarketLabel.setText("Market");

        MarketText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarketTextActionPerformed(evt);
            }
        });

        BTLabel.setText("Business");

        BTText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTTextActionPerformed(evt);
            }
        });

        c_search.setText("Cari");
        c_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_searchActionPerformed(evt);
            }
        });

        c_stype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer Key", "Customer Name", "States", "Region", "Country", "Market", "Business Type" }));

        javax.swing.GroupLayout CustomerPanelLayout = new javax.swing.GroupLayout(CustomerPanel);
        CustomerPanel.setLayout(CustomerPanelLayout);
        CustomerPanelLayout.setHorizontalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(c_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CustomerInput, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_search)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(StateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StateText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CustomerLabel)
                            .addComponent(CNameLabel))
                        .addGap(26, 26, 26)
                        .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CustomerText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerPanelLayout.createSequentialGroup()
                        .addComponent(RegionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RegionText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(237, 237, 237)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(CountryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CountryText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(MarketLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MarketText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addComponent(BTLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        CustomerPanelLayout.setVerticalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_search)
                    .addComponent(c_stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerLabel)
                    .addComponent(CustomerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountryLabel)
                    .addComponent(CountryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CNameLabel)
                    .addComponent(CNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MarketLabel)
                    .addComponent(MarketText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StateLabel)
                    .addComponent(BTLabel)
                    .addComponent(BTText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegionLabel)
                    .addComponent(RegionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel1.add(CustomerPanel, "CustomerPanel");

        CustomerButton.setText("Customer");
        CustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerButtonActionPerformed(evt);
            }
        });

        ProductButton.setText("Product");
        ProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductButtonActionPerformed(evt);
            }
        });

        OrderButton.setText("Order");
        OrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderButtonActionPerformed(evt);
            }
        });

        TransactionButton.setText("Transaction");
        TransactionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CorePanelLayout = new javax.swing.GroupLayout(CorePanel);
        CorePanel.setLayout(CorePanelLayout);
        CorePanelLayout.setHorizontalGroup(
            CorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorePanelLayout.createSequentialGroup()
                .addGroup(CorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CorePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(CorePanelLayout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(CustomerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProductButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TransactionButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(CorePanelLayout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CorePanelLayout.setVerticalGroup(
            CorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CorePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerButton)
                    .addComponent(ProductButton)
                    .addComponent(OrderButton)
                    .addComponent(TransactionButton))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tombolTambah.setText("Tambah");
        tombolTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolTambahActionPerformed(evt);
            }
        });

        tombolUbah.setText("Ubah");
        tombolUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahActionPerformed(evt);
            }
        });

        tombolHapus.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(tombolTambah)
                .addGap(195, 195, 195)
                .addComponent(tombolUbah)
                .addGap(196, 196, 196)
                .addComponent(tombolHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolTambah)
                    .addComponent(tombolUbah)
                    .addComponent(tombolHapus))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerButtonActionPerformed
        cardLayout.show(jPanel1, "CustomerPanel");
        halamanDibuka = "CustomerPanel";
        try{
            loadCustomerTable();
         } catch (Exception e){
             System.out.println(-1);
         }
    }//GEN-LAST:event_CustomerButtonActionPerformed

    private void ProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductButtonActionPerformed
        cardLayout.show(jPanel1, "ProductPanel");
        halamanDibuka = "ProductPanel";
        try{
            loadProductTable();
         } catch (Exception e){

         }
    }//GEN-LAST:event_ProductButtonActionPerformed

    private void OrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrderButtonActionPerformed
        cardLayout.show(jPanel1, "OrderPanel");
        halamanDibuka = "OrderPanel";
        try{
            loadOrderTable();
         } catch (Exception e){

         }
    }//GEN-LAST:event_OrderButtonActionPerformed

    private void TransactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionButtonActionPerformed
        cardLayout.show(jPanel1, "TransactionPanel");
        halamanDibuka = "TransactionPanel";
        try{
            loadTransactionTable();
         } catch (Exception e){

         }
    }//GEN-LAST:event_TransactionButtonActionPerformed

    private void CustomerInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerInputActionPerformed

    private void MarketTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarketTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarketTextActionPerformed

    private void BTTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTTextActionPerformed

    private void ProductInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductInputActionPerformed

    private void SCTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SCTextActionPerformed

    private void InfoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfoTextActionPerformed

    private void OrderInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrderInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderInputActionPerformed

    private void ShipTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShipTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShipTextActionPerformed

    private void OQTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OQTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OQTextActionPerformed

    private void DisTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisTextActionPerformed

    private void TransactionInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TransactionInputActionPerformed

    private void ODTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ODTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ODTextActionPerformed

    private void SMTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMTextActionPerformed

    private void DDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DDTextActionPerformed

    private void tombolTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tombolTambahActionPerformed

    private void tombolUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tombolUbahActionPerformed

    private void c_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_searchActionPerformed

    private void o_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_o_searchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDBCProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBCProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBCProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBCProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JDBCProject().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCProject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JDBCProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BTLabel;
    private javax.swing.JTextField BTText;
    private javax.swing.JTextField CKey2Text;
    private javax.swing.JLabel CNameLabel;
    private javax.swing.JTextField CNameText;
    private javax.swing.JTextField CategoryText;
    private javax.swing.JPanel CorePanel;
    private javax.swing.JLabel CountryLabel;
    private javax.swing.JTextField CountryText;
    private javax.swing.JButton CustomerButton;
    private javax.swing.JTextField CustomerInput;
    private javax.swing.JLabel CustomerLabel;
    private javax.swing.JPanel CustomerPanel;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JTextField CustomerText;
    private javax.swing.JTextField DDText;
    private javax.swing.JTextField DisText;
    private javax.swing.JTextField InfoText;
    private javax.swing.JLabel MarketLabel;
    private javax.swing.JTextField MarketText;
    private javax.swing.JTextField ODText;
    private javax.swing.JTextField OPText;
    private javax.swing.JTextField OQText;
    private javax.swing.JButton OrderButton;
    private javax.swing.JTextField OrderInput;
    private javax.swing.JPanel OrderPanel;
    private javax.swing.JTable OrderTable;
    private javax.swing.JTextField PKey2Text;
    private javax.swing.JTextField PKeyText;
    private javax.swing.JTextField PNameText;
    private javax.swing.JTextField PriceText;
    private javax.swing.JButton ProductButton;
    private javax.swing.JTextField ProductInput;
    private javax.swing.JPanel ProductPanel;
    private javax.swing.JTable ProductTable;
    private javax.swing.JLabel RegionLabel;
    private javax.swing.JTextField RegionText;
    private javax.swing.JTextField SCText;
    private javax.swing.JTextField SMText;
    private javax.swing.JTextField SON2Text;
    private javax.swing.JTextField SONText;
    private javax.swing.JTextField ShipText;
    private javax.swing.JLabel StateLabel;
    private javax.swing.JTextField StateText;
    private javax.swing.JButton TransactionButton;
    private javax.swing.JTextField TransactionInput;
    private javax.swing.JPanel TransactionPanel;
    private javax.swing.JTable TransactionTable;
    private javax.swing.JButton c_search;
    private javax.swing.JComboBox<String> c_stype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton o_search;
    private javax.swing.JComboBox<String> o_stype;
    private javax.swing.JButton p_search;
    private javax.swing.JComboBox<String> p_stype;
    private javax.swing.JButton t_search;
    private javax.swing.JComboBox<String> t_stype;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolTambah;
    private javax.swing.JButton tombolUbah;
    // End of variables declaration//GEN-END:variables
    private CardLayout cardLayout;

    //Variabel untuk menentukan halaman yang sedang dibuka
    private String halamanDibuka = "ProductPanel"; //"ProductPanel" sebagai halaman pertama yang dibuka 
}

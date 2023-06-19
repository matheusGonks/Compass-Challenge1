package dbAccessEntities;

import entities.*;

import java.sql.*;
import java.util.ArrayList;

public class InventoryDB {

    private final String url;
    private final String username;
    private final String password;
    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement st = null;

    public InventoryDB(){
        this.url = "jdbc:postgresql://localhost:5432/ecommerce";
        this.username = "postgres";
        this.password = "";
    }

    public void connect() throws SQLException{
        connection = DriverManager.getConnection(url, username, password);
    }
    public void addProduct(String name, double price, int quantity){
        try {
            this.connect();
            st = connection.prepareStatement("INSERT INTO product (name, price, quantity) VALUES (?,?,?)");
            st.setString(1, name);
            st.setDouble(2, price);
            st.setInt(3, quantity);

            st.execute();

        }catch (SQLException e){

            e.printStackTrace();

        }finally {
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public int removeProduct(int product_id) throws SQLException{
        try{
            this.connect();
            st = connection.prepareStatement("DELETE FROM product WHERE product_id = ?");
            st.setInt(1, product_id);
            return st.executeUpdate();

        }finally {
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void updateProduct(Product product){
        try{
            this.connect();
            st = this.connection.prepareStatement("UPDATE product SET name = ?, price = ?, quantity = ? WHERE product_id = ?");
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getQuantity());
            st.setInt(4, product.getId());

            st.execute();

        }catch (SQLException e){

            e.printStackTrace();

        }finally{
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public ArrayList retrieveAllProducts() throws SQLException{

        try {
            this.connect();
            st = this.connection.prepareStatement("SELECT * FROM product ORDER BY product_id");
            rs = st.executeQuery();

            ArrayList<Product> allProducts = new ArrayList<>();
            while(rs.next()){

                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                allProducts.add(new Product(id, name, price, quantity));
            }

            return allProducts;

        }catch(SQLException e){

            throw e;

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public Product retrieveOneProduct(int product_id) throws SQLException{
        try {
            this.connect();
            st = connection.prepareStatement("SELECT * FROM product WHERE product_id = ?");
            st.setInt(1, product_id);

            rs = st.executeQuery();
            if(!rs.next()) throw new SQLException("Um produto com este ID não existe");

            int id = rs.getInt("product_id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");

            return new Product(id, name, price, quantity);

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

}
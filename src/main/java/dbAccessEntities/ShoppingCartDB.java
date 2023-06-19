package dbAccessEntities;
import entities.*;

import java.sql.*;
import java.util.ArrayList;

public class ShoppingCartDB{

    private final String url;
    private final String username;
    private final String password;
    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement st = null;

    public ShoppingCartDB(){
        this.url = "jdbc:postgresql://localhost:5432/ecommerce";
        this.username = "postgres";
        this.password = "";
    }

    public void connect() throws SQLException{
        connection = DriverManager.getConnection(url, username, password);
    }


    public int createCart(int clientId) throws SQLException{
        try {
            this.connect();
            st = this.connection.prepareStatement("SELECT * FROM shopping_cart WHERE client_id = ? ");
            st.setInt(1, clientId);
            rs = st.executeQuery();

            if (!rs.next()) {
                st = connection.prepareStatement("INSERT INTO shopping_cart (client_id) VALUES (?)");
                st.setInt(1, clientId);
                st.execute();

                st = connection.prepareStatement("SELECT * FROM shopping_cart WHERE client_id = ?");
                st.setInt(1, clientId);
                rs = st.executeQuery();
                rs.next();
            }

            return rs.getInt("cart_id");

        }catch (SQLException e){

            throw e;

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void addProduct(Product product, int cart_id){
        try {
            this.connect();
            st = this.connection.prepareStatement("SELECT * FROM cart_item WHERE cart_id = ? AND product_id = ?");
            st.setInt(1, cart_id);
            st.setInt(2, product.getId());
            rs = st.executeQuery();


            if(rs.next()){ //se o produto já estiver no carrinho
                int currentQuantityOnCart = rs.getInt("quantity");//pegar a quantidade atual do carrinho
                int newQuantity = currentQuantityOnCart + product.getQuantity(); //soma com a nova quantidade

                st = this.connection.prepareStatement("SELECT * FROM product WHERE product_id = ?");
                st.setInt(1, product.getId());
                rs = st.executeQuery();
                rs.next();
                int currentQuantityOnStock = rs.getInt("quantity");

                st = this.connection.prepareStatement("UPDATE cart_item SET quantity = ? WHERE cart_id = ? AND product_id = ?");
                st.setInt(1, Math.min(newQuantity, currentQuantityOnStock)); //quantidade
                st.setInt(2, cart_id);
                st.setInt(3, product.getId());

                st.execute();

            }else{
                st = this.connection.prepareStatement("INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)");
                st.setInt(1, cart_id);
                st.setInt(2, product.getId());
                st.setInt(3, product.getQuantity());

                st.execute();
            }


        }catch (SQLException e){

            e.printStackTrace();

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public int removeProduct(int productId, int cartId) throws SQLException{
        try {
            this.connect();
            st = this.connection.prepareStatement("DELETE FROM cart_item WHERE cart_id = ? AND product_id =  ?");
            st.setInt(1, cartId);
            st.setInt(2, productId);

            return st.executeUpdate();

        }finally {
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Product> cartContent(int cartId) throws SQLException{
        try{
            this.connect();
            st = this.connection.prepareStatement("SELECT product.product_id, product.name, product.price, cart_item.quantity FROM shopping_cart INNER JOIN cart_item ON shopping_cart.cart_id = cart_item.cart_id INNER JOIN product ON cart_item.product_id = product.product_id WHERE shopping_cart.cart_id = ? ");
            st.setInt(1, cartId);

            rs = st.executeQuery();

            ArrayList<Product> allCartProducts = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                allCartProducts.add(new Product(id, name, price, quantity));
            }

            return allCartProducts;

        }catch (SQLException e){

            throw e;

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void emptyCart(int id){
        try {
            this.connect();
            st = this.connection.prepareStatement("DELETE FROM cart_item WHERE cart_id = ?");
            st.setInt(1, id);
            st.execute();

        }catch (SQLException e){

            e.printStackTrace();

        }finally {
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
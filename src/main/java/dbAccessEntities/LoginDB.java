package dbAccessEntities;
import entities.Client;
import java.sql.*;

public class LoginDB {

    private final String url;
    private final String username;
    private final String password;
    private Connection connection = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public LoginDB(){
        this.url = "jdbc:postgresql://localhost:5432/ecommerce";
        this.username = "postgres";
        this.password = "";
    }

    public void connect() throws SQLException{
        if (connection != null) {
            return;
        }

        connection = DriverManager.getConnection(url, username, password);
    }
    public Client verifyLogin(String name, String email) throws SQLException{

        try{
            this.connect();
            st = connection.prepareStatement("SELECT * FROM client WHERE email = ?");

            st.setString(1, email);
            rs = st.executeQuery();

            if (!rs.next()) { //Se o usuario não existe, criamos ele no banco de dados e depois selecionamos de novo pra obter o id, que é gerado pelo bd
                st = connection.prepareStatement("INSERT INTO client (first_name, email) VALUES  (?,?)");
                st.setString(1, name);
                st.setString(2, email);
                st.execute();

                st = connection.prepareStatement("SELECT * FROM client WHERE email = ?");
                st.setString(1, email);
                rs = st.executeQuery();
                rs.next();
            }

            int id = rs.getInt("client_id");
            return new Client(id, name, email);

        }catch (SQLException e){

            throw e;

        }finally {
            try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { st.close(); } catch (Exception e) { e.printStackTrace(); }
            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

    }

}

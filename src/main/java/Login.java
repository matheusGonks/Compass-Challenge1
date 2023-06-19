import dbAccessEntities.LoginDB;
import entities.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Login{

    private LoginDB loginDB;
    public Login(){
        this.loginDB = new LoginDB();
    }

    public Client login() throws SQLException{

        Scanner input = new Scanner(System.in);

        System.out.println("Qual o seu nome?");
        String name = input.nextLine();

        System.out.println("Qual o seu email?");
        String email = input.nextLine();

        try{
            return loginDB.verifyLogin(name, email);

        }catch (SQLException e){
            throw e;
        }


    }

}
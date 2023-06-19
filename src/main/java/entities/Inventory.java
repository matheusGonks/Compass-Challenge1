package entities;
import java.sql.SQLException;
import java.util.ArrayList;
import dbAccessEntities.*;

public class Inventory{

    private InventoryDB inventoryDB;

    public Inventory(){
        this.inventoryDB = new InventoryDB();
    }
    public Product pickProduct(int product_id) throws SQLException{
        try{
            Product product = inventoryDB.retrieveOneProduct(product_id);
            return product;

        }catch (SQLException e){
            throw e;
        }
    }

    public void displayProducts(){
        try{
            ArrayList<Product> allProducts = inventoryDB.retrieveAllProducts();

            System.out.println("==================== LOJA ====================");
            System.out.printf("%-5s %-20s %-8s %-10s\n", "ID", "Nome", "Preço", "Quantidade");

            for(Product product : allProducts){
                System.out.print(product);
            }
            System.out.println();

        }catch(SQLException e){

            e.printStackTrace();

        }
    }

    public void addNewProductToStock(String name, double price, int quantity){
        inventoryDB.addProduct(name, price, quantity);
    }

    public void removeProductFromStock(int productId){
        try {
            int removedProducts = inventoryDB.removeProduct(productId);
            if(removedProducts != 0) System.out.println("Produto removido!");
            else System.out.println("Produto com este ID não existe.");

        }catch (SQLException e){

            e.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        inventoryDB.updateProduct(product);
    }

}
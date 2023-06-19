package entities;
import dbAccessEntities.ShoppingCartDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingCart{

    private double totalValue;
    private int id;
    private int client_id;
    private ShoppingCartDB shoppingCartDB;

    ShoppingCart(int client_id){
        try{
            this.shoppingCartDB = new ShoppingCartDB();
            this.client_id = client_id;
            this.id = shoppingCartDB.createCart(this.client_id);
            this.totalValue = 0.0;

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void addProduct(Product product){
        shoppingCartDB.addProduct(product, this.id);
    }

    public void removeProduct(int productId){
        try{
            int removedProducts = shoppingCartDB.removeProduct(productId, this.id);
            if(removedProducts != 0) System.out.println("Produto removido!");
            else System.out.println("Produto com este ID não existe.");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void cartContent(){
        try{
            totalValue = 0.0;
            ArrayList<Product> cartProducts = shoppingCartDB.cartContent(this.id);

            System.out.println("=============== SEU CARRINHO =============== ");
            System.out.printf("%-5s %-20s %-8s %-10s\n", "ID", "Nome", "Preço", "Quantidade");

            if (cartProducts.size() == 0) System.out.println("Você ainda não comprou nada!\n");

            for(Product product : cartProducts){
                    totalValue += product.getPrice() * product.getQuantity();
                    System.out.print(product);
            }
            System.out.printf("O preço atual do carrinho é R$%.2f\n\n", totalValue);

        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    public void closeOrder(Inventory inventory){
        try{
            ArrayList<Product> cartProducts = shoppingCartDB.cartContent(this.id);
            for(Product productInCart : cartProducts){
                //pega o produto do inventario e subtrai de sua quantidade em estoque a quantidade comprada
                Product inventoryProduct = inventory.pickProduct(productInCart.getId());

                int currentQuantityOnStock = inventoryProduct.getQuantity();
                int boughtQuantity = productInCart.getQuantity();
                inventoryProduct.setQuantity(currentQuantityOnStock - boughtQuantity);

                inventory.updateProduct(inventoryProduct); //atualiza as quantidade
            }

            this.shoppingCartDB.emptyCart(this.id);

        }catch (SQLException e){

            e.printStackTrace();

        }

    }
}
package entities;

import java.sql.SQLException;

public class Client {

    private int id;
    private String name;
    private String email;
    private ShoppingCart myCart;

    public Client(int id, String name, String email){
        this.name = name;
        this.email = email;
        this.id = id;

        this.myCart = new ShoppingCart(id);
    }

    public void addProductToCart(int productId , int quantity, Inventory inventory){

        try{
            Product productToBeAdded = inventory.pickProduct(productId);
            productToBeAdded.setQuantity(quantity);

            this.myCart.addProduct(productToBeAdded);
            System.out.println("Produto Adicionado!");

        }catch(SQLException e){

            System.out.println(e.getMessage());

        }

    };
    public void removerProductFromCart(int productId){

        this.myCart.removeProduct(productId);
    };

    public void displayCart(){
        this.myCart.cartContent();
    };

    public void closeOrder(Inventory inventory){
        this.myCart.closeOrder(inventory);
        System.out.println("Pedido concluído");
    };

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}
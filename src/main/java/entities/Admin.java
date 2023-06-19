package entities;
import dbAccessEntities.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin{

    private Inventory inventory;

    public Admin(Inventory inventory){
        this.inventory = inventory;
    }

    public void addNewProductToInventory(String name, double price, int quantity){
        inventory.addNewProductToStock(name, price, quantity);
        System.out.println("Produto adicionado!");
    }

    public void removeProductFromInventory(int productId){
        inventory.removeProductFromStock(productId);
    }

    public void updateInventoryProduct(int productId, Scanner input){

        try {
            Product product = inventory.pickProduct(productId);
            System.out.println(product);

            int changeOption;
            do {
                System.out.println("Qual atributo você deseja alterar?");
                System.out.println("1-Nome 2-Preco 3-Quantidade 4-CONFIRMAR");

                changeOption = input.nextInt();
                input.nextLine();

                switch (changeOption) {
                    case 1:
                        System.out.println("Insira um novo nome");
                        String name = input.nextLine();
                        product.setName(name);
                        break;

                    case 2:
                        System.out.println("Qual o novo preço? (deve ser um numero com virgula)");
                        Double price = input.nextDouble();
                        input.nextLine();
                        product.setPrice(price);
                        break;

                    case 3:
                        System.out.println("Qual a nova quantidade? (deve ser um numero inteiro)");
                        int quant = input.nextInt();
                        input.nextLine();
                        product.setQuantity(quant);
                        break;

                    case 4:
                        break;

                    default:
                        System.out.println("Tente outra opcao.");

                }

                System.out.println("Produto agora esta assim :");
                System.out.println(product);

            } while (changeOption != 4);

            inventory.updateProduct(product);

        }catch (SQLException e){

            System.out.println(e.getMessage());

        }
    }
}
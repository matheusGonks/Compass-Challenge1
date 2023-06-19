import java.sql.SQLException;
import java.util.Scanner;
import entities.*;

public class Menu {

    private static int operationOption;

    public static void clientMenu(Inventory inventory, Scanner input) {

        try {
            Client client;
            client = new Login().login();


            do {
                System.out.println("Qual operação você quer fazer?");
                System.out.println("1- Ver produtos disponíveis na loja");
                System.out.println("2- Ver produtos no seu carrinho");
                System.out.println("3- Adicionar um produto no carrinho");
                System.out.println("4- Remover um produto do carrinho");
                System.out.println("5- Concluir compra");
                System.out.println("6-Sair");

                int productId;
                operationOption = input.nextInt();
                input.nextLine();

                switch (operationOption) {
                    case (1):
                        inventory.displayProducts();
                        break;

                    case (2):
                        client.displayCart();
                        break;

                    case (3):
                        System.out.println("Qual o id do produto que você deseja adicionar ao carrinho?");
                        productId = input.nextInt();
                        input.nextLine();

                        System.out.println("Qual a quantidade?");
                        int quant = input.nextInt();
                        input.nextLine();

                        client.addProductToCart(productId, quant, inventory);
                        break;

                    case (4):
                        System.out.println("Qual o id do produto que você deseja remover do carrinho?");
                        productId = input.nextInt();
                        input.nextLine();
                        client.removerProductFromCart(productId);
                        break;

                    case(5):
                        System.out.println("Tem certeza que deseja concluir a compra?");
                        System.out.println("Digite 1 para confirmar ou 2 para cancelar");
                        int confirmacao = input.nextInt();

                        if(confirmacao == 1) client.closeOrder(inventory);
                        break;

                    case (6):
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha outra.");
                        break;
                }

            } while (operationOption != 6);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public static void adminMenu(Inventory inventory, Scanner input){

        Admin admin = new Admin(inventory);

        do {
            System.out.println("Qual operação você quer fazer?");
            System.out.println("1- Adicionar produto ao estoque");
            System.out.println("2- Remover um produto do estoque");
            System.out.println("3- Alterar propriedade de algum produto no estoque");
            System.out.println("4- Ver produtos da loja");
            System.out.println("5- Sair");


            int id, quantity;
            String name;
            double price;
            operationOption = input.nextInt();
            input.nextLine();

            switch (operationOption) {
                case (1):
                    System.out.println("Insira o nome do produto");
                    name = input.nextLine();

                    System.out.println("Insira o preço (número com vírgula):");
                    price = input.nextDouble();
                    input.nextLine();

                    System.out.println("Insira a quantidade em estoque (um número inteiro):");
                    quantity = input.nextInt();
                    input.nextLine();

                    admin.addNewProductToInventory(name, price, quantity);
                    break;

                case(2):
                    System.out.println("Qual o id do produto que você deseja remover do estoque?");
                    id = input.nextInt();
                    input.nextLine();
                    admin.removeProductFromInventory(id);
                    break;

                case (3):
                    System.out.println("Qual o id do produto que você deseja alterar?");
                    id = input.nextInt();
                    input.nextLine();
                    admin.updateInventoryProduct(id, input);
                    break;

                case(4):
                    inventory.displayProducts();
                    break;

                case (5): break;

                default:
                    System.out.println("Opção inválida! Escolha outra.");
                    break;
            }

        } while (operationOption != 5);
    }
}
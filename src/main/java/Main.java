import java.util.Scanner;
import entities.*;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int loginOption;

        do {
            System.out.println("Você vai logar como Administrador do Sistema ou como Cliente?");
            System.out.println(String.format("1-Como Administrador  2- Como Cliente 3-Sair"));
            loginOption = input.nextInt();

            switch (loginOption){
                case 1:
                    Menu.adminMenu(inventory, input);
                    break;

                case 2:
                    Menu.clientMenu(inventory, input);
                    break;

                case 3: break;

                default:
                    System.out.println("Só existem essas três opções.");

            }

        }while(loginOption != 3);

        System.out.println("Acesso finalizado.");

    }
}
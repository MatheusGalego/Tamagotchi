
import br.com.teste.model.Tamagotchi;

import java.util.Scanner;

public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    
    // Criação de um objeto Tamagotchi
    Tamagotchi tamagotchi = new Tamagotchi();

    // Loop principal do programa, onde o usuário interage com o Tamagotchi
    while (true) {

        // Exibe as opções de interação com o Tamagotchi
        System.out.println("O que você quer fazer?");
        System.out.println("1 - Status");
        System.out.println("2 - Brincar");
        System.out.println("3 - Alimentar");
        System.out.println("4 - Dormir");
        System.out.println("5 - Dar remédio");
        System.out.println("6 - Dar banho");
        System.out.println("0 - Matar tamachotchi");

        // Obtém a choose do usuário
        int choose = scanner.nextInt();

        // Realiza a ação de acordo com a escolha do usuário
        switch (choose) {
            case 1:
                tamagotchi.status();
                break;
            case 2:
                tamagotchi.play();
                break;
            case 3:
                tamagotchi.alimentar();
                break;
            case 4:
                tamagotchi.sleep();
                break;
            case 5:
                tamagotchi.darRemedio();
                break;
            case 6:
                tamagotchi.darBanho();
            case 0:
                System.out.println("Você matou seu tamagotchi :'(");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
        }

        // Simula a passagem de tempo após a interação do usuário
        tamagotchi.passarTempo();
    }
}

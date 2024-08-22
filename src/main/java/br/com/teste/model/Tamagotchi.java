package br.com.teste.model;

import java.util.Random; // Import para gerar um número aleatório
import java.util.Scanner; // Import para ler o que o usuário digitar no terminal

//Definição da classe Tamagotchi
public class Tamagotchi {

    //Definição dos atributos da classe do Tamagotchi
    private Integer hungry;
    private Integer hapiness;
    private Integer age;
    private Integer healthy;
    private String name;
    private LifeStyle lifeStyle;
    private Integer energy;
    private Humor humor;
    private Integer hygiene;

    //Enumeração para os diferentes estilos de vida do Tamagotchi
    public enum LifeStyle {
        BEBE,
        ADOLESCENTE,
        ADULTO,
        IDOSO
    }

    // Enumeração para diferentes doenças que o Tamagotchi pode ter
    public enum Disease {
        RESFRIADO,
        DOR_DE_ESTÔMAGO,
        FERIDA
    }

    // Enumeração para os diferentes humores do Tamagotchi
    public enum Humor {
        FELIZ,
        TRISTE,
        REVOLTADO,
        CANSADO
    }

    // Atributo que guarda a doença atual do Tamagotchi
    private Disease currentDesease = null;


    // Declaração de objetos Scanner e Random
    static Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Tamagotchi() {


        // Pede o name do Tamagotchi ao usuário
        System.out.println("Digite o name do seu Tamagotchi: ");
        this.name = scanner.nextLine();

        // Inicializa os atributos do Tamagotchi com valores padrão
        hungry = 100;
        hapiness = 100;
        age = 0;
        lifeStyle = lifeStyle.BEBE;
        energy = 100;
        healthy = 100;
        humor = Humor.FELIZ;
        hygiene = 100;
    }

    // Métodos para realizar ações com o Tamagotchi
    public void status() { // 1: STATUS

        System.out.println("Nome: " + name);
        System.out.println("Fome: " + hungry);
        System.out.println("Felicidade: " + hapiness);
        System.out.println("Energia: " + energy);
        System.out.println("Idade: " + age);
        System.out.println("Estilo de Vida: " + lifeStyle);
        System.out.println("Saúde:" + healthy);
    }


    public void play() { // 2: play
        if (hapiness < 90) {
            hapiness += 20;
            hygiene -= 10;
            System.out.println(name + " está brincando muito!");
            setHumor(Humor.FELIZ);
        } else {
            System.out.println(name + " cansou de brincar! Deixa ele descansar");
            setHumor(Humor.CANSADO);
        }
    }

    public void alimentar() { // 3: ALIMENTAR
        if (hungry < 90) {
            hungry += 20;
            setHumor(Humor.FELIZ);
            System.out.println(name + " comeu!");
            setHumor(Humor.FELIZ);
        } else {
            System.out.println(name + " não está com fome.");
            setHumor(Humor.REVOLTADO);
        }
    }

    public void sleep() { // 4: sleep
        if (energy < 90) {
            energy += 20;

            System.out.println(name + " está dormindo!");

            // Diminui a fome e a felicidade mais lentamente enquanto dorme
            for (int i = 0; i < 10; i++) {
                hungry -= 1;
                hapiness -= 1;

                if (hungry <= 0) {
                    System.out.println(name + " morreu de fome! :(");
                    System.exit(0);
                } else if (hapiness <= 0) {
                    System.out.println(name + " morreu de tristeza! :(");
                    System.exit(0);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(name + " acordou!");
        } else {
            System.out.println(name + " já está dormindo.");
        }
    }

    public void darRemedio() { // 5: DAR REMÉDIO

        if (currentDesease == null && healthy < 30) {
            healthy += 40;
            System.out.println(name + " quase ficou doente, mas está se sentindo melhor!");
            setHumor(Humor.FELIZ);
        } else if (currentDesease != null && healthy < 30) {
            healthy += 70;
            System.out.println(name + " não está mais com " + currentDesease + "!");
            currentDesease = null;
            setHumor(Humor.FELIZ);
        } else {

            System.out.println(name + " não precisa de remédio agora.");
        }
    }

    public void darBanho() { // 6: DAR BANHO
        if (hygiene < 50) {
            hygiene += 50;
            System.out.println(name + " tomou um banhozinho e está cheiroso!");
        } else {
            System.out.println(name + " já está limpo!");
        }
    }


    // Método para definir o humor do Tamagotchi
    public void setHumor(Humor newHumor) {
        if (newHumor != humor) {
            System.out.println(name + " está se sentindo " + newHumor + " agora!"); // Printa o humor atual
            humor = newHumor;
        }
    }

    // Método para simular a passagem de tempo e as mudanças no estado do Tamagotchi
    public void passarTempo() {
        hungry -= 5;
        hapiness -= 5;
        energy -= 3;
        healthy -= 5;
        hygiene -= 5;
        age++;


        //Motivos para o Tamagotchi morrer

        if (hungry <= 0) {
            System.out.println(name + " morreu de fome! :(");
            System.exit(0);
        } else if (hapiness <= 0) {
            System.out.println(name + " morreu de tristeza! :(");
            System.exit(0);
        } else if (age >= 100) {
            System.out.println(name + " morreu de velhice! :(");
            System.exit(0);
        } else if (energy <= 0) {
            System.out.println(name + " morreu de exaustão! :(");
        } else if (healthy <= 0) {
            System.out.println(name + " morreu doente! :(");
            System.exit(0);
        }

        // Aumenta a age do Tamagotchi

        if (age >= 10 && lifeStyle == lifeStyle.BEBE) {
            lifeStyle = lifeStyle.ADOLESCENTE;
            System.out.println(name + " está crescendo e agora é um adolescente!");
        } else if (age >= 20 && lifeStyle == lifeStyle.ADOLESCENTE) {
            lifeStyle = lifeStyle.ADULTO;
            System.out.println(name + " está crescendo e agora é um adulto!");
        } else if (age >= 60 && lifeStyle == lifeStyle.ADULTO) {
            lifeStyle = lifeStyle.IDOSO;
            System.out.println(name + " está crescendo e agora é um idoso!");
        }

        // Faz o Tamagotchi ficar doente "aleatoriamente"

        if (healthy < 30 && Math.random() < 0.7) {
            currentDesease = Disease.values()[(int) (Math.random() * Disease.values().length)];
            System.out.println(name + " está doente com " + currentDesease.toString().toLowerCase() + "!");
        }

        //Diminui os status do Tamagotchi de acordo com a doença que ele está

        if (currentDesease != null) {
            switch (currentDesease) {
                case RESFRIADO:
                    hapiness -= 10;
                    energy -= 10;
                    break;

                case DOR_DE_ESTÔMAGO:
                    hungry -= 10;
                    hapiness -= 10;
                    break;

                case FERIDA:
                    energy -= 10;
                    healthy -= 10;
                    break;
            }
        }


        //Define o evento aleatório
        if (random.nextDouble() < 0.3) {
            eventoAleatorio();
        }
    }


    // Método para simular eventos aleatórios que afetam o Tamagotchi
    private void eventoAleatorio() {
        int evento = random.nextInt(3);
        switch (evento) {
            case 0:
                System.out.println("Hoje está um dia chuvoso! " + name + " está com preguiça!");
                energy -= 20;
                break;
            case 1:
                System.out.println("Hoje é aniversário de " + name + " e ele ganhou um presente! Ele ficou feliz!");
                hapiness += 20;
                break;
            case 2:
                System.out.println(name + " caiu numa poça de barro, agora ele está todo sujo!");
        }
    }
}





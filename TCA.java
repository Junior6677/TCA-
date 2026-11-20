import java.util.Random;
import java.util.Scanner;

public class TCA {

    public static Scanner sc = new Scanner(System.in);

    public static void jogar(String palavraSecreta) {

        int erros = 0;
        int maxErros = 8;

        char[] palavra = palavraSecreta.toCharArray();
        // "toCharArray" converte uma String num novo vetor de char
        // O caso acima transforma a variável "palavraSecreta" num vetor de char chamado
        // de "palavra"

        char[] progresso = new char[palavraSecreta.length()];

        for (int i = 0; i < progresso.length; i++) {
            progresso[i] = '_';
        }
        // transforma cada caractere da palavra secreta
        // num travessão para esconde-la

        boolean[] letrasChutadas = new boolean[26];

        while (erros < maxErros) {

            System.out.printf("\nTentativas restantes: %d\n", (maxErros - erros));
            System.out.println(progresso);
            System.out.println("\nDigite uma letra: ");

            String letra = sc.nextLine().trim().toUpperCase();
            // "toUpperCase" converte uma String digitada para maiúsculo

            BibliotecaTCA.limparTela();
            // método da BibliotecaTCA.java

            if (letra.isEmpty()) {
                System.out.println("\nSem resposta");
                continue;
            }
            // verifica se o usúario não respondeu (apertou enter sem uma entrada)

            char chute = letra.charAt(0);
            // "charAt()" retorna um caractere de uma posição específica da entrada
            // O caso acima está retornando um caractere da posição 0 da entrada

            System.out.println("\nVocê chutou: " + letra);

            if (chute < 'A' || chute > 'Z') {
                System.out.printf("%c não é uma letra de A a Z\n", chute);
                continue;
            }
            // verifica se a entrada digitada pelo usuário é uma letra

            if (letrasChutadas[chute - 'A']) {
                System.out.printf("A letra %c já foi digitada\n", chute);
                continue;
            }
            // verifica se a letra chutada é repetida

            letrasChutadas[chute - 'A'] = true;
            // declara que a letra chutada é repetida
            

            boolean acertou = false;

            for (int j = 0; j < palavra.length; j++) {
                if (chute == palavra[j]) {
                    progresso[j] = palavra[j];
                    acertou = true;
                }
            }
            // verifica se acertou o chute

            if (acertou) {
                System.out.println("Parabéns, você acertou uma letra!!");
            } else {
                System.out.printf("A letra %c não está na palavra secreta\n", chute);
                erros++;
            }

            boolean ganhou = true;

            for (int k = 0; k < progresso.length; k++) {
                if (progresso[k] == '_') {
                    ganhou = false;
                }
            }
            // verifica no progresso (palavra secreta escondida) se há algum travessão
            // pois se não há mais nenhum travessão, significa que a palavra secreta foi
            // descoberta

            if (ganhou) {
                BibliotecaTCA.limparTela();
                System.out.println(progresso);
                System.out.println("\nParabéns, você descobriu a palavra secreta!!");
                BibliotecaTCA.esperar(5000);
                break;
            }
        }
        if (erros == maxErros) {
            BibliotecaTCA.limparTela();
            System.out.println("\nBoa tentativa, não foi dessa vez");
            System.out.println("A palavra secreta era: " + palavraSecreta);
            BibliotecaTCA.esperar(6000);
        }
    }

    public static void main(String[] args) {

        String[] opcoes = { "MODO NORMAL", "MODO PERSONALIZADO", "EVIL HANGMAN" };

        while (true) {

            int escolha = BibliotecaTCA.escolherNoMenu("JOGO DA PHORKA", opcoes);
            BibliotecaTCA.limparTela();

            switch (escolha) {
                case 0:
                    System.out.println("\n\nModo Escolhido: NORMAL");
                    BibliotecaTCA.esperar(2000);

                    System.out.println("\nVamos lá!! Descubra a palavra secreta");

                    String[] palavras = { "JAVA", "CODIGO", "COMPUTADOR", "UBUNTU", "TERMINAL", "WINDOWS" };

                    Random sorteio = new Random();
                    int posicao = sorteio.nextInt(palavras.length);

                    String palavraSecreta = palavras[posicao];

                    jogar(palavraSecreta);
                    break;
                    // OPÇÃO 0 (MODO NORMAL)
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                case 1:
                    System.out.println("\n\nModo Escolhido: PERSONALIZADO");
                    BibliotecaTCA.esperar(2000);

                    System.out.println("\nVamos Lá!! Digite sua palavra secreta abaixo:");
                    String palavraDoUsuario = sc.nextLine().trim().toUpperCase();
                    BibliotecaTCA.limparTela();
                    
                    jogar(palavraDoUsuario);
                    break;
                    // OPÇÃO 1 (MODO PERSONALIZADO)
                    ///////////////////////////////////////////////////////////////////////////////////////////////

                case 2:
                    System.out.println("\nModo Escolhido: EVIL HANGMAN");
                    BibliotecaTCA.esperar(2000);

                    System.out.println("\nVamos lá!! Descubra a palavra secreta");

                    // OPÇÃO 2 (EVIL HANGMAN)
                    ///////////////////////////////////////////////////////////////////////////////////////////////

            } // SWITCH

        } // WHILE

    } // MAIN

} // CLASS

import java.util.Random;
import java.util.Scanner;

public class TCA {

    public static Scanner sc = new Scanner(System.in);

    public static final String[][] BONECO = {
        // Estágio 0 (0 erros - Inteiro)
        {
            "    ___    ",
            "  _[___]_  ",
            "   (o_o)   ",
            "  /( : )\\  ",
            "  (  :  )  ",
            "~~~~~~~~~~~"
        },
        // Estágio 1 (1 erro - Perdeu o chapéu)
        {
            "           ",
            "           ",
            "   (o_o)   ",
            "  /( : )\\  ",
            "  (  :  )  ",
            "~~~~~~~~~~~"
        },
        // Estágio 2 (2 erros - Perdeu os braços)
        {
            "           ",
            "           ",
            "   (o_o)   ",
            "   ( : )   ",
            "  (  :  )  ",
            "~~~~~~~~~~~"
        },
        // Estágio 3 (3 erros - Base derretendo)
        {
            "           ",
            "           ",
            "   (o_o)   ",
            "   ( : )   ",
            "  (_____)  ",
            "~~~~~~~~~~~"
        },
        // Estágio 4 (4 erros - Só a cabeça)
        {
            "           ",
            "           ",
            "           ",
            "   (o_o)   ",
            "  (_____)  ",
            "~~~~~~~~~~~"
        },
        // Estágio 5 (5 erros - Quase uma poça)
        {
            "           ",
            "           ",
            "           ",
            "           ",
            "   (o_o)   ",
            "~~~~~~~~~~~"
        },
        // Estágio 6 (6 erros - Derreteu)
        {
            "           ",
            "           ",
            "           ",
            "           ",
            "  _..o.._  ",
            "~~~~~~~~~~~"
        },
        // Estágio 7 (7 erros - Apenas uma bolinha)
        {
            "           ",
            "           ",
            "           ",
            "           ",
            "     o     ",
            "~~~~~~~~~~~"
        },
        // Estágio 8 (8 erros - Fim de jogo)
        {
            "           ",
            "           ",
            "YOU LOSE :(",
            "           ",
            "           ",
            "~~~~~~~~~~~"
        }
    };
    // Matriz em que cada linha do estágio é uma String, e que o índice do estágio é o número de erros

    public static void mostrarBoneco(int erros) {
        for (int j = 0; j < BONECO[erros].length; j++) {
            System.out.println(BONECO[erros][j]);
        }
    }
    // "BONECO[erros]" é o estágio atual, o laço imprime as 6 linhas dele

    public static void jogar(String palavraSecreta) {

        int erros = 0;
        int maxErros = 8;

        char[] palavra = palavraSecreta.toCharArray();
        // "toCharArray" converte uma String num novo vetor de char
        // O caso acima transforma a variável "palavraSecreta" num vetor de char chamado de "palavra"

        char[] progresso = new char[palavraSecreta.length()];
        // "progresso" será a palavra escondida em travessões

        for (int i = 0; i < progresso.length; i++) {
            progresso[i] = '_';
        }
        // transforma o progresso nos travessões

        boolean[] letrasChutadas = new boolean[26];

        while (erros < maxErros) {

            System.out.printf("\nTentativas restantes: %d\n", (maxErros - erros));
            mostrarBoneco(erros);
            System.out.println(progresso);
            System.out.println("\nDigite uma letra: ");

            String letra = sc.nextLine().trim().toUpperCase();
            // "toUpperCase" converte uma String digitada para maiúsculo
            // "trim" ignora os espaços em branco ao lado da String. Ex.: "a "

            BibliotecaTCA.limparTela();
            // método da BibliotecaTCA.java

            if (letra.isEmpty()) {
                System.out.println("\nSem resposta");
                continue;
            }
            // verifica se o usuário não respondeu (deu enter sem uma entrada)

            char chute = letra.charAt(0);
            // "charAt" retorna um caractere de uma posição específica da entrada
            // O caso acima está retornando um caractere da posição 0 da entrada

            System.out.println("\nVocê chutou: " + letra);

            if (chute < 'A' || chute > 'Z') {
                System.out.printf("%c não é uma letra de A a Z\n", chute);
                continue;
            }
            // É usado como referência os valores dos caracteres 'A' e 'Z' em decimal
            // 'A' = 65 em decimal; 'Z' = 90 em decimal
            // Acima ou abaixo disso são outros caracteres

            if (letrasChutadas[chute - 'A']) {
                System.out.printf("A letra %c já foi digitada\n", chute);
                continue;
            }
            // Usa como referência o 'A' pois é o caractere inicial

            letrasChutadas[chute - 'A'] = true;
            // marca a letra como já chutada, para bloquear um segundo chute igual

            boolean acertou = false;

            // verifica se acertou o chute:
            for (int j = 0; j < palavra.length; j++) {
                if (chute == palavra[j]) {
                    progresso[j] = palavra[j];
                    acertou = true;
                }
            }

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
            // o for acima verifica no progresso (palavra secreta escondida) se há algum travessão
            // a palavra é descoberta quando não sobra nenhum travessão no progresso

            if (ganhou) {
                BibliotecaTCA.limparTela();
                mostrarBoneco(erros);
                System.out.println("");
                System.out.println(progresso);
                System.out.println("\nParabéns, você descobriu a palavra secreta!!");
                BibliotecaTCA.esperar(5500);
                break;
            }
        }
        if (erros == maxErros) {
            BibliotecaTCA.limparTela();
            mostrarBoneco(erros);
            System.out.println("\nBoa tentativa, não foi dessa vez");
            System.out.println("A palavra secreta era: " + palavraSecreta);
            BibliotecaTCA.esperar(5500);
        }
    }

    public static String[] lerTema(String arquivo) {

        String texto = BibliotecaTCA.lerDoArquivo(arquivo);

        if (texto == null) {
            return null;
        }

        String[] palavras = texto.trim().split("\n");
        return palavras;
    }
    // "split("\n")" transforma em vetor (sugestão de BibliotecaTCA.java, linha 585) 
    // "null" indica arquivo ausente

    public static void main(String[] args) {

        BibliotecaTCA.esperar(1000);
        System.out.println("\nJogo feito por:\n\nJunior Felipe L. dos Anjos\nRaphael Victor D. Eleoteri");
        BibliotecaTCA.esperar(4000);

        String[] opcoes = {"MODO NORMAL", "MODO PERSONALIZADO"};

        while (true) {

            int escolha = BibliotecaTCA.escolherNoMenu("JOGO DA PHORKA", opcoes);
            BibliotecaTCA.limparTela();

            if (escolha == -1) {
                // ESC foi pressionado: encerra o programa
                System.out.println("Obrigado por jogar :)\n");
                break;
            }

            switch (escolha) {
                case 0:
                    System.out.println("\nModo Escolhido: NORMAL");
                    BibliotecaTCA.esperar(2000);

                    System.out.println("\nEscolha seu tema: \n0 => tema padrão \n1 => verbos \n2 => nomes \n3 => adjetivos \n4 => estados e capitais\n");

                    int lista = sc.nextInt();
                    sc.nextLine();
                    // O "nextLine" acima consome o Enter que ficou no buffer depois do número

                    String[] palavras;

                    switch (lista) {

                        case 0:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nTEMA ESCOLHIDO: PADRÃO");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaPadrao.txt");
                            break;

                        case 1:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nTEMA ESCOLHIDO: VERBOS");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaVerbos.txt");
                            break;

                        case 2:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nTEMA ESCOLHIDO: NOMES");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaNomes.txt");
                            break;

                        case 3:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nTEMA ESCOLHIDO: ADJETIVOS");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaAdjetivos.txt"); 
                            break;

                        case 4:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nTEMA ESCOLHIDO: ESTADOS E CAPITAIS");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaEstadosCapitais.txt");
                            break;

                        default:

                            BibliotecaTCA.limparTela();
                            System.out.println("\nNÚMERO ESCOLHIDO NÃO EXISTE, TEMA PADRÃO ESCOLHIDO");
                            BibliotecaTCA.esperar(2000);
                            System.out.println("\nVamos Lá!! Descubra a palavra secreta");
                            palavras = lerTema("temaPadrao.txt");
                            break;

                    }//SWITCH (TEMAS)

                    if (palavras == null) {
                        System.out.println("\nNão foi possível carregar o tema.");
                        BibliotecaTCA.esperar(3000);
                        break;
                    }

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

                    System.out.println("\nVamos Lá!! Digite sua palavra secreta abaixo:\n(OBS.: Use apenas letras de A a Z, sem espaços, sem acentos/outros caracteres, não deixe em branco)\n");
                    
                    // O valor inicial existe só para a variável ser declarada antes do while, e o laço sempre a substitui
                    String palavraDoUsuario = "";
                    boolean valida = false;

                    while (!valida) {

                        palavraDoUsuario = sc.nextLine().trim().toUpperCase();
                        // "toUpperCase" converte uma String digitada para maiúsculo
                        // "trim" ignora os espaços em branco ao lado da String. Ex.: "a "

                        BibliotecaTCA.limparTela();
                        valida = true;

                        for (int i = 0; i < palavraDoUsuario.length(); i++) {

                            char c = palavraDoUsuario.charAt(i);

                            if (c < 'A' || c > 'Z') {
                                valida = false;
                                break;
                            }
                        }

                        if (palavraDoUsuario.isEmpty()) {
                            valida = false;
                        }
                        if (!valida) {
                            System.out.println("\nA palavra digitada é inválida, por favor digite novamente:\n");
                            BibliotecaTCA.esperar(3000);
                        }   
                    }
                    //Este while repete até a palavra ser válida, ou seja: quando não há acentos/outros caracteres

                    jogar(palavraDoUsuario);
                    break;

                // OPÇÃO 1 (MODO PERSONALIZADO)
                ///////////////////////////////////////////////////////////////////////////////////////////////

            } // SWITCH (OPÇÕES NO MENU)

        } // WHILE

    } // MAIN

} // CLASS

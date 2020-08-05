import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Principal {
    public static void main(String args[]){


        // construindo a arraylist de caracteres utilizados para a criptografia
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.,;!?";
        ArrayList<Character> alfabeto = new ArrayList<Character>();
        for (char c : str.toCharArray()) {
            alfabeto.add(c);
        }

        // chamando o método que lê o arquivo txt, decifra o código e printa no console a mensagem descriptografada
        decifraCodigo("SecretMessage.txt",alfabeto);




    }

    public static void decifraCodigo(String nomeArquivo, ArrayList<Character> alfabeto) { // método que decifra o código
        String linhas[] = new String[10];
        int numLinhas = 0;

        Path path1 = Paths.get(nomeArquivo); // localiza o arquivo que será lido

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                linhas[numLinhas] = line;
                numLinhas++;
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        for (int j = 1; j <= numLinhas; j++) {

            String linha = linhas[j - 1]; // string com a linha de texto completa


            // obs: a key foi descoberta testando valores em um loop em uma parte do txt até encontrar uma mensagem com significiado
            int key = 19;
            String textoDecifrado = "";



            for (int i = 0; i < linha.length(); i++) { //loop que percorre cada caractere da linha de texto criptografa
                Character letraOriginal; // variável para guardar a letra da mensagem original
                Character ch = linha.charAt(i); // pegando o caractere
                int posicaoNaTabela = alfabeto.indexOf(ch); // pegando a posição utilizada para criptografar o caractere
                int posicaoDaLetraOriginal = posicaoNaTabela - key; // pegando a posição à qual foi acrescida a key para criptografar, é a posição que me serve para descobrir o caractere "de verdade"

                // ifs utilizados para percorrer a tabela de letras/números caso na hora de criptografar a posição utilizada tenha sido maior que 30

                if (posicaoDaLetraOriginal == -1) posicaoDaLetraOriginal = 30;
                if (posicaoDaLetraOriginal == -2) posicaoDaLetraOriginal = 29;
                if (posicaoDaLetraOriginal == -3) posicaoDaLetraOriginal = 28;
                if (posicaoDaLetraOriginal == -4) posicaoDaLetraOriginal = 27;
                if (posicaoDaLetraOriginal == -5) posicaoDaLetraOriginal = 26;
                if (posicaoDaLetraOriginal == -6) posicaoDaLetraOriginal = 25;
                if (posicaoDaLetraOriginal == -7) posicaoDaLetraOriginal = 24;
                if (posicaoDaLetraOriginal == -8) posicaoDaLetraOriginal = 23;
                if (posicaoDaLetraOriginal == -9) posicaoDaLetraOriginal = 22;
                if (posicaoDaLetraOriginal == -10) posicaoDaLetraOriginal = 21;
                if (posicaoDaLetraOriginal == -11) posicaoDaLetraOriginal = 20;
                if (posicaoDaLetraOriginal == -12) posicaoDaLetraOriginal = 19;
                if (posicaoDaLetraOriginal == -13) posicaoDaLetraOriginal = 18;
                if (posicaoDaLetraOriginal == -14) posicaoDaLetraOriginal = 17;
                if (posicaoDaLetraOriginal == -15) posicaoDaLetraOriginal = 16;
                if (posicaoDaLetraOriginal == -16) posicaoDaLetraOriginal = 15;
                if (posicaoDaLetraOriginal == -17) posicaoDaLetraOriginal = 14;
                if (posicaoDaLetraOriginal == -18) posicaoDaLetraOriginal = 13;
                if (posicaoDaLetraOriginal == -19) posicaoDaLetraOriginal = 12;
                if (posicaoDaLetraOriginal == -20) posicaoDaLetraOriginal = 11;
                if (posicaoDaLetraOriginal == -21) posicaoDaLetraOriginal = 10;
                if (posicaoDaLetraOriginal == -22) posicaoDaLetraOriginal = 9;
                if (posicaoDaLetraOriginal == -23) posicaoDaLetraOriginal = 8;
                if (posicaoDaLetraOriginal == -24) posicaoDaLetraOriginal = 7;
                if (posicaoDaLetraOriginal == -25) posicaoDaLetraOriginal = 6;
                if (posicaoDaLetraOriginal == -26) posicaoDaLetraOriginal = 5;
                if (posicaoDaLetraOriginal == -27) posicaoDaLetraOriginal = 4;
                if (posicaoDaLetraOriginal == -28) posicaoDaLetraOriginal = 3;
                if (posicaoDaLetraOriginal == -29) posicaoDaLetraOriginal = 2;
                if (posicaoDaLetraOriginal == -30) posicaoDaLetraOriginal = 1;
                if (posicaoDaLetraOriginal == -31) posicaoDaLetraOriginal = 0;

                if (posicaoDaLetraOriginal <= 30) {
                    if (ch == '#') letraOriginal = ' ';
                    else if (ch == 'S') letraOriginal = '\''; // if utilizado para que seja colocado um apóstrofo no lugar de '?', já que não há apóstrofo na tabela e ele é utilizado na palavra "don't"
                    else {
                        letraOriginal = alfabeto.get(posicaoDaLetraOriginal);
                    }
                    textoDecifrado = textoDecifrado + letraOriginal; // construção da string com o texto decifrado, adicionando caractere a caractere

                }


            }

            System.out.println(textoDecifrado);
        }
    }
}

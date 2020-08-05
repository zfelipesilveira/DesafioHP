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


                // verifica se a posição da letra original é menor que zero || se for, soma-se ao tamanho do alfabeto para percorrê-lo de "forma inversa"
                // ex: se a posição obtida for -1, o código abaixo corrige para 30, que é a posição que foi utilizada no início da criptografia
                if(posicaoDaLetraOriginal<0){
                    posicaoDaLetraOriginal = posicaoDaLetraOriginal + 31;

                }


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

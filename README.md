# Desafio de criptografia da LIS

Inicialmente, tive um pouco de dificuldade para entender bem o enunciado, então enviei um e-mail para tirar minhas dúvidas.
Depois de receber a resposta, consegui entender bem e desenvolver o código. 

A linguagem que utilizei é a linguagem com a qual eu tenho maior experiência e intimidade: *Java*.

Para rodar o programa, basta ir na pasta src e depois ir na classe Principal. Na classe principal há o main que roda o programa.

O código no geral é divido em **duas partes**: o método *main* que executa o programa e um método *static* que é chamado no main para de fato decifrar a mensagem.

O método *main* do código é bem simples:

Primeiramente, criei uma lista de arranjo com todas as letras do alfabeto da tabela:
```
String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.,;!?";
ArrayList<Character> alfabeto = new ArrayList<Character>();
for (char c : str.toCharArray()) {
    alfabeto.add(c);
}
```

Depois, fiz apenas a chamada do método que contém a parte mais relevante do código, que de fato faz a descriptação da mensagem. Esse método lê o arquivo com a mensagem, decifra a mensagem e depois printa no console:

```
decifraCodigo("SecretMessage.txt",alfabeto);
```

No código do método que de fato decifra a mensagem criptografada, divido as linhas do texto em Strings que serão percorridas caractere a caractere para fazer a substituição para o caractere original.
Para a leitura do arquivo, utilizei a classe *BufferedReader* de *Java*:

```
String linhas[] = new String[100];
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
```


Nesta última parte, utilizei um for para percorrer as duas *Strings* que foram obtidas através do arquivo txt.
Depois, utilizei um for para percorrer cada caractere da *String* com o texto criptografado.
Para cada caractere criptografado, eu verifico sua posição na arraylist com o alfabeto e depois subtraio utilizando o valor da key, para chegar a **uma nova posição que será a posição que vai indicar o caractere da mensagem original.**
Caso o valor dê negativo, adiciono ao valor o número 31, que é o tamanho do alfabeto, para que a posição seja "corrigida" no processo de percorrer o alfabeto do último caractere ao primeiro

Por fim, há um trecho de código que constrói a *String* com o texto decifrado em cada iteração do *loop*. Depois o texto é printado na tela:
```
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
```






package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.ResourcesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Representa a fonte de caracteres de entrada, de onde o Analisador Léxico
 * {@link Scanner} deve ler.
 * <p>
 * O construtor desta classe garante que os objetos sejam inicializados
 * em um estado de pronto-processamento. Isso quer dizer que {@link #getCurrentChar()}
 * retorna dados válidos desde a primeira chamada.
 */
public class Source {

    /**
     * Representação interna do caractere "fim-de-arquivo"
     */
    public static final char EOF = (char) 0;

    /**
     * Leitor dos dados de entrada
     */
    private BufferedReader reader;

    /**
     * Mantém informação sobre a linha que está sendo lida.
     */
    private long currentLine;
    /**
     * Mantém informação de qual posição, dentro da linha atual, o último caractere lido ocupa.
     */
    private long currentColumn;
    /**
     * Mantém informação de qual posição, globalmente, o último caractere lido ocupa.
     */
    private long currentPosition;

    /**
     * Mantém o último caractere lido
     */
    private char currentChar;

    /**
     * Constrói uma fonte de caracteres.
     * <p>
     * Inicialmente, nenhum caractere é lido.
     *
     * @param input o fluxo de dados de entrada.
     * @throws IOException Caso a inicialização ou a leitura do fluxo falhe.
     */
    public Source(InputStream input) throws IOException {
        this.currentLine = 1;
        this.currentColumn = 0;
        this.currentPosition = 0;

        this.reader = new BufferedReader(new InputStreamReader(input));

        ResourcesManager.manage(this.reader);
        advance();
    }

    /**
     * Avança a leitura, extraindo o próximo caractere da entrada.
     *
     * @return Caractere lido ou {@link #EOF} caso a entrada tenha acabado.
     * @throws IOException Caso a leitura falhe.
     */
    public char advance() throws IOException {
        final int nextChar = reader.read();

        if (nextChar > 0) {
            this.currentChar = (char) nextChar;

            ++this.currentPosition;

            if (nextChar == '\n') {
                ++this.currentLine;
                this.currentColumn = 0;
            } else {
                ++this.currentColumn;
            }
        } else {
            this.currentChar = EOF;
        }

        return this.currentChar;
    }

    /**
     * Obtém o último caractere lido.
     *
     * @return O último caractere lido.
     */
    public char getCurrentChar() {
        return this.currentChar;
    }

    /**
     * Obtém o número de linha do último caractere lido.
     *
     * @return Linha do último caractere lido.
     */
    public long getCurrentLine() {
        return this.currentLine;
    }

    /**
     * Obtém a posição relativa à linha do último caractere lido.
     *
     * @return Posição relativa à linha do último caractere lido.
     */
    public long getCurrentColumn() {
        return currentColumn;
    }

    /**
     * Obtém a posição relativa à entrada do último caractere lido.
     *
     * @return Posição relativa à entrada do último caractere lido.
     */
    public long getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Encerra a leitura e fecha a entrada.
     *
     * @throws IOException caso ocorra um erro ao fechar a entrada.
     */
    public void close() throws IOException {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
}

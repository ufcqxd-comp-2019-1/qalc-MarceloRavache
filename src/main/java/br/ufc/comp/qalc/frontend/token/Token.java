package br.ufc.comp.qalc.frontend.token;

/**
 * Classe abstrata representando as informações de tokens
 * reconhecidos pela Análise Léxica.
 *
 * @see br.ufc.comp.qalc.frontend.Scanner
 */
public abstract class Token {
    /**
     * Correspondência do token no fluxo de entrada (lexema).
     */
    protected String stringValue;

    /**
     * Posição relativa ao início da linha onde o lexema inicia.
     */
    protected long columnStart;
    /**
     * Posição relativa ao início da linha onde o lexema termina.
     */
    protected long columnEnd;
    /**
     * Linha onde ocorre o lexema.
     */
    protected long lineNum;

    /**
     * Constrói um token a partir de informações de posição e do lexema.
     * <p>
     * Para tokens que aceitam mais de um lexema, dado seu padrão.
     *
     * @param line  Linha onde ocorre o lexema.
     * @param start Coluna onde inicia o lexema.
     * @param value O próprio lexema associado ao token.
     * @throws IllegalArgumentException Caso o lexema informado seja vazio.
     */
    protected Token(long line, long start, String value) throws IllegalArgumentException {
        if (value == null || value.length() == 0)
            throw new IllegalArgumentException("O lexema não pode ser vazio.");

        columnStart = start;
        columnEnd = start + value.length() - 1;
        lineNum = line;

        stringValue = value;
    }

    /**
     * Constrói um token a partir de informações de posição.
     * <p>
     * Para tokens que só possuem um lexema válido.
     *
     * @param line  Linha onde ocorre o lexema.
     * @param start Coluna onde inicia o lexema.
     * @param end   Coluna onde termina o lexema.
     */
    protected Token(long line, long start, long end) {
        columnStart = start;
        columnEnd = end;
        lineNum = line;

        stringValue = null;
    }

    /**
     * Obter lexema associado ao token.
     * <p>
     * <strong>ATENÇÃO</strong>:
     * <ul>
     *     <li><em>Deve retornar lexema, mesmo que este não esteja armazenado!</em></li>
     * </ul>
     *
     * @return Lexema associado ao token.
     */
    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Obter número da linha onde se encontra o lexema deste token.
     * Valores iniciam de {@code 1}.
     *
     * @return Número da linha do lexema.
     */
    public long getLineNumber() {
        return lineNum;
    }

    /**
     * Obter coluna onde inicia o lexema deste token, relativo ao início da linha.
     * Valores iniciam de {@code 1}.
     *
     * @return Coluna onde inicia o lexema.
     */
    public long getColumnStart() {
        return columnStart;
    }

    /**
     * Obter coluna onde termina o lexema deste token, relativo ao início da linha.
     * Valores iniciam de {@code 1}.
     *
     * @return Coluna onde termina o lexema.
     */
    public long getColumnEnd() {
        return columnEnd;
    }

    /**
     * Método responsável por descartar o lexema, convertendo-o à representação
     * que será usada na manipulação do token e preenchendo os demais atributos
     * que dele advêm.
     * <p>
     * Por exemplo, para tokens numéricos, converte o lexema em um número.
     */
    public void interpretAttributes() {
        return;
    }

    /**
     * Deve retornar a {@link String} representando o identificador do token,
     * segundo as convenções de nomenclatura definidas na especificação
     * da linguagem.
     * <p>
     * IMPORTANTE: <em>Não incluir os parênteses!</em>
     * <p>
     * EXEMPLO: <del>(PLUS)</del> => <ins>"PLUS"</ins>.
     *
     * @return identificador do token.
     */
    public abstract String getTokenIdentifier();
}

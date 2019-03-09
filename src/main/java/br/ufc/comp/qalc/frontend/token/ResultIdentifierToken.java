package br.ufc.comp.qalc.frontend.token;

/**
 * Classe que representa um token do tipo (RESID).
 */
public class ResultIdentifierToken extends Token {
    /**
     * Número correspondente ao que o lexema deste token representa.
     * <p>
     * Só é inicializado quando solicitado.
     *
     * @see #interpretAttributes()
     */
    protected long resultNumber;

    public ResultIdentifierToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Para este tipo de token, descarta o {@code $} e converte o lexema em um número do tipo
     * {@code double}, caso não tenha feito ainda.
     *
     * @see Token#interpretAttributes()
     */
    @Override
    public void interpretAttributes() {
        // TODO Se o lexema ainda existir, desconsiderar o `$` e interpretar o resto como um long,
        //      atribuindo ao campo `resultNumber`.
    }

    /**
     * Obtém a posição de resultado associado ao token.
     *
     * @return Posição de resultado associado.
     */
    public long getResultNumber() {
        interpretAttributes();

        return resultNumber;
    }

    @Override
    public String getTokenIdentifier() {
        return "RESID";
    }

}

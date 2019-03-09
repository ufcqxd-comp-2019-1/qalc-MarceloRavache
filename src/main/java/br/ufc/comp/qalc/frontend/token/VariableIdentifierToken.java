package br.ufc.comp.qalc.frontend.token;

/**
 * Classe que representa um token do tipo (VARID).
 */
public class VariableIdentifierToken extends Token {

    public VariableIdentifierToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Para este tipo de token, descarta o {@code $} do lexema, caso não tenha sido descartado ainda.
     *
     * @see Token#interpretAttributes()
     */
    @Override
    public void interpretAttributes() {
        // TODO Se o lexema ainda existir e ainda não tiver sido interpretado, descartar o `$`.
    }

    /**
     * Obtém o identificador associado ao token.
     *
     * @return Identificador de variável associado.
     */
    public String getVariableIdentifier() {
        interpretAttributes();

        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        return "VARID";
    }

}

package br.ufc.comp.qalc.frontend.token;

public class RParenToken extends Token{
    public RParenToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "RPAREN";}
}

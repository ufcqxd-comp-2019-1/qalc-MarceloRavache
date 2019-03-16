package br.ufc.comp.qalc.frontend.token;

public class LParenToken extends Token{
    public LParenToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "LPAREN";}
}

package br.ufc.comp.qalc.frontend.token;

public class MinusToken extends Token{
    public MinusToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "MINUS";}
}

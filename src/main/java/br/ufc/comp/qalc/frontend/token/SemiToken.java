package br.ufc.comp.qalc.frontend.token;

public class SemiToken extends Token{
    public SemiToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "SEMI";}
}
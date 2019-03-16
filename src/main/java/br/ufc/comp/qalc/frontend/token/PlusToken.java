package br.ufc.comp.qalc.frontend.token;

public class PlusToken extends Token{
    public PlusToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "PLUS";}
}

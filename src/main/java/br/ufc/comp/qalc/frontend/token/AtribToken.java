package br.ufc.comp.qalc.frontend.token;

public class AtribToken extends Token{
    public AtribToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "ATRIB";}
}

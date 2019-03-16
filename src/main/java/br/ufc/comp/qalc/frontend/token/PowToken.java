package br.ufc.comp.qalc.frontend.token;

public class PowToken extends Token{
    public PowToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "POW";}
}

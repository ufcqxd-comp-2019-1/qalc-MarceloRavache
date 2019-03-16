package br.ufc.comp.qalc.frontend.token;

public class ComToken extends Token{
    public ComToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "COM";}
}

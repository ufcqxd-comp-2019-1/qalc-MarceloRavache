package br.ufc.comp.qalc.frontend.token;

public class ErrorToken extends Token{
    public ErrorToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "ERROR";}
}

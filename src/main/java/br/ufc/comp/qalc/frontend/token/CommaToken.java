package br.ufc.comp.qalc.frontend.token;

public class CommaToken extends Token{
    public CommaToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "COMMA";}
}

package br.ufc.comp.qalc.frontend.token;

public class ModToken extends Token{
    public ModToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "MOD";}
}

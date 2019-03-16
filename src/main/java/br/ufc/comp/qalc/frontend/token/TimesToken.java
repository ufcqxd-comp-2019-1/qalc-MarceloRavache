package br.ufc.comp.qalc.frontend.token;

public class TimesToken extends Token{
    public TimesToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }
    public String getTokenIdentifier(){return "TIMES";}
}

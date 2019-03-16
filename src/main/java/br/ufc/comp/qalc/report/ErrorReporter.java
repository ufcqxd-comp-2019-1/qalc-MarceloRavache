package br.ufc.comp.qalc.report;

import br.ufc.comp.qalc.OutputVerbosity;
import br.ufc.comp.qalc.report.messages.Message;
import br.ufc.comp.qalc.report.messages.MessageCategory;

import java.io.OutputStream;

/**
 * Classe especializada no relato de mensagens de erro.
 *
 * @see MessageCategory#ERROR
 */
public class ErrorReporter extends BasicReporter {
    /**
     * @see BasicReporter#BasicReporter(OutputStream)
     */
    public ErrorReporter(OutputStream stream) {
        super(stream);
    }

    /**
     * @see BasicReporter#BasicReporter(OutputStream, OutputVerbosity)
     */
    public ErrorReporter(OutputStream stream, OutputVerbosity verbosity) {
        super(stream, verbosity);
    }

    @Override
    public void consume(Message message) {
        // TODO
        System.out.println("Error Lexer!");
    }
}

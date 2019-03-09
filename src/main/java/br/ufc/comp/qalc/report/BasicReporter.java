package br.ufc.comp.qalc.report;

import br.ufc.comp.qalc.OutputVerbosity;
import br.ufc.comp.qalc.ResourcesManager;
import br.ufc.comp.qalc.report.messages.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Classe base para todos as que funcionam como relatores das mensagens
 * recebidas via {@link MessageCenter}.
 * <p>
 * Contém funcionalidade básica e define um comportamento inicial.
 * Subclasses que desejam personalizar o texto exibido pelo consumo
 * de uma mensagem devem sobrescrever o método {@link #consume(Message)}.
 */
public class BasicReporter implements MessageConsumer {

    protected OutputStreamWriter output;
    protected OutputVerbosity verbosity;

    /**
     * Cria um relator cujas mensagens serão direcionadas à {@code stream} informada,
     * com nível de verbosidade mínimo.
     *
     * @param stream Fluxo de saída a ser usado.
     */
    public BasicReporter(OutputStream stream) {
        this(stream, OutputVerbosity.ESSENTIAL);
    }

    /**
     * Cria um relator cujas mensagens serão direcionadas à {@code stream} informada,
     * com nível de verbosidade informado.
     *
     * @param stream    Fluxo de saída a ser usado.
     * @param verbosity Nível de verbosidade na saída gerada.
     */
    public BasicReporter(OutputStream stream, OutputVerbosity verbosity) {
        this.output = new OutputStreamWriter(stream);
        this.verbosity = verbosity;

        ResourcesManager.manage(this.output);
    }

    /**
     * Comportamento padrão do consumo de mensagens.
     * <p>
     * Redireciona à saída ({@link #output}) informações básicas sobre o
     * objeto {@code message} recebido.
     *
     * @param message A mensagem a consumir.
     */
    @Override
    public void consume(Message message) {
        try {
            output.write(String.format(
                    "[%s](%s): %s\n",
                    message.getCategory(),
                    message.getClass().getSimpleName(),
                    message.getContent()
            ));
        } catch (IOException e) {
            reportFailure(e);
        }
    }

    /**
     * Método a ser executado quando uma exceção for encontrada durante
     * o processo de relato de uma mensagem.
     *
     * @param e Exceção encontrada.
     */
    protected void reportFailure(IOException e) {
        System.err.println("Erro ao reportar mensagem.");
        e.printStackTrace();
    }
}

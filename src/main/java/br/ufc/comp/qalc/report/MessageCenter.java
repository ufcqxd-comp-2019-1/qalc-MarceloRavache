package br.ufc.comp.qalc.report;

import br.ufc.comp.qalc.report.messages.Message;
import br.ufc.comp.qalc.report.messages.MessageCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Funciona como intermediário entre produtores de mensagens e consumidores.
 * <p>
 * Tem como responsabilidade viabilizar a delegação de responsabilidade de
 * relato de eventos ocorridos no sistema a outras classes através de
 * objetos {@link Message}.
 */
public final class MessageCenter {
    /**
     * Construtor privado para impedir que um objeto desta classe
     * seja instanciado.
     */
    private MessageCenter() {
    }

    /**
     * Armazena todos os consumidores registrados, categorizados pelo
     * tipo de mensagem que eles alegam poder processar.
     */
    private static HashMap<MessageCategory, List<MessageConsumer>> mapping;

    static {
        mapping = new HashMap<>();
    }

    /**
     * Método a ser chamado para registrar um relator capaz de processar mensagens
     * de um tipo específico.
     *
     * @param category Categoria de mensagem que o consumidor pode processar.
     * @param consumer Consumidor a ser registrado.
     */
    public static void registerConsumerFor(MessageCategory category, MessageConsumer consumer) {
        List<MessageConsumer> list = mapping.get(category);

        if (list == null) {
            list = new ArrayList<>();
            list.add(consumer);
            mapping.put(category, list);
        } else {
            list.add(consumer);
        }
    }

    /**
     * Método a ser chamado para solicitar o envio de uma mensagem a todos
     * os consumidores registrados capazes de consumi-la.
     *
     * @param message Mensagem a ser enviada.
     */
    public static void deliver(Message message) {
        List<MessageConsumer> list = mapping.get(message.getCategory());

        if(list != null) {
            for (MessageConsumer consumer : list) {
                consumer.consume(message);
            }
        }
    }
}

package br.ufc.comp.qalc.report.misc;

/**
 * Contém métodos para auxiliar na padronização da saída dos relatores.
 */
public class OutputFormatter {
    /**
     * Recebe os componentes de uma mensagem do tipo {@link br.ufc.comp.qalc.report.messages.MessageCategory#NOTE}
     * e constrói o corpo de uma mensagem desse tipo a ser enviada para a saída.
     *
     * @param header     Texto principal da saída.
     * @param additional Informações adicionais a serem exibidas.
     * @return String contendo o corpo da mensagem construída através dos parâmetros, que pode ser enviado à saída.
     */
    public static String noteMessage(String header, String... additional) {
        StringBuilder builder = new StringBuilder("[*] NOTA: ");

        return makeMessage(header, builder, additional).toString();
    }

    /**
     * Recebe os componentes de uma mensagem do tipo {@link br.ufc.comp.qalc.report.messages.MessageCategory#WARNING}
     * e constrói o corpo de uma mensagem desse tipo a ser enviada para a saída.
     *
     * @param header     Texto principal da saída.
     * @param additional Informações adicionais a serem exibidas.
     * @return String contendo o corpo da mensagem construída através dos parâmetros, que pode ser enviado à saída.
     */
    public static String warningMessage(String header, String... additional) {
        StringBuilder builder = new StringBuilder("[@] AVISO: ");

        return makeMessage(header, builder, additional).toString();
    }

    /**
     * Recebe os componentes de uma mensagem do tipo {@link br.ufc.comp.qalc.report.messages.MessageCategory#ERROR}
     * e constrói o corpo de uma mensagem desse tipo a ser enviada para a saída.
     *
     * @param header     Texto principal da saída.
     * @param additional Informações adicionais a serem exibidas.
     * @return String contendo o corpo da mensagem construída através dos parâmetros, que pode ser enviado à saída.
     */
    public static String errorMessage(String header, String... additional) {
        StringBuilder builder = new StringBuilder("[!] ERRO: ");

        return makeMessage(header, builder, additional).toString();
    }

    /**
     * Complementa o conteúdo no {@link StringBuilder} informado com as partes da mensagem.
     *
     * @param header     Texto principal da mensagem.
     * @param builder    Onde inserir a mensagem construída.
     * @param additional Informações adicionais a serem inseridas.
     * @return {@link java.lang.StringBuilder} alterado com a mensagem.
     */
    protected static StringBuilder makeMessage(String header, StringBuilder builder, String[] additional) {
        builder.append(header);

        if (additional != null) {
            for (String info : additional) {
                builder.append(System.lineSeparator());
                builder.append("\t :: ");
                builder.append(info);
            }
        }
        builder.append(System.lineSeparator());

        return builder;
    }

    /**
     * Devolve uma representação da String informada com os caracteres de quebra de linha e de tabulação
     * substituídos por versões visíveis.
     *
     * @param input Entrada a substituir.
     * @return Entrada com quebras de linha e tabulações visíveis.
     */
    public static String replaceDisplacementCharacters(String input) {
        return input
                .replaceAll("\\R", "\u21B5")
                .replaceAll("\\t", "\u21E5");
    }
}

package br.ufc.comp.qalc;

import br.ufc.comp.qalc.report.MessageCenter;
import br.ufc.comp.qalc.report.TokensReporter;
import br.ufc.comp.qalc.report.messages.MessageCategory;
import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Classe principal do interpretador.
 * <p>
 * Contém a função {@link QALC#main(String[])} e as definições de interpretação
 * dos parâmetros de execução.
 */
@CommandLine.Command(
        versionProvider = VersionProvider.class,
        description = "Interpretador para a linguagem QALC.",
        headerHeading = "Uso:%n",
        synopsisHeading = "\t",
        descriptionHeading = "%nDescrição:%n\t",
        parameterListHeading = "%nParâmetros:%n",
        optionListHeading = "%nOpções:%n"
)
public class QALC {

    @CommandLine.Option(
            names = {"-h", "-?", "--help"},
            description = "Exibe este texto de ajuda e sai.",
            usageHelp = true
    )
    boolean helpRequested = false;

    @CommandLine.Option(
            names = {"-v", "--version"},
            description = "Exibe informações de versão e sai.",
            versionHelp = true
    )
    boolean versionRequested = false;

    /**
     * Indica a última fase que o usuário deseja executar no interpretador.
     */
    @CommandLine.Option(
            names = {"-s", "--stop-at"},
            paramLabel = "PHASE",
            description = "Informa a última fase a ser executada na interpretação da entrada." +
                    "\n@|bold Valores válidos:|@ ${COMPLETION-CANDIDATES}." +
                    "\n@|bold Valor padrão:|@ @|underline ${DEFAULT-VALUE}|@."
    )
    InterpreterPass stopAt = InterpreterPass.RUNNER;

    /**
     * Indica o nível de detalhamento a ser usado no arquivo de saída, se solicitado.
     */
    @CommandLine.Option(
            names = {"-o", "--output-verbosity"},
            paramLabel = "VERBOSITY",
            description = "Controla a quantidade de informação a ser incluída no arquivo de saída." +
                    "\n@|bold Valores válidos:|@ ${COMPLETION-CANDIDATES}." +
                    "\n@|bold Valor padrão:|@ @|underline ${DEFAULT-VALUE}|@."
    )
    OutputVerbosity outputVerbosity = OutputVerbosity.ESSENTIAL;

    /**
     * Indica o arquivo de onde ler a entrada.
     * <p>
     * O valor {@code null} significa que a entrada deve ser lida da
     * entrada-padrão ({@code System.out}).
     */
    @CommandLine.Parameters(
            paramLabel = "IN-FILE",
            index = "0",
            arity = "0..1",
            description = "Informa o arquivo a ser usado como entrada. Caso não seja informado, " +
                    "lê da entrada-padrão."
    )
    File readFrom = null;

    @CommandLine.Option(
            names = {"-d", "--dump-to"},
            paramLabel = "OUT-FILE",
            description = "Arquivo onde escrever a saída solicitada. Caso não seja informado, " +
                    "escreve para a saída-padrão."
    )
    File outputTo = null;

    public static void main(String[] args) {
        QALC qalc = new QALC();
        CommandLine cmd = new CommandLine(qalc);

        try {
            // Reconhecimento dos parâmetros de linha de comando.
            cmd.setOverwrittenOptionsAllowed(true);
            cmd.parse(args);
            if (cmd.isUsageHelpRequested()) {
                cmd.usage(System.out);
            } else if (cmd.isVersionHelpRequested()) {
                cmd.printVersionHelp(System.out);
            } else {
                // Alterar esta porção do código
                // ---->

                OutputStream outputToStream = qalc.outputTo == null ? System.out : new FileOutputStream(qalc.outputTo);

                // WARNING: Apenas a última fase deve gerar saída.
                switch (qalc.stopAt) {
                    case LEXER:
                        MessageCenter.registerConsumerFor(
                                MessageCategory.SCANNING,
                                new TokensReporter(outputToStream, qalc.outputVerbosity)
                        );
                        break;
                    case PARSER:
                        // TODO
                        break;
                    case SEMANTIC:
                        // TODO
                        break;
                    case RUNNER:
                        // TODO
                        break;
                }

                // TODO Registrar os relatores de notas, alertas e erros.

                // NOTE: A ordem em que a enumeração foi definida indica a progressão das fases.
                //       Então, comparamos a posição dos valores para saber se a fase que deveríamos executar
                //       está antes da fase em que o usuário deseja parar.
                if (qalc.stopAt.ordinal() >= InterpreterPass.LEXER.ordinal()) {
                    // Fase de Análise Léxica deve ser executada
                    // TODO Executar análise léxica
                }
                // TODO Verificar e executar demais fases

                // TODO Retornar código de erro correspondente às falhas que ocorrerem, via `System.exit(...)`;

                // <----
            }
        } catch (CommandLine.UnmatchedArgumentException ex) {
            System.err.println("[!] No máximo um arquivo deve ser informado como entrada.");
            System.err.println("\tParâmetros ignorados:");

            for (String param : cmd.getUnmatchedArguments()) {
                System.err.println("\t:: " + param);
            }
            System.err.println();

            if (!CommandLine.UnmatchedArgumentException.printSuggestions(ex, System.err)) {
                ex.getCommandLine().usage(System.err);
            }
        } catch (CommandLine.ParameterException ex) {
            System.err.println(ex.getMessage());
            ex.getCommandLine().usage(System.err);
        } catch (FileNotFoundException ex) {
            System.err.println("[!] Não foi possível abrir o arquivo de saída.");
            if (qalc.outputTo != null) {
                if (qalc.outputTo.isDirectory()) {
                    System.err.println("\t:: O arquivo informado é um diretório.");
                } else if (!qalc.outputTo.canWrite()) {
                    System.err.println("\t:: O arquivo informado está protegido contra escrita.");
                } else if (!qalc.outputTo.exists()) {
                    System.err.println("\t:: O arquivo informado não existe e não pôde ser criado.");
                }
            }
        } catch (Exception ex) {
            System.err.println("Não foi possível executar a ação solicitada.");
            ex.printStackTrace();
        } finally {
            ResourcesManager.shutdown(true);
        }
    }
}

package br.ufc.comp.qalc;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Gerencia os recursos do programa que devem ser liberados de forma automatizada
 * ao final da execução.
 * <p>
 * O uso mais útil no momento é o de garantir que a saída dos relatores seja exibida
 * antes que o programa encerre.
 */
public class ResourcesManager {
    /**
     * Todos os objetos que implementem a interface {@link Closeable} que devem ser gerenciados.
     */
    private static Set<Closeable> managedCloseables = new HashSet<>();

    /**
     * Adicionar o recurso ao gerenciador na intenção de ser liberado de forma automatizada.
     * @param resource recurso a ser gerenciado.
     */
    public static void manage(Closeable resource) {
        managedCloseables.add(resource);
    }

    /**
     * Desistir do gerenciamento do recurso informado.
     *
     * @param resource recurso a remover do gerenciamento.
     * @return {@code true} caso removido, {@code false} caso não estava sendo gerenciado.
     */
    public static boolean yield(Closeable resource) {
        return managedCloseables.remove(resource);
    }

    /**
     * Libera todos os recursos sob a gestão deste gerenciador.
     *
     * @param printStackTrace Se as exceções devem ser impressas.
     * @return a quantidade de recursos cuja liberação ocasionou uma exceção.
     */
    public static long shutdown(boolean printStackTrace) {
        long failed = 0;

        for(Closeable resource : managedCloseables) {
            try {
                resource.close();
            } catch (IOException e) {
                ++failed;

                if(printStackTrace) {
                    e.printStackTrace();
                }
            }
        }

        return failed;
    }
}

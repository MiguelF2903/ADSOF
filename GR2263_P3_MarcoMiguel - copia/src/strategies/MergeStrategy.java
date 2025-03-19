package strategies;

import commit.Commit;

/**
 * Representa una estrategia de merge utilizada para resolver conflictos entre commits
 * durante la fusión de ramas en un sistema de control de versiones.
 *
 * Se definen tres estrategias:
 *{STRATEGY_SOURCE}: resuelve los conflictos favoreciendo los cambios del commit fuente.
 *{STRATEGY_TARGET}: resuelve los conflictos favoreciendo los cambios del commit destino.
 *{STRATEGY_NULL}: no se resuelven los conflictos (por defecto, se opta por no resolverlos).
 *
 */
public class MergeStrategy {
    /** Estrategia que favorece los cambios del commit fuente. */
    public static final int STRATEGY_SOURCE = 1;
    /** Estrategia que favorece los cambios del commit destino. */
    public static final int STRATEGY_TARGET = 2;
    /** Estrategia por defecto en la que no se resuelven los conflictos. */
    public static final int STRATEGY_NULL = 3;
    
    /** Tipo de estrategia de merge utilizada. */
    private int type;
    
    /**
     * Construye una nueva estrategia de merge con el tipo especificado.
     *
     * @param type el tipo de estrategia a utilizar
     */
    public MergeStrategy(int type) {
        this.type = type;
    }
    
    /**
     * Resuelve el conflicto entre el commit fuente y el commit destino según la estrategia definida.
     *
     * @param sourceCommit el commit de la rama fuente
     * @param targetCommit el commit de la rama destino
     * @return true si la estrategia resuelve el conflicto favoreciendo el commit fuente,
     *         o false si favorece el commit destino o no aplica resolución
     */
    public boolean resolveConflict(Commit sourceCommit, Commit targetCommit) {
        switch(type) {
            case STRATEGY_SOURCE:
                return true;
            case STRATEGY_TARGET:
                return false;
            case STRATEGY_NULL:
            default:
                return false;
        }
    }
}

package strategies;

import commit.Commit;

public class MergeStrategy {
    public static final int STRATEGY_SOURCE = 1;
    public static final int STRATEGY_TARGET = 2;
    public static final int STRATEGY_NULL = 3;
    
    private int type;
    
    public MergeStrategy(int type) {
        this.type = type;
    }
    
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
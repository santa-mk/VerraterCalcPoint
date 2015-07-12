package jp.mk.santa.verratercalcpoint;

/**
 * Created by santa-mk on 2015/06/21.
 */
public enum LandConflict {
    CONFLICT_0(0),
    CONFLICT_3(3),
    CONFLICT_5(5),
    CONFLICT_7(7),
    CONFLICT_10(10),
    CONFLICT_15(15);

    int conflict;

    LandConflict(int c) {
        conflict = c;
    }

    public int getConflict() {
        return conflict;
    }
}

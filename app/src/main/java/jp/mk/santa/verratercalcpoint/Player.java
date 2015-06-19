package jp.mk.santa.verratercalcpoint;

/**
 * Created by satan-mk on 2015/06/20.
 */
public class Player {
    private Family family;
    private int conflict;

    public Family getFamily() {
        return family;
    }

    public int getConflict() {
        return conflict;
    }

    public void setFamily(Family f) {
        family = f;
    }

    public void setConflict(int c) {
        conflict = c;
    }
}

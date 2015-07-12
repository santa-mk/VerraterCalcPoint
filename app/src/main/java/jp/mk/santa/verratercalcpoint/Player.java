package jp.mk.santa.verratercalcpoint;

import android.widget.ImageView;

/**
 * Created by satan-mk on 2015/06/20.
 */
public class Player {
    private Family family;
    private int conflict;
    private ImageView imageView;

    public Player(Family f, ImageView iv) {
        family = f;
        imageView = iv;
    }

    public Family getFamily() {
        return family;
    }

    public int getConflict() {
        return conflict;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setFamily(Family f) {
        family = f;
        if (f == Family.Eagle) {
            imageView.setImageResource(R.drawable.eagle);
        } else if (f == Family.Rose) {
            imageView.setImageResource(R.drawable.rose);
        }
    }

    public void setConflict(int c) {
        conflict = c;
    }

    public void setImageView(ImageView iv) {
        imageView = iv;
    }

    public void toggleFamily() {
        if (family == Family.Eagle) {
            setFamily(Family.Rose);
        } else {
            setFamily(Family.Eagle);
        }
    }
}

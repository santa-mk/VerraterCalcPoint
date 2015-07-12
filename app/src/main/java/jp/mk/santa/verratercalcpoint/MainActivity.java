package jp.mk.santa.verratercalcpoint;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Player> mPlayers = new ArrayList<Player>();

    final private int mPlayerIds[] = {R.id.player1,
            R.id.player2,
            R.id.player3,
            R.id.player4,
            R.id.player5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        createPlayers();
        setPlayerColor();
        setChangePlayerFamilyListener();
        setSpinnerChangeListener();
        setEditTextListeners();

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPoints();
            }
        });
    }

    private void createPlayers() {
        mPlayers.add(new Player(Family.Eagle, (ImageView)findViewById(R.id.player1).findViewById(R.id.family)));
        mPlayers.add(new Player(Family.Eagle, (ImageView)findViewById(R.id.player2).findViewById(R.id.family)));
        mPlayers.add(new Player(Family.Eagle, (ImageView)findViewById(R.id.player3).findViewById(R.id.family)));
        mPlayers.add(new Player(Family.Eagle, (ImageView)findViewById(R.id.player4).findViewById(R.id.family)));
        mPlayers.add(new Player(Family.Eagle, (ImageView)findViewById(R.id.player5).findViewById(R.id.family)));
    }

    private void setPlayerColor() {
        LinearLayout player = (LinearLayout)findViewById(R.id.player2);
        ImageView image = (ImageView)player.findViewById(R.id.family);
        image.setBackgroundColor(getResources().getColor(R.color.blue));

        player = (LinearLayout)findViewById(R.id.player3);
        image = (ImageView)player.findViewById(R.id.family);
        image.setBackgroundColor(getResources().getColor(R.color.white));

        player = (LinearLayout)findViewById(R.id.player4);
        image = (ImageView)player.findViewById(R.id.family);
        image.setBackgroundColor(getResources().getColor(R.color.orange));

        player = (LinearLayout)findViewById(R.id.player5);
        image = (ImageView)player.findViewById(R.id.family);
        image.setBackgroundColor(getResources().getColor(R.color.brown));
    }

    private void resetPoints() {
        for (int i = 0; i < mPlayerIds.length; i++) {
            LinearLayout player = (LinearLayout)findViewById(mPlayerIds[i]);
            EditText conflict = (EditText)player.findViewById(R.id.player_conflict);
            conflict.setText("0");
        }
    }

    private void setChangePlayerFamilyListener() {
        for (int i = 0; i < mPlayerIds.length; i++) {
            LinearLayout player = (LinearLayout)findViewById(mPlayerIds[i]);
            ImageView image = (ImageView)player.findViewById(R.id.family);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mPlayers.size(); i++) {
                        if (mPlayers.get(i).getImageView() == v) {
                            mPlayers.get(i).toggleFamily();
                        }
                    }
                }
            });
        }
    }

    private void setSpinnerChangeListener() {
        Spinner s = (Spinner)findViewById(R.id.eagle_base);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calcConflict();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s = (Spinner)findViewById(R.id.rose_base);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calcConflict();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setEditTextListeners() {
        for (int i = 0; i < mPlayerIds.length; i++) {
            setEditTextListener((EditText) findViewById(mPlayerIds[i]).findViewById(R.id.player_conflict), i);
        }
    }

    private void setEditTextListener(final EditText edit, final int playerId) {
        edit.setSelectAllOnFocus(true);

        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) v).selectAll();
                }
            }
        });

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String conflict = s.toString();
                if (conflict.equals("")) {
                    conflict = "0";
                }
                mPlayers.get(playerId).setConflict(Integer.parseInt(conflict));
                calcConflict();
            }
        });
    }

    private void calcConflict() {
        int eagleSum = Integer.parseInt(((Spinner) findViewById(R.id.eagle_base)).getSelectedItem().toString());
        int roseSum = Integer.parseInt(((Spinner)findViewById(R.id.rose_base)).getSelectedItem().toString());

        for (int i = 0; i < mPlayers.size(); i++) {
            Player p = mPlayers.get(i);
            if (p.getFamily() == Family.Eagle) {
                eagleSum += p.getConflict();
            } else {
                roseSum += p.getConflict();
            }
        }

        ((TextView)findViewById(R.id.eagle_point)).setText(String.valueOf(eagleSum));
        ((TextView)findViewById(R.id.rose_point)).setText(String.valueOf(roseSum));
    }
}

package jp.mk.santa.verratercalcpoint;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {
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
        setPlayerColor();

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPoints();
            }
        });
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
}

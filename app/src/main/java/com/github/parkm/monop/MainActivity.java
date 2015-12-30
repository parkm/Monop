package com.github.parkm.monop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout playerLayout = (LinearLayout) this.findViewById(R.id.playerLayout);

        Player[] players = {new Player("John"), new Player("Bob")};
        for (Player p : players) {
            playerLayout.addView(this.createPlayerGroupView(p));
        }
    }

    View createPlayerGroupView(final Player player) {
        RelativeLayout rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.player_action_group, null);
        TextView playerNameView = (TextView) rl.findViewById(R.id.playerName);
        final TextView playerMoney = (TextView) rl.findViewById(R.id.playerMoney);
        Button actionButton = (Button) rl.findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), PlayerActionActivity.class);
                i.putExtra("player", player);
                startActivity(i);
            }
        });
        playerNameView.setText(player.name);
        return rl;
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
}

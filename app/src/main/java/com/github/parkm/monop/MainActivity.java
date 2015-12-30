package com.github.parkm.monop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends ActionBarActivity {

    HashMap<Player, View> playerGroupViews = new HashMap<>();
    LinearLayout playerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.playerLayout = (LinearLayout) this.findViewById(R.id.playerLayout);

        Player[] players = {new Player("John"), new Player("Bob"), new Player("Greg")};
        for (Player p : players) Monop.addPlayer(p);
        this.createPlayerGroupViews();
    }

    @Override
    protected void onActivityResult(int requstCode, int resultCode, Intent data) {
        this.createPlayerGroupViews();
    }

    void createPlayerGroupViews() {
        this.playerLayout.removeAllViews();
        this.playerGroupViews.clear();
        for (Player player : Monop.getPlayers()) {
            View view = this.createPlayerGroupView(player);
            this.playerLayout.addView(view);
            this.playerGroupViews.put(player, view);
        }
    }

    View createPlayerGroupView(final Player player) {
        RelativeLayout rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.player_action_group, null);

        Button actionButton = (Button) rl.findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), PlayerActionActivity.class);
                i.putExtra("player", player.getId());
                startActivityForResult(i, 0, null);
            }
        });

        this.updatePlayerGroupView(rl, player);

        return rl;
    }

    void updatePlayerGroupView(View playerLayout, Player p) {
        View view = playerLayout;
        TextView playerName = (TextView) view.findViewById(R.id.playerName);
        playerName.setText(p.name);
        TextView playerMoney = (TextView) view.findViewById(R.id.playerMoney);
        playerMoney.setText(String.valueOf(p.balance));
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
        } else if (id == R.id.action_edit_players) {
            startActivityForResult(new Intent(this.getBaseContext(), EditPlayersActivity.class), 0, null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

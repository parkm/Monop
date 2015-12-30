package com.github.parkm.monop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class PlayerActionActivity extends ActionBarActivity {

    Player activePlayer;
    HashMap<Integer, Player> playerBySpinnerIndex = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_action);

        this.activePlayer = Monop.getPlayer(this.getIntent().getIntExtra("player", -1));

        TextView textView = (TextView) this.findViewById(R.id.playerName);
        textView.setText(this.activePlayer.name);

        final Spinner playerListSpinner = this.addPlayerNamesToSpinner();

        Button submit = (Button) this.findViewById(R.id.submitButton);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player selectedPlayer = playerBySpinnerIndex.get(playerListSpinner.getSelectedItemPosition());
                int money = getIntegerValueForTextView(R.id.moneyAmount);
                activePlayer.balance -= money;
                selectedPlayer.balance += money;
                finish();
            }
        });

        Button addMoneyButton = (Button) this.findViewById(R.id.addMoneyButton);
        addMoneyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                activePlayer.balance += getIntegerValueForTextView(R.id.addMoneyAmount);
                finish();
            }
        });

        Button subMoneyButton = (Button) this.findViewById(R.id.subtractMoneyButton);
        subMoneyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                activePlayer.balance -= getIntegerValueForTextView(R.id.subtractMoneyAmount);
                finish();
            }
        });
    }

    int getIntegerValueForTextView(int id) {
        TextView view = (TextView) findViewById(id);
        return Integer.parseInt(view.getText().toString());
    }

    Spinner addPlayerNamesToSpinner() {
        Spinner playerListSpinner = (Spinner) this.findViewById(R.id.playerListSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerListSpinner.setAdapter(adapter);

        for (Player p : Monop.getPlayers()) {
            if (p.equals(this.activePlayer)) continue;
            playerBySpinnerIndex.put(adapter.getCount(), Monop.getPlayer(p.getId()));
            adapter.add(p.name);
        }

        adapter.notifyDataSetChanged();
        return playerListSpinner;
    }
}

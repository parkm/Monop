package com.github.parkm.monop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerActionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_action);
        Player player = this.getIntent().getParcelableExtra("player");
        TextView textView = (TextView) this.findViewById(R.id.playerName);
        textView.setText(player.name);

        this.addPlayerNamesToSpinner();
    }

    void addPlayerNamesToSpinner() {
        Spinner playerListSpinner = (Spinner) this.findViewById(R.id.playerListSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerListSpinner.setAdapter(adapter);

        adapter.add("TEST");
        adapter.add("TEST2");
        adapter.notifyDataSetChanged();
    }
}

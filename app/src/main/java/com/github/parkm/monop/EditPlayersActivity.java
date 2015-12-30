package com.github.parkm.monop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class EditPlayersActivity extends ActionBarActivity {
    HashMap<ViewGroup, PlayerGroup> playerGroups = new HashMap<>();

    class PlayerGroup {
        public Player player;
        public TextView textView;
        public PlayerGroup(Player player, TextView textView) {
            this.player = player;
            this.textView = textView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_players);

        final ViewGroup playerListGroup = (ViewGroup) this.findViewById(R.id.playerListLayout);

        for (Player p : Monop.getPlayers()) {
            this.createPlayerGroup(playerListGroup, p);
        }

        Button addPlayer = (Button) this.findViewById(R.id.addPlayerButton);
        addPlayer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPlayerGroup(playerListGroup, null);
            }
        });

        Button submitButton = (Button) this.findViewById((R.id.submitButton));
        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Player> toRemove = new ArrayList<Player>(Monop.getPlayers());
                for (PlayerGroup playerGroup : playerGroups.values()) {
                    toRemove.remove(playerGroup.player);
                    String name = playerGroup.textView.getText().toString();
                    if (playerGroup.player == null)
                        Monop.addPlayer(new Player(name));
                    else
                        playerGroup.player.name = name;
                }
                for (Player player : toRemove) Monop.getPlayers().remove(player);
                finish();
            }
        });
    }

    void createPlayerGroup(final ViewGroup root, Player existingPlayer) {
        final LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);

        EditText playerNameInput = new EditText(this);
        playerNameInput.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        if (existingPlayer == null) {
            playerNameInput.setText("Player");
        } else {
            playerNameInput.setText(existingPlayer.name);
        }

        LinearLayout a = new LinearLayout(this);
        Button removePlayerButton = new Button(this);
        removePlayerButton.setText("Remove");
        removePlayerButton.setGravity(Gravity.RIGHT);
        removePlayerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerGroups.remove(container);
                root.removeView(container);
            }
        });

        this.playerGroups.put(container, new PlayerGroup(existingPlayer, playerNameInput));

        container.addView(playerNameInput);
        container.addView(removePlayerButton);

        root.addView(container);
    }

}

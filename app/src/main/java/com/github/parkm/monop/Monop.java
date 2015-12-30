package com.github.parkm.monop;

import java.util.Collection;
import java.util.HashMap;

public class Monop {
    private static int playerIdCounter = 0;
    private static HashMap<Integer, Player> playersById = new HashMap<>();

    public static void addPlayer(Player player) {
        player.setId(Monop.playerIdCounter);
        Monop.playersById.put(player.getId(), player);
        Monop.playerIdCounter++;
    }

    public static Collection<Player> getPlayers() {
        return playersById.values();
    }

    public static Player getPlayer(int id) {
        return playersById.get(id);
    }
}

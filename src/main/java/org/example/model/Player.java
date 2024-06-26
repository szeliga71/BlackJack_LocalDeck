package org.example.model;

public class Player extends House {

    private final String nick;
    private int playerPoints = 0;
    private boolean playerWin;

    public Player(String nick) {
        this.nick = nick;
        playerWin = false;
    }

    public String getNick() {
        return nick;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }
}

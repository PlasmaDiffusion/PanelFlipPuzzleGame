package com.example.a100580683.panelprototype;

/**
 * Created by 100585588 on 12/4/2017.
 */

public class Highscore {
    private long id;
    private int levelNo;
    private int turns;


    Highscore(int levelNo, int turns){
        setLevelNo(levelNo);
        setTurns(turns);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public String toString(){
        return Integer.toString(turns);
    }
}

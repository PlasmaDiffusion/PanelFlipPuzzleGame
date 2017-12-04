package com.example.a100580683.panelprototype;

/**
 * Created by 100585588 on 12/4/2017.
 */

public class Highscore {
    private long id;
    private int levelNo;
    private String name;
    private int turns;


    Highscore(int levelNo, String name, int turns){
        setLevelNo(levelNo);
        setName(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public String toString(){
        return name + " " + turns;
    }
}

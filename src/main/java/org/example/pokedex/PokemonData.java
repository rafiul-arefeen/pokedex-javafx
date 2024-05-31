package org.example.pokedex;

public class PokemonData {
    private int id;
    private String name;
    private String type1;
    private String type2;
    private String total;
    private String hp;
    private String attack;
    private String defense;
    private String sp_attack;
    private String sp_defense;
    private String speed;
    private String generation;

    public String getLegendary() {
        return legendary;
    }

    public void setLegendary(String legendary) {
        this.legendary = legendary;
    }

    private String legendary;
    private String favourite;

    public PokemonData(int id, String name, String type1, String type2, String total, String hp, String attack, String defense, String sp_attack, String sp_defense, String speed, String generation, String legendary, String favourite) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.sp_attack = sp_attack;
        this.sp_defense = sp_defense;
        this.speed = speed;
        this.generation = generation;
        this.legendary = legendary;
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getSp_attack() {
        return sp_attack;
    }

    public void setSp_attack(String sp_attack) {
        this.sp_attack = sp_attack;
    }

    public String getSp_defense() {
        return sp_defense;
    }

    public void setSp_defense(String sp_defense) {
        this.sp_defense = sp_defense;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }
}

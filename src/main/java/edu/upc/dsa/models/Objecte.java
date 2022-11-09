package edu.upc.dsa.models;

public class Objecte { //Enunciado: nombre, descr, precio
    String name;
    String descr;
    int dsaCoins;

    public Objecte(){} //Constructor vacio

    public Objecte(String name, String descr, int dsaCoins){
        this.name = name;
        this.descr = descr;
        this.dsaCoins = dsaCoins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(int dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

}

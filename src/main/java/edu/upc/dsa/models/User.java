package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils; //Para crear randoms si fuesen necesarios

import java.util.LinkedList;

public class User {  //El enunciado pide nombre, apellido, fechaNac, correo y pass. Ademas tiene un ID y saldo de dsaCoins
    String id;
    String name;
    String surnames;
    String bday;
    String pass;
    String mail;
    int dsaCoins;
    LinkedList<Objecte> boughtObjects;  //Guardemos una lista con los objetos que compra el pibardo

   public User() {} //Constructor vacio siempre va bien

    public User(String id, String name, String surnames, String bday, String pass, String mail) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.bday = bday;
        this.pass = pass;
        this.mail = mail;
        this.dsaCoins = 50; //Nos dice el enunciado que empezamos con 50
        this.boughtObjects = new LinkedList<>(); //Creamos la lista
    }

    public User (VOuser vouser){
        this.id = vouser.getId();
    }
//----------- GETTERS/SETTERS --------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(int dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

    public LinkedList<Objecte> getBoughtObjects() {
        return boughtObjects;
    }

    public void setBoughtObjects(LinkedList<Objecte> boughtObjects) {
        this.boughtObjects = boughtObjects;
    }
}

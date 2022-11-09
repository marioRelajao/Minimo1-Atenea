package edu.upc.dsa.models;

public class VOuser { //La creamos pq no tiene sentido usar la clase User para determinadas acciones, como hacer login ya que tenemos lista objetos/demas info que no nos interesa
    String id;
    String name;
    String surnames;
    String bday;
    String pass;
    String mail;
//------- Constructores -----------
    public VOuser(){};

    public VOuser(String id, String name, String surnames, String bday, String pass, String mail) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.bday = bday;
        this.pass = pass;
        this.mail = mail;
    }

    public VOuser(User u){
        this.name = u.getName();
        this.id = u.getId();
        this.surnames = u.getSurnames();
        this.bday = u.getBday();
        this.mail = u.getMail();
        this.pass = u.getPass();
    }
//---------- GETTERS/SETTERS ------------------

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
}

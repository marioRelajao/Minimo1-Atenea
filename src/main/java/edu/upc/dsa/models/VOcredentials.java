package edu.upc.dsa.models;

public class VOcredentials {
    String mail;
    String pass;

    public VOcredentials(){}

    public VOcredentials(String mail, String pass){
        this.mail = mail;
        this.pass = pass;
    }

    public VOcredentials(User u){
        this.mail = u.getMail();
        this.pass = u.getPass();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

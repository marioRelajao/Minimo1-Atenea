package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredentials;
import edu.upc.dsa.models.VOuser;

import java.util.List;
import java.util.Objects;

public interface ShopManager { //Interficie con funciones para hacer lo que pide el enunciado, añadir, sort etc
    public User addUser(VOuser vouser);
    public List<User> sortUsers();
    public User logIn(VOcredentials credentials);
    public Object buyObject(VOcredentials credentials, String objecte);

    Objecte getObject(String name);

    public Objecte addObject(Objecte objecte);
    public List<Objecte> sortPrice();

    public String getUserByMail(String mail);

    int sizeUsers();
    public List<User> getAllUsers();
    int sizeObjects();
    VOcredentials getCredentials(User u);
}

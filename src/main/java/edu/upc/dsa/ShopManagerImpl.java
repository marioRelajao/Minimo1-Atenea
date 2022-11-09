package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;

import edu.upc.dsa.models.VOcredentials;
import edu.upc.dsa.models.VOuser;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ShopManagerImpl implements ShopManager{
    private static ShopManager instance;

    protected Map<String, User> users;
    protected List<Objecte> objectes;

    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);

    ShopManagerImpl() {
        this.users = new HashMap<>();
        this.objectes = new ArrayList<>();
    }

    public static ShopManager getInstance(){
        if (instance==null) instance = new ShopManagerImpl();
        return instance;
    }

    public String getUserCredentials (VOcredentials credentials){
        logger.info("Searching for user: " + credentials.getMail());
        String idUser = getUserByMail(credentials.getMail());
        if(idUser != null){
            if(this.users.get(idUser).getPass().equals(credentials.getPass())){
                logger.info("User found: " + this.users.get(idUser));
                return idUser;
            }
        }
        logger.warn("User not found" + credentials);
        return null;
    }

    @Override
    public User addUser(VOuser vouser) {
        logger.info("Trying to add new User: " + vouser);

        if (getUserByMail(vouser.getMail()) == null){ //Si es null, no existe un usuario con ese mail, podemos añadirlo
            User u = new User(vouser); //HashMap espera key,User tenemos que hacer la transfo VOuser->User
            this.users.put(vouser.getId(),u);
            logger.info("New user added with ID: " + u.getId());
            return u;
        }
        logger.info("User NOT added, mail already in use");
        return null;
    }

    @Override
    public List<User> sortUsers() {
        if(this.users.size()==0){
            logger.info("Users list is empty");
            return null;
        }
        List<User> list = new ArrayList<>(this.users.values());
        list.sort((User u1, User u2)-> { //Tremenda lambda, ni idea de que hace pero esta guapisimo
            int res = u1.getSurnames().compareToIgnoreCase(u2.getSurnames());
            if (res == 0){
                res = u1.getName().compareToIgnoreCase(u2.getName());
            }
            return res;
        });
        return list;
    }

    @Override
    public User logIn(VOcredentials credentials) {
        logger.info("LogIN for: " + credentials.getMail());

        String u = getUserCredentials(credentials);
        if (u == null){
            logger.warn("Login FAILED");
            return null;
        }
        logger.info("Login OK");
        
        return users.get(u); //retorname de la lista de Hash el que tiene userid u
    }

    @Override
    public Object buyObject(VOcredentials credentials, String objecte) {
        Objecte thingie = getObject(objecte);

        if(thingie == null){
            logger.info("Object not found");
            return null;
        }
        String i = getUserCredentials(credentials);
        if(i == null){
            logger.info("User not found");
            return null;
        }
        Objecte compra = this.users.get(i).buyObject(thingie);
        if (compra != null){
            logger.info("Object bought");
            return thingie;
        }
        logger.info("Not enough money, la de trabajar te la sabes?");
        return null;
    }



    @Override
    public Objecte getObject(String name){
        logger.info("Searching for: " + name);
        for(Objecte o: this.objectes){
            if(o.getName().equals(name)){
                logger.info("Found!\uD83D\uDC49 \uD83D\uDC48");
                return o;
            }
        }
        logger.info("Object not found");
        return null;
    }

    public int sizeUsers(){
        int ret = this.users.size();
        logger.info(ret + " users in the list");
        return ret;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = this.users.values().stream().collect(toList());
        return list;
    }

    public int sizeObjects(){
        int ret = this.objectes.size();
        logger.info(ret + " objects in the list");
        return ret;
    }
    @Override
    public VOcredentials getCredentials(User u) {
        return new VOcredentials(u);
    }

    @Override
    public List<Objecte> getAllObjects() {

        return this.objectes;
    }

    @Override
    public List<Objecte> sortNumObjectes() {
        if(sizeObjects()==0){
            logger.info("Empty object list, can't sort");
            return null;
        }
        objectes.sort((Objecte o1, Objecte o2) -> Integer.compare(o2.getDsaCoins(), o1.getDsaCoins()));
        return this.objectes;
    }

    @Override
    public User getUser(String id) {
        return this.users.get(id);
    }

    @Override
    public List<Objecte> getObjectes(User t) {
        return t.getBoughtObjects();
    }

    @Override
    public Objecte addObject(Objecte objecte) {
        logger.info("Trying to add object:" + objecte);
        if(getObject(objecte.getName())==null){
            logger.info("New object added: " + objecte.getName());
            return objecte;
        }
        logger.info("Object NOT added, already exists");
        return null;
    }

    @Override
    public List<Objecte> sortPrice() {
        if(objectes.size() == 0){
            logger.info("Empty list, can't sort");
            return null;
        }
        objectes.sort((Objecte o1, Objecte o2) -> Integer.compare(o2.getDsaCoins(), o1.getDsaCoins()));
        return this.objectes;
    }

    @Override
    public String getUserByMail(String mail) { //Retorname el key de ese user
        logger.info("Searching User with mail: " + mail);
        //Busquemos en toda la lista de users(HashMap) un user con ese mail
        for (Map.Entry<String,User> entry : this.users.entrySet()){
            if (entry.getValue().getMail().equals(mail)){ //Tremendo porro, si el VALOR (no la key) que equivale a un User.getMail = mail cosas
                logger.info("Found User: " + entry.getValue());
                return entry.getKey(); //Retornamos la posicion en el HashMap
            }
        }
        logger.info("User not found");
        return null;
    }
}

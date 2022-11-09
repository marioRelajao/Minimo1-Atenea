package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredentials;
import edu.upc.dsa.models.VOuser;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ShopManagerImplTest {
    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);
    ShopManager sm;

    @Before
    public void setUp(){
        sm = new ShopManagerImpl();

        sm.addUser(new VOuser("111","Mario","Cerda Trigueros","15","cum", "mario@gmail.com"));
        sm.addUser(new VOuser("222","Yelepo","Circumferencia Completa","Si","Solanum", "nomai@gmail.com"));
        sm.addUser(new VOuser("333","Felepo","Circumferencia Completa","No","Solanum", "tremendo@gmail.com"));

        sm.addObject(new Objecte("Snack Chocolate", "Anitin", 10));
        sm.addObject(new Objecte("Lapis", "que guapo como pinta oleeee", 1));
        sm.addObject(new Objecte("Grapadora", "Dale grapa mi rey", 4));
    }

    @After
    public void tearDown(){
        this.sm = null;
        logger.info("----END----");
    }

    @Test
    public void addUser(){
        logger.info("----- Start test - Add user ------");
        logger.info("Initial conditions: ");
        Assert.assertEquals(3,this.sm.sizeUsers());

        logger.info("We add 1 user");
        this.sm.addUser(new VOuser("444","Chupo","MM Mototruco","Cmelman","pablo", "grugadecum@gmail.com"));

        Assert.assertEquals(4,this.sm.sizeUsers());
    }

    @Test
    public void logIn(){
        logger.info("------- Start test - LogIn ---------");
        List<User> users = this.sm.getAllUsers();
        VOcredentials cred1 = this.sm.getCredentials(users.get(0));
        logger.info("Trying to log in with " + cred1.getPass());
        Assert.assertEquals(users.get(0),this.sm.logIn(cred1));

        VOcredentials cred2 = this.sm.getCredentials(users.get(1));
        cred2.setPass("cagaste");
        logger.info("Trying to log in with:" + cred2.getPass());
        Assert.assertEquals(null,this.sm.logIn(cred2));
    }
}

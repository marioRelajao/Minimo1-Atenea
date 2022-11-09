package edu.upc.dsa.services;



import edu.upc.dsa.ShopManager;
import edu.upc.dsa.ShopManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/shop", description = "Endpoint to Track Service")
@Path("/shop")
public class ShopService {

    private ShopManager sm;

    public ShopService() {
        this.sm = ShopManagerImpl.getInstance();
        if (sm.sizeUsers()==0) {
            User u1 = this.sm.addUser(new VOuser("111","Mario","Cerda Trigueros","15","cum", "mario@gmail.com"));
            VOcredentials cred1 = this.sm.getCredentials(u1);
            User u2 = this.sm.addUser(new VOuser("222","Yelepo","Circumferencia Completa","Si","Solanum", "nomai@gmail.com"));
            VOcredentials cred2 = this.sm.getCredentials(u2);
            User u3 = this.sm.addUser(new VOuser("333","Felepo","Circumferencia Completa","No","Solanum", "tremendo@gmail.com"));
            VOcredentials cred3 = this.sm.getCredentials(u2);
        }
        if (sm.sizeObjects()==0){
            this.sm.addObject(new Objecte("Snack Chocolate", "Anitin", 10));
            this.sm.addObject(new Objecte("Lapis", "que guapo como pinta oleeee", 1));
            this.sm.addObject(new Objecte("Grapadora", "Dale grapa mi rey", 4));

        }

    }

    @GET
    @ApiOperation(value = "get all Users", notes = "See all users in the lsit")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.sm.getAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get Users sorted", notes = "Sorted lsit by surname, if equal by name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/users/order")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortUsers() {

        List<User> users = this.sm.sortUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all objects", notes = "List of all objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objecte.class, responseContainer="List"),
    })
    @Path("/objectes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectes() {

        List<Objecte> objectes = this.sm.getAllObjects();
        GenericEntity<List<Objecte>> entity = new GenericEntity<List<Objecte>>(objectes) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all objects sorted by price", notes = "List of all objects sorted illo, si lo pone ahi")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objecte.class, responseContainer="List"),
    })
    @Path("/objectes/order")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortNumObjectes() {

        List<Objecte> objectes = this.sm.sortNumObjectes();
        GenericEntity<List<Objecte>> entity = new GenericEntity<List<Objecte>>(objectes) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "Get user objects", notes = "List of all objects of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
    })
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {

        User t = this.sm.getUser(id);
        if(t == null){
            return Response.status(404).build();
        }
        else{
            List<Objecte> objects = this.sm.getObjectes(t);
            GenericEntity<List<Objecte>> entity = new GenericEntity<List<Objecte>>(objects) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @POST
    @ApiOperation(value = "Register a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=VOuser.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/users/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(VOuser user) {
        User u = this.sm.addUser(user);
        if(u==null){
            return Response.status(500).build();
        }
        VOuser vo = new VOuser(u);
        return Response.status(201).entity(vo).build();
    }

    @POST
    @ApiOperation(value = "Register a new Object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Objecte.class),
            @ApiResponse(code = 500, message = "Already Exists")

    })

    @Path("/objects/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addObject(Objecte object) {
        Objecte o = this.sm.addObject(object);
        if(o==null){
            return Response.status(500).build();
        }

        return Response.status(201).entity(o).build();
    }

    @POST
    @ApiOperation(value = "login", notes = "Try to login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(VOcredentials credentials) {
        User u = this.sm.logIn(credentials);
        if (u==null)  return Response.status(500).build();
        else return Response.status(201).entity(u).build();
    }

    @PUT
    @ApiOperation(value = "Buy smthng", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Don't be poor please")
    })
    @Path("/users/{id}/{prodName}")
    public Response getUser(@PathParam("id") String id, @PathParam("prodName") String objecte) {

        User t = this.sm.getUser(id);
        if(t==null){
            return Response.status(404).build();
        }
        else {
            Object compra = this.sm.buyObject(this.sm.getCredentials(t),objecte );
            if(compra == null) return Response.status(400).build();
            else return Response.status(201).build();
        }
    }





}
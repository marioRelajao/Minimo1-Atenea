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
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.sm.getAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Track.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Track track) {

        if (track.getSinger()==null || track.getTitle()==null)  return Response.status(500).entity(track).build();
        this.tm.addTrack(track);
        return Response.status(201).entity(track).build();
    }

}
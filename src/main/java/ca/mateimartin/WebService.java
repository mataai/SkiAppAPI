package ca.mateimartin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.mateimartin.dto.StatusDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class WebService {

    static Gson gson = new Gson();

    public WebService() {

    }

    // region GET
    @GET
    @Path("/")
    public static String home() {
        return "supp brooo";
    }

    @GET
    @Path("/levels")
    @Produces(MediaType.APPLICATION_JSON)
    public static String teachers() throws InterruptedException {
        return gson.toJson(DBService.getLevels());
    }

    @GET
    @Path("/levels/{id}/exercices")
    @Produces(MediaType.APPLICATION_JSON)
    public static String ExercicesByLevel(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getExercicesByLevel(id));
    }

    @GET
    @Path("/levels/{id}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public static String GroupsByLevel(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getGroupsByLevel(id));
    }

    @GET
    @Path("/levels/{id}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public static String groupStudent(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getStudentsByLevel(id));
    }

    @GET
    @Path("/groups/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String Groups(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getGroupsByLevel(id));
    }

    @GET
    @Path("/group/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String Group(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getGroup(id));
    }

    @GET
    @Path("/upgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getUpgrade(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson("yo");
    }

    @GET
    @Path("/downgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getDowngrade(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson("YO");
    }

    @GET
    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String search(@PathParam("id") String id) throws InterruptedException {
        return gson.toJson(DBService.search(id));
    }

    // endregion

    @POST
    @Path("/status/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createClasse(StatusDTO s) throws InterruptedException {
        Thread.sleep(2000);
        if (s == null) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidObject").build();
            return res;
        }
        boolean lr = DBService.updateStatus(s);


        if(lr){
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
            return res;
        }

        
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr).build();
        
    }

}
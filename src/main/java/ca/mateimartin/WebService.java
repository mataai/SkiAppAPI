package ca.mateimartin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.*;

import ca.mateimartin.dto.*;

@Path("/")
public class WebService {

    static Gson gson = new Gson();
    static DBService db = new DBService();;

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
    public static String GroupsByLevel(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getStudentsByLevel(id));
    }

    @GET
    @Path("/groups/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String Group(@PathParam("id") int id) throws InterruptedException {
        return gson.toJson(DBService.getGroup(id));
    }


    // endregion

}
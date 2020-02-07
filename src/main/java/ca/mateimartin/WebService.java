package ca.mateimartin;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    // endregion

}
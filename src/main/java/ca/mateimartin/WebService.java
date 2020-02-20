package ca.mateimartin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import com.google.gson.Gson;

import ca.mateimartin.dto.Employe;
import ca.mateimartin.dto.LoginRequest;
import ca.mateimartin.dto.LoginResponse;
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
    public static String home(@HeaderParam("Token") String token) {
        return "EEEyyyyoooo\n" + token;
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
    public static String GroupsByLevel(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
        return gson.toJson(DBService.getGroupsByLevel(id));
    }

    @GET
    @Path("/levels/{id}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public static String groupStudent(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
        return gson.toJson(DBService.getStudentsByLevel(id));
    }

    @GET
    @Path("/groups/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String Groups(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
        return gson.toJson(DBService.getGroupsByLevel(id));
    }

    @GET
    @Path("/group/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String Group(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
        return gson.toJson(DBService.getGroup(id));
    }

    @GET
    @Path("/upgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getUpgrade(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
        return gson.toJson("yo");
    }

    @GET
    @Path("/downgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getDowngrade(@HeaderParam("UserToken") String token, @PathParam("id") int id)
            throws InterruptedException {
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
    public Response updateStatusOld(@HeaderParam("UserToken") String token, StatusDTO s) throws InterruptedException {
        Thread.sleep(2000);
        if (s == null) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidObject").build();
            return res;
        }
        boolean lr = DBService.updateStatus(s);

        if (lr) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
            return res;
        }

        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr)
                .build();

    }

    @POST
    @Path("/update/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@HeaderParam("UserToken") String token, StatusDTO s) throws InterruptedException {
        Thread.sleep(2000);
        if (s == null) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidObject").build();
            return res;
        }
        boolean lr = DBService.updateStatus(s);

        if (lr) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
            return res;
        }

        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr)
                .build();

    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest s) throws InterruptedException {

        if (s == null) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("Invalid Login").build();
            return res;
        }

        Employe emp = DBService.getEmploye(s.code);
        if (emp == null) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InexistentUser").build();
            return res;
        }
        emp = DBService.getPerms(emp);
        UUID token = UUID.randomUUID();
        LoginResponse lr = new LoginResponse(emp, token);

        boolean sql = DBService.login(emp.id, token);
        if (sql) {
            Response.ResponseBuilder rBuild = Response.status(Response.Status.ACCEPTED);
            Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr).build();
            return res;
        }

        Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
        Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
        return res;
        
    }

}
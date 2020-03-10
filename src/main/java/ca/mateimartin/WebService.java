package ca.mateimartin;

import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import ca.mateimartin.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class WebService {

    static Gson gson = new Gson();

    public WebService() {
    }

    // region GET
    @GET
    @Path("")
    public static String home(@HeaderParam("UserToken") final String token) {
        return "EEEyyyyoooo\n" + token;
    }

    @GET
    @Path("/levels")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getLevels() throws InterruptedException {
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity(DBService.getLevels()).build();
    }

    @GET
    @Path("/levels/{id}/exercices")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response ExercicesByLevel(@PathParam("id") final int id) throws InterruptedException {
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity(DBService.getExercicesByLevel(id)).build();
    }

    @GET
    @Path("/groups/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response Groups(@HeaderParam("UserToken") final String token, @PathParam("id") final int id)
            throws InterruptedException {
        if (token == null || token == "" || token.length() != 36) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("InvalidToken").build();
            return res;
        }
        System.out.println("55");
        List<Group> output = Service.getGroupsByLevel(id, UUID.fromString(token));
        System.out.println("output size");
        System.out.println(output.size());
        if (output.size() < 1) {
            final Response res = Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity(output).build();
            return res;
        }
        System.out.println("57");

        if (output.get(0).id == -1) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("{ error : " + output.get(0) + " }").build();
            return res;
        }
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity(output).build();
    }

    @GET
    @Path("/levels/{id}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response GroupsByLevel(@HeaderParam("UserToken") final String token, @PathParam("id") final int id)
            throws InterruptedException {
        if (token == null || token == "" || token.length() != 36) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("InvalidToken").build();
            return res;
        }

        List<Group> output = Service.getGroupsByLevel(id, UUID.fromString(token));

        if (output.size() < 1) {
            final Response res = Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity(output).build();
            return res;
        }

        if (output.get(1).id == -1) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity(output.get(1)).build();
            return res;
        }
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity(output).build();
    }

    // @GET
    // @Path("/levels/{id}/students")
    // @Produces(MediaType.APPLICATION_JSON)
    // public static String groupStudent(@HeaderParam("UserToken") final String
    // token, @PathParam("id") final int id)
    // throws InterruptedException {
    // return gson.toJson(DBService.getStudentsByLevel(id));
    // }

    @GET
    @Path("/group/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response Group(@HeaderParam("UserToken") final String token, @PathParam("id") final int id)
            throws InterruptedException {
        if (token == null || token == "" || token.length() != 36) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("InvalidToken").build();
            return res;
        }
        System.out.println(token);
        Group out = Service.getGroup(id, UUID.fromString(token));
        System.out.println(out);
        if (out.id == -1) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity(out.Number).build();
            return res;
        }
        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(out)
                .build();
    }

    @GET
    @Path("/upgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getUpgrade(@HeaderParam("UserToken") final String token, @PathParam("id") final int id)
            throws InterruptedException {
        return Response.status(Response.Status.NOT_IMPLEMENTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity("NotImplemented").build();
    }

    @GET
    @Path("/downgrade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getDowngrade(@HeaderParam("UserToken") final String token, @PathParam("id") final int id)
            throws InterruptedException {
        return Response.status(Response.Status.NOT_IMPLEMENTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                .entity("NotImplemented").build();
    }

    @GET
    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response search(@HeaderParam("UserToken") final String token, @PathParam("id") final String id)
            throws InterruptedException {
        UUID okToken;
        if (token == null || token == "" || token.length() != 36) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("InvalidToken").build();
            return res;
        }
        try {
            okToken = UUID.fromString(token);
        } catch (Exception e) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("InvalidToken").build();
            return res;
        }

        List<SearchResponse> out = Service.search(okToken, id);

        if (out.size() < 1) {
            final Response res = Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity("EmptyDataseta").build();
            return res;
        } else if (out.size() == 1 && out.get(0).group.id == -1) {
            final Response res = Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
                    .encoding("UTF-8").entity(out.get(0).group.Number).build();
            return res;
        }

        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(out)
                .build();

    }

    // endregion

    @POST
    @Path("/status/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatusOld(@HeaderParam("UserToken") final String token, final StatusDTO s)
            throws InterruptedException {
        if (token == null || token == "") {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                    .entity("InvalidToken").build();
            return res;
        }
        if (s == null) {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                    .entity("InvalidObject").build();
            return res;
        }
        final boolean lr = DBService.updateStatus(s);

        if (lr) {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                    .entity("SQLError").build();
            return res;
        }

        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr)
                .build();

    }

    @POST
    @Path("/update/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@HeaderParam("UserToken") final String token, final StatusDTO s)
            throws InterruptedException {
        if (token == null || token == "") {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                    .entity("InvalidToken").build();
            return res;
        }
        if (s == null) {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8")
                    .entity("InvalidObject").build();
            return res;
        }
        System.out.println(s);
        final boolean lr = DBService.updateStatus(s);

        if (lr) {
            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
            return res;
        }

        return Response.status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr)
                .build();

    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest s) throws InterruptedException {
        try {
            if (s == null) {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidLogin")
                        .build();
                return res;
            }
            try {
                Integer.parseInt(s.code);
            } catch (Exception e) {
                //TODO: handle exception
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidLogin")
                        .build();
                return res;
            }
            final Employe emp = DBService.getEmploye(Integer.parseInt(s.code));
            if (emp == null) {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InexistentUser")
                        .build();
                return res;
            }

            final LoginResponse lr = Service.login(emp);

            if (lr != null) {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.ACCEPTED);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity(lr).build();
                return res;
            }

            final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
            final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError").build();
            return res;
        } catch (final Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }

        final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
        final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("Error").build();
        return res;

    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("UserToken") final String token, final String Token)
            throws InterruptedException {

        try {
            if (Token == null && token == null || Token == "" && token == "") {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("InvalidToken")
                        .build();
                return res;
            }
            String result;
            if (token == null) {
                result = Service.logout(UUID.fromString(Token));

            } else {
                result = Service.logout(UUID.fromString(token));
            }

            if (result.equals("Good")) {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.ACCEPTED);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("Disconnected")
                        .build();
                return res;
            } else if (result.equals("SQLError")) {
                final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
                final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("SQLError")
                        .build();
                return res;
            }

        } catch (final Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }

        final Response.ResponseBuilder rBuild = Response.status(Response.Status.BAD_REQUEST);
        final Response res = rBuild.type(MediaType.APPLICATION_JSON).encoding("UTF-8").entity("Error").build();
        return res;

    }

}
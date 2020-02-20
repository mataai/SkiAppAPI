package ca.mateimartin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonElement;

import ca.mateimartin.dto.*;

public class Service {


    public static LoginResponse login(Employe emp){
        emp = DBService.getPerms(emp);
        UUID token = UUID.randomUUID();

        boolean sql = DBService.login(emp.id, token);
        if (!sql){
            return null;
        }
        else{
            return new LoginResponse(emp, token);
        }
    }

    public static String logout(UUID token){
        if (token == null){
            return "TokenNull";
        }

        if (DBService.deleteToken(token)){
            return "Good";
        }
        return "SQLError";

    }

}
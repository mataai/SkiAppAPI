package ca.mateimartin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import ca.mateimartin.dto.*;

public class Service {

    public static LoginResponse login(Employe emp) {
        emp = DBService.getPerms(emp);
        UUID token = UUID.randomUUID();

        boolean sql = DBService.login(emp.id, token);
        if (!sql) {
            return null;
        } else {
            return new LoginResponse(emp, token);
        }
    }

    public static String logout(UUID token) {
        if (token == null) {
            return "TokenNull";
        }

        if (DBService.deleteToken(token)) {
            return "Ok";
        }

        return "SQLError";

    }

    public static Group getGroup(int groupID, UUID token) {
        Group out = new Group().id(-1).number("Error");
        int empID = DBService.getUserByToken(token);
        if (empID == 0) {
            return out.number("InvalidatedToken");
        }
        Employe emp = DBService.getEmploye(empID);
        emp = DBService.getPerms(emp);
        if (emp.permissions.containsValue(0) || Service.hasGroupPerms(emp, groupID, 'R')) {
            return DBService.getGroup(groupID);
        }

        return out.number("NotAuthorized");
    }

    public static boolean hasGroupPerms(Employe emp, int groupID, char action) {
        if (emp == null) {
            return false;
        }

        int perm = DBService.getPerm(emp.id, groupID);

        if (perm == 0) {
            return true;
        } else if (action == 'R' && perm < 100) {
            return true;
        } else if (action == 'W' && perm < 10) {
            return true;
        }

        return false;
    }

    public static List<Group> getGroupsByLevel(int id, UUID token) {

        List<Group> output = new ArrayList<>();
        int empID = DBService.getUserByToken(token);
        if (empID == 0) {
            output.add(new Group(-1, "InvalidatedToken"));
            return output;
        }   

        return DBService.getGroupsByLevel(id, empID);

    }

    public static String updateStatus(StatusDTO s, UUID token) {
        Employe emp = DBService.getEmploye(token);
        if (emp == null) {
            return "InvalidatedToken";
        }
        boolean perm = hasGroupPerms(emp, s.groupID, 'w');
        if (perm){
            boolean sqlerror = DBService.updateStatus(s, emp.id);
            if (!sqlerror){
                return "Ok";
            }
            else {
                return "SQLError";
            }
        }
        return "NotAuthorized";
    }

    public static List<SearchResponse> search(UUID token , String id) {
        List<SearchResponse> output = new ArrayList<>();

        int empID = DBService.getUserByToken(token);
        if (empID == 0) {
            output.add(new SearchResponse(null, new Group(-1, "InvalidatedToken")));
            return output;
        }

        output = DBService.search(id);

        return output;
    }

}
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

public class DBService {

    public DBService() {
    }

    private static Connection connect() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ski?useSSL=false", "user",
                    "passw0rd");
            return con;
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Level> getLevels() {

        Connection sql = null;
        List<Level> output = new ArrayList<>();
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Levels`");
            while (res.next()) {
                Level tempClasse = new Level(res.getInt(1), res.getString(2), res.getString(3));
                output.add(tempClasse);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static List<Exercice> getExercices() {

        Connection sql = null;
        List<Exercice> output = new ArrayList<>();
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Exercices`");
            while (res.next()) {
                Exercice tempClasse = new Exercice(res.getString(2), res.getString(3), res.getInt(4));
                output.add(tempClasse);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static List<Group> getGroupsByLevel(int id, int empID) {

        Connection sql = null;
        List<Group> output = new ArrayList<>();
        try {
            sql = connect();
            PreparedStatement req = sql
                    .prepareStatement("SELECT * FROM `VW_Permissions` WHERE LevelID = ? AND EmployeID = ?");
            req.setInt(1, id);
            req.setInt(2, empID);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                if (res.getInt(2) <= 10) {
                    Group tempGroup = new Group(res.getInt(3), res.getString(5), res.getString(4), res.getString(6),
                            res.getInt(7), res.getString(8));
                    output.add(tempGroup);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static List<Group> getGroupsByLevelOG(int id) {

        Connection sql = null;
        List<Group> output = new ArrayList<>();
        try {
            sql = connect();
            PreparedStatement req = sql
                    .prepareStatement("SELECT * FROM `Groups` WHERE LevelID = ? ORDER BY day desc, Time;");
            req.setInt(1, id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                Group tempGroup = new Group(res.getInt(1), res.getString(3), res.getString(2), res.getString(4),
                        res.getInt(5), res.getString(6));
                output.add(tempGroup);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static Group getStudents(int id) {

        Connection sql = null;
        Group output = null;
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Groups` WHERE GroupID = " + id + ";");
            while (res.next()) {
                Group tempGroup = new Group(res.getInt(1), res.getString(3), res.getString(2), res.getString(4),
                        res.getInt(5), res.getString(6));
                try {
                    sql = connect();
                    Statement req2 = sql.createStatement();
                    ResultSet res2 = req2.executeQuery("SELECT * FROM VW_Inscription where GroupID = " + id + ";");
                    while (res2.next()) {

                        tempGroup.Students.add(new Student(res2.getInt(2), res2.getString(3) + " " + res2.getString(4),
                                res.getInt(5)));
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                output = tempGroup;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static Group getGroup(int id) {

        Connection sql = null;
        Group output = null;
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Groups` WHERE GroupID = " + id + ";");
            while (res.next()) {
                Group tempGroup = new Group(res.getInt(1), res.getString(3), res.getString(2), res.getString(4),
                        res.getInt(5), res.getString(6));
                try {
                    sql = connect();
                    Statement req2 = sql.createStatement();
                    ResultSet res2 = req2.executeQuery("SELECT * FROM VW_Inscription where GroupID = " + id + ";");
                    while (res2.next()) {
                        tempGroup.Students.add(new Student(res2.getInt(1), res2.getString(2) + " " + res2.getString(3),
                                res2.getInt(4)));
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                output = tempGroup;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static List<Exercice> getExercicesByLevel(int id) {
        Connection sql = null;
        List<Exercice> output = new ArrayList<>();
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Exercices` WHERE `LevelID` = " + id + ";");
            while (res.next()) {
                Exercice tempClasse = new Exercice(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
                output.add(tempClasse);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
    }

    public static JsonElement getStudentsByLevel(int id) {
        return null;
    }

    public static List<Group> getDowngrade(int studentID) {
        Connection sql = null;
        List<Group> output = new ArrayList<>();
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `Groups` WHERE GroupID = " + studentID + ";");
            while (res.next()) {
                Group tempClasse = new Group(res.getInt(1), res.getString(3), res.getString(2), res.getString(4),
                        res.getInt(5), res.getString(6));
                output.add(tempClasse);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB(sql);
        return output;
    }

    public static boolean updateStatus(StatusDTO s) {

        Connection sql = null;
        PreparedStatement stmt = null;
        boolean output = false;
        int[] old = new int[3]; // GroupID StudentID Status
        try {

            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM `StudentGroup` WHERE StudentID = " + s.studentID + ";");
            while (res.next()) {
                old[0] = res.getInt(1);
                old[1] = res.getInt(2);
                old[2] = res.getInt(3);

            }

            stmt = sql.prepareStatement("INSERT INTO `StudentGroupHistoryOld` (GroupID,StudentID,Status) VALUES (" + old[0]
                    + "," + old[1] + "," + old[2] + ")");

            stmt.executeUpdate();

            stmt = sql.prepareStatement("UPDATE `StudentGroup` SET `Status`= ? WHERE `StudentID` = ?");

            stmt.setInt(1, s.status);
            stmt.setInt(2, s.studentID);
            stmt.executeUpdate();
            closeDB(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            output = true;
        }
        closeDB(sql);
        return output;
    }

    public static List<SearchResponse> search(String id) {
        String[] input = id.toUpperCase().split("_");
        List<SearchResponse> out = new ArrayList<>();
        Connection sql = null;
        try {
            sql = connect();
            Statement req = sql.createStatement();
            ResultSet res;
            if (input.length == 2) {
                res = req.executeQuery("SELECT * FROM `VW_Inscription` WHERE `Name` LIKE '" + input[0]
                        + "%' OR `FirstName` LIKE '" + input[1] + "%' OR `Name` LIKE '" + input[1]
                        + "%' OR `FirstName` LIKE '" + input[0] + "%' ");
            } else {
                res = req.executeQuery("SELECT * FROM `VW_Inscription` WHERE `Name` LIKE '" + input[0]
                        + "%' OR `FirstName` LIKE '" + input[0] + "%'");
            }
            req.toString();
            while (res.next()) {
                out.add(new SearchResponse(
                        new Student(res.getInt(1), res.getString(2) + " " + res.getString(3), res.getInt(4)),
                        getGroup(res.getInt(5))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB(sql);
        return out;
    }

    public static Employe getEmploye(int id) {
        Employe output = null;
        Connection sql = null;
        try {
            sql = connect();
            PreparedStatement req = sql.prepareStatement("SELECT * FROM `Employes` WHERE EmployeID = ?;");
            req.setInt(1, id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                output = new Employe(res.getInt(1), res.getString(2) + " " + res.getString(3));
            }
            res.close();
        } catch (Exception e) {
            closeDB(sql);
            System.out.println(e.getMessage());
        }

        closeDB(sql);

        return output;
    }

    public static int getPerm(int empID, int groupID) {
        Connection sql = null;
        try {
            sql = connect();
            PreparedStatement req = sql
                    .prepareStatement("SELECT * FROM `VW_Permissions` WHERE `GroupID` = ? AND `EmployeID` = ?");
            req.setInt(1, groupID);
            req.setInt(2, empID);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                return res.getInt(1);
            }
            res.close();
        } catch (Exception e) {
            closeDB(sql);
            System.out.println(e.getMessage());
            return 1000;
        }
        return 1000;

    }

    public static Employe getPerms(Employe emp) {
        Connection sql = null;
        try {
            sql = connect();
            PreparedStatement req = sql.prepareStatement("SELECT * FROM `DepartementStaff` WHERE EmployeID = ?;");
            req.setInt(1, emp.id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                emp.permissions.put(res.getInt(3), res.getInt(2));
            }
            res.close();
        } catch (Exception e) {
            closeDB(sql);
            System.out.println(e.getMessage());
            return null;
        }
        return emp;
    }

    public static int getUserByToken(UUID token) {
        Integer out = 0;
        Connection sql = null;
        try {
            sql = connect();
            PreparedStatement req = sql.prepareStatement("SELECT * FROM `Logins` WHERE `Token` = ?");
            req.setString(1, token.toString());
            ResultSet res = req.executeQuery();
            while (res.next()) {

                out = res.getInt(1);
            }
        } catch (Exception e) {
            closeDB(sql);
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return out;
    }

    public static Boolean login(int EmployeID, UUID token) {

        Connection sql = null;
        try {
            sql = connect();
            PreparedStatement req = sql.prepareStatement("INSERT INTO `Logins`(`EmployeID`, `Token`) VALUES (?,?)");
            req.setInt(1, EmployeID);
            req.setString(2, token.toString());
            int res = req.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            closeDB(sql);
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return false;
    }

    public static boolean deleteToken(UUID token) {

        if (token == null) {
            return false;
        }
        try (Connection conn = connect();
                PreparedStatement req = conn.prepareStatement("DELETE FROM `Logins` WHERE `Token` = ?")) {

            req.setString(1, token.toString());
            int i = req.executeUpdate();
            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static void closeDB(Connection sql) {
        if (sql != null) {
            try {
                sql.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
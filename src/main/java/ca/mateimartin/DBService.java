package ca.mateimartin;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.JsonElement;

import ca.mateimartin.dto.*;

public class DBService {

    public DBService() {
    }

    private static Connection connect() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ski?useSSL=false", "user","passw0rd");
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
                Level tempClasse = new Level(res.getInt(1),res.getString(2), res.getString(3));
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
                Exercice tempClasse = new Exercice(res.getString(2), res.getString(3),res.getInt(4));
                output.add(tempClasse);
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
                Exercice tempClasse = new Exercice(res.getString(2), res.getString(3),res.getInt(4));
                output.add(tempClasse);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        closeDB(sql);
        return output;
	}

    // public static List<Exercice> getClassesbyID(int id) {

    //     Connection sql = null;
    //     List<Classe> output = new ArrayList<>();
    //     try {
    //         sql = connect();
    //         Statement req = sql.createStatement();
    //         ResultSet res = req.executeQuery("SELECT * FROM `Class`  WHERE `roomID` = " + id + ";");
    //         while (res.next()) {
    //             Classe tempClasse = new Classe(res.getInt(1), res.getTime(2), res.getTime(3), res.getInt(4),
    //                     res.getInt(5), DBService.getTeacher(res.getInt(6)), DBService.getGroup(res.getInt(7)));
    //             output.add(tempClasse);
    //         }

    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }

    //     closeDB(sql);
    //     return output;

    // }


    public static void closeDB(Connection sql) {
        if (sql != null) {
            try {
                sql.close();
            } catch (Exception e) {
            }
        }
    }

}
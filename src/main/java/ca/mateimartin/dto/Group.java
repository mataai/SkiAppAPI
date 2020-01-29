package ca.mateimartin.dto;

import java.util.ArrayList;

public class Group {
    int id;
    public String Number;
    public String Level;
    public String Time;
    public ArrayList<Student> Students = new ArrayList<>();

    public Group(String Number,String Level, String Time){
        this.Number = Number;
        this.Level = Level;
        this.Time = Time;
    }
    public Group(int id ,String Number,String Level, String Time){
        this.id = id;
        this.Number = Number;
        this.Level = Level;
        this.Time = Time;
    }
}

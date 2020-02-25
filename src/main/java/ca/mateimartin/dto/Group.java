package ca.mateimartin.dto;


import java.util.ArrayList;

public class Group {
    public int id;
    public String Number;
    public String Level;
    public String Time;
    public String TeacherName;
    public int day;
    public ArrayList<Student> Students = new ArrayList<>();

    public Group(){}

    public Group(int id, String number){
        this.id = id;
        this.Number = number;
    }

    public Group(int id ,String Number,String Level, String Time,int day){
        this.id = id;
        this.Number = Number;
        this.Level = Level;
        this.Time = Time;
        this.day = day;
    }
    public Group(int id ,String Number,String Level, String Time,int day,String teacher){
        this.id = id;
        this.Number = Number;
        this.Level = Level;
        this.Time = Time;
        this.day = day;
        this.TeacherName = teacher;
    }

    public Group id(int id){
        this.id = id;
        return this;
    }

    public Group number(String number){
        this.Number = number;
        return this;
    }
}

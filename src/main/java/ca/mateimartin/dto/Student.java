package ca.mateimartin.dto;

public class Student {
    public int id;
    public String Name;
    public String Phone;
    public int Status;


    public static String[] progression = new String[]{"normal", "fini", "retard", "tresretard"};

    public Student(String Name, int Status){
        this.Name = Name;
        this.Status = Status;
    }

    public Student(int id, String Name, int Status){
        this.id = id;
        this.Name = Name;
        this.Status = Status;
    }
}

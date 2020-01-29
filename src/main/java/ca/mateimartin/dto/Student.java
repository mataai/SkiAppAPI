package ca.mateimartin.dto;

public class Student {
    int id;
    public String Name;
    public String Phone;

    public Student(String Name,String Phone){
        this.Name = Name;
        this.Phone = Phone;
    }

    public Student(int id, String Name,String Phone){
        this.id = id;
        this.Name = Name;
        this.Phone = Phone;
    }
}

package ca.mateimartin.dto;

import java.util.ArrayList;

public class Level {
    int id;
    public String name;
    public String description;
    public ArrayList<Exercice> exerciceList = new ArrayList<Exercice>();

    public Level(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciceList = new ArrayList<Exercice>();
    }

    public Level(String name, String description){
        this.name = name;
        this.description = description;
        this.exerciceList = new ArrayList<Exercice>();
    }

    public Level(String name, String description, ArrayList<Exercice> exerciceList){
        this.name = name;
        this.description = description;
        this.exerciceList = exerciceList;
    }

    
}



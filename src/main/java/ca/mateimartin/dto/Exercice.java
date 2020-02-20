package ca.mateimartin.dto;

public class Exercice {
    public int id;
    public String description;
    public String terrain;
    public int type;

    public Exercice(String description,String terrain, int type){
        this.description = description;
        this.terrain = terrain;
        this.type = type;
    }
    public Exercice(int id ,String description,String terrain, int type){
        this.id = id;
        this.description = description;
        this.terrain = terrain;
        this.type = type;
    }
}

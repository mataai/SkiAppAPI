package ca.mateimartin.dto;

public class Exercice {
    int id;
    public String description;
    public String terrain;
    public int type;

    public Exercice(String description,String terrain, int type){
        this.description = description;
        this.terrain = terrain;
        this.type = type;
    }
}

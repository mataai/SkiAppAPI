package ca.mateimartin.dto;

import java.util.HashMap;
import java.util.Map;

public class Employe {
    public int id;
    public String name;
    public Map<Integer, Integer> permissions = new HashMap<Integer,Integer>();

    public Employe(int id, String Name){
        this.name = Name;
        this.id = id;
    }
}

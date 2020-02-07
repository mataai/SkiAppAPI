package ca.mateimartin.dto;

import java.util.ArrayList;

public class Level {
    public int id;
    public String name;
    public String description;
    public ArrayList<Exercice> exerciceList = new ArrayList<Exercice>();

    public Level(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciceList = new ArrayList<Exercice>();
    }

    public Level(int id, String name, String description, ArrayList<Exercice> exerciceList){
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciceList = exerciceList;
    }

    // public void add(Exercice ex){
    //     this.exerciceList.add(ex);
    // }

    // public static ArrayList<Level> getLevels(){
    //     ArrayList<Level> output = new ArrayList<>();
    //     Level temp = new Level("Ourson","Apprendre a s'arreter");
    //     temp.exerciceList.add(new Exercice("Descendre en chasse-neige sous contrôle en exécutant quelques changements de direction.","Pente facile",1));
    //     temp.exerciceList.add(new Exercice("Tourner sur place (360) avec les skis aux pieds.","Terrain plat.",1));
    //     temp.exerciceList.add(new Exercice("Descendre en trace directe sans assistance et sèimmobiliser par ses propres moyens sans tomber.","Pente facile sans replat.",1));
    //     temp.exerciceList.add(new Exercice("Poiuvoir ramasser un objet sur la neige tout en descendant sans tomber, sans s'arrêter.","Pente facile.",2));
    //     temp.exerciceList.add(new Exercice("Pouvoir tomber et se relever sans assistance.","Pente facile.",2));
    //     temp.exerciceList.add(new Exercice("Descendre en chasse-neige sous contrôle en lignre droite.","Pente facile se terminant par un replat",3));
    //     output.add(temp);
    //     temp = new Level("Kangourou","Apprendre a s'arreter");
    //     temp.exerciceList.add(new Exercice("Exécuter la pas ciseaux.","Pente facile.",1));
    //     temp.exerciceList.add(new Exercice("Exécuter une série de petits sauts tout en descendant, en soulevant les skis à chaque saut.","Pente facile.",1));
    //     temp.exerciceList.add(new Exercice("Exécuter une traversée (skis en position parallèle) et s'immobiliser en se dirigeant vers l'amont (skis en positions chasse-neige).","Pente facile.",1));
    //     temp.exerciceList.add(new Exercice("Pouvoir monter dans une remontée mécanique puis en débarquer, seul ou avec une autre personne, mais sans assistance.","",2));
    //     temp.exerciceList.add(new Exercice("Pouvoir chausser et déchausser les skis sans assistance.","Terrain plat.",2));
    //     temp.exerciceList.add(new Exercice("Exécuter quatre (4) virages chasse-neige..","Pente facile.",3));
    //     output.add(temp);
    //     return output;
    // }
}



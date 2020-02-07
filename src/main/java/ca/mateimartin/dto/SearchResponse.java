package ca.mateimartin.dto;

public class SearchResponse {

    public Student student;
    public Group group;

    public SearchResponse(Student student, Group group){
        this.student = student;
        this.group = group;
    }

}

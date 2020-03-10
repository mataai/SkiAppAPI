package ca.mateimartin.dto;

public class StatusDTO {

    public int status;
    public int studentID;
    public int groupID;

    public StatusDTO(int status, int studentID){
        this.status = status;
        this.studentID = studentID;
    }

}

package ca.mateimartin.dto;

public class LoginRequest{

    public int code;
    public String ip;

    public LoginRequest(int code){
        this.code = code;
    }

    public LoginRequest(int code,String ip){
        this.code = code;
        this.ip = ip;
    }

}
package ca.mateimartin.dto;

public class LoginRequest{

    public String code;
    public String ip;

    public LoginRequest(String code){
        this.code = code;
    }

    public LoginRequest(String code,String ip){
        this.code = code;
        this.ip = ip;
    }

}
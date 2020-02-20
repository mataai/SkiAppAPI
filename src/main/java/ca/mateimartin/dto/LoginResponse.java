package ca.mateimartin.dto;

import java.util.UUID;

public class LoginResponse {

    public Employe employe;
    public UUID token;

    public LoginResponse(Employe Employe,UUID Token){
        this.employe = Employe;
        this.token = Token;
    }

}
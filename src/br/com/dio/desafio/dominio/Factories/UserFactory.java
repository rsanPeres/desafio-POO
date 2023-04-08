package br.com.dio.desafio.dominio.Factories;

import br.com.dio.desafio.UserRequest;
import br.com.dio.desafio.dominio.*;

public class UserFactory {
    public User userCreate(UserRequest request){
        switch (request.type){
            case DEV -> {
                return new Dev(request.nome, request.email, request.password, request.type);
            }
            case ADMIN -> {
                return new Admin(request.nome, request.email, request.password, request.type);
            }
            case PROFESSOR -> {
                return new Professor(request.nome, request.email, request.password, request.type);
            }
        }
        return null;
    }
}

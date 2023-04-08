package br.com.dio.desafio.infra;

import br.com.dio.desafio.dominio.User;

import java.util.*;

public class UserDataBase {
    Set<User> users;

    public UserDataBase() {
        this.users = new HashSet<>();
    }

    public Set<User> getUsers() {
        return users;
    }

    private void setUsers(Set<User> users) {
        this.users = users;
    }

    public void inserirUsuario(User user){
        this.users.add(user);
    }

    public User buscaUsuario(String email){

        User equals = findUser(email);
        if(equals != null){
            System.out.println("Usuario já criado");
            return equals;
        }
        return equals;
    }

    public User findUser(String email){
        for (User user:this.users) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

}

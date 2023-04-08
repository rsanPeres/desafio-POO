package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Set;

public class Professor extends User{
    private Set<ConteudoBase> ministrados;
    public Professor(String nome, String email, String password, UserType  type) {
        super(nome, email, password, type);
        this.ministrados = new LinkedHashSet<>();
    }

    public Set<ConteudoBase> getMinistrados() {
        return ministrados;
    }

    public void setMinistrados(ConteudoBase ministrados) {
        this.ministrados.add(ministrados);
    }
}

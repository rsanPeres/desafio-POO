package br.com.dio.desafio.dominio;

import java.util.*;

public class Admin extends User{
    private Set<Bootcamp> conteudos;

    public Admin(String nome, String email, String password, UserType type) {
        super(nome, email, password, type);
        this.conteudos = new LinkedHashSet<>();
    }



    public Set<Bootcamp> getConteudos() {
        return conteudos;
    }

    public void setBootcamp(Set<Bootcamp> conteudos) {
        this.conteudos = conteudos;
    }

    public void setConteudo(Bootcamp conteudo){

        boolean contains = this.conteudos.contains(conteudo);
        if(!contains){
            this.conteudos.add(conteudo);
        }else {
            System.out.println("Conteudo já existe");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(conteudos, admin.conteudos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conteudos);
    }

    public Bootcamp getBootcamp(String nome){
        for (Bootcamp bootcamp: this.conteudos) {
            if(bootcamp.getNome().equals(nome)){
                return bootcamp;
            }
        }
        return null;
    }

    public void setConteudos(String name, Professor prof, ConteudoBase cont){
        for (Bootcamp boot: this.conteudos){
            if(boot.getNome().equals(name)){
                boot.setConteudos(prof, cont);
            }else {
                System.out.println("Bootcamp não encontrado");
            }
        }
    }
}

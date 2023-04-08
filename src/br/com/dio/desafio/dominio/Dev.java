package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev extends User{
    private Set<ConteudoBase> conteudosInscritos;
    private Set<ConteudoBase> conteudosConcluidos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conteudosInscritos, conteudosConcluidos);
    }

    public Dev(String nome, String email, String password, UserType type) {
        super(nome, email, password, type);
        this.conteudosInscritos = new LinkedHashSet<>();
        this.conteudosConcluidos = new LinkedHashSet<>();
    }

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos().values());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir(){
        Optional<ConteudoBase> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()){
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        }else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp(){
        return this.conteudosConcluidos.stream().mapToDouble(ConteudoBase::calcularXp).sum();
    }

    public Set<ConteudoBase> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<ConteudoBase> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<ConteudoBase> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<ConteudoBase> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }
}

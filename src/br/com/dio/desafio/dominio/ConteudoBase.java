package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ConteudoBase {
    protected static final double XP_PADRAO = 10d;
    private String titulo;
    private String descricao;
    private Set<Professor> professors;

    public ConteudoBase(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.professors = new LinkedHashSet<>();
    }

    public abstract double calcularXp();

    public Set<Professor> getProfessors() {
        return professors;
    }

    protected void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public String getTitulo() {
        return titulo;
    }

    protected void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

package com.ufes.sistemagestaofuncionario.model;

public class Cargo {
    
    private long id;
    private String nome;
    
    public Cargo(String nome){
        this.nome = nome;
    }
    
    public Cargo(long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
}

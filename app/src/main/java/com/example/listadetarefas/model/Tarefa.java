package com.example.listadetarefas.model;

public class Tarefa {
    private String nomeDaTarefa;
    private boolean isPrioridade;


    public Tarefa(String nome, boolean prioridade) {
        this.nomeDaTarefa = nome;
        this.isPrioridade = prioridade;
    }

    public String getNomeDaTarefa() {
        return nomeDaTarefa;
    }

    public void setNomeDaTarefa(String nomeDaTarefa) {
        this.nomeDaTarefa = nomeDaTarefa;
    }

    public boolean isPrioridade() {
        return isPrioridade;
    }

    public void setPrioridade(boolean prioridade) {
        isPrioridade = prioridade;
    }
}

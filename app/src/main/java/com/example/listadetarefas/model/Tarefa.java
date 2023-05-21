package com.example.listadetarefas.model;

import android.icu.util.LocaleData;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Tarefa implements Serializable {

    private String nomeDaTarefa;

    private String descricao;

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    private Date dataDeCriacao;

    private boolean isPrioridade;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tarefa(String nome,String descricao, boolean prioridade) {
        this.nomeDaTarefa = nome;
        this.descricao = descricao;
        this.isPrioridade = prioridade;
        this.dataDeCriacao = new Date();
    }




    public Tarefa(String nome,String descricao, boolean prioridade,Date dataAtual) {
        this.nomeDaTarefa = nome;
        this.descricao = descricao;
        this.isPrioridade = prioridade;
        this.dataDeCriacao = dataAtual;
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

package com.example.listadetarefas.dao;

import com.example.listadetarefas.model.Tarefa;

import java.util.List;

public interface ITarefaDao {
    void create(Tarefa article);

    boolean update(String oldTitle, Tarefa article);

    boolean delete(Tarefa article);

    Tarefa findByTitle(String title);

    List<Tarefa> findAll();
}

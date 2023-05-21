package com.example.listadetarefas.dao;

import com.example.listadetarefas.model.Tarefa;

import java.util.List;

public interface ITarefaDao {
    void create(Tarefa task);

    boolean update(String oldTitle, Tarefa task);

    boolean delete(Tarefa task);

    Tarefa findByTitle(String title);

    List<Tarefa> findAll();
}

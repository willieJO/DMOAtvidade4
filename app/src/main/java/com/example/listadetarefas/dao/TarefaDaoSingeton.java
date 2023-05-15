package com.example.listadetarefas.dao;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDaoSingeton implements ITarefaDao {
    private static TarefaDaoSingeton instance = null;
    private List<Tarefa> dataset;

    private TarefaDaoSingeton() {
        dataset = new ArrayList<>();
    }

    public static TarefaDaoSingeton getInstance(){
        if(instance == null)
            instance = new TarefaDaoSingeton();
        return instance;
    }

    @Override
    public void create(Tarefa tarefa) {
        if(tarefa != null) {
            dataset.add(tarefa);
        }
    }

    @Override
    public boolean update(String oldTitle, Tarefa tarefa) {
        Tarefa inDataset;
        inDataset = dataset.stream()
                .filter(tarefa1 -> tarefa1.getNomeDaTarefa().equals(oldTitle))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setNomeDaTarefa(tarefa.getNomeDaTarefa());
            inDataset.setPrioridade(tarefa.isPrioridade());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Tarefa article) {
        return dataset.remove(article);
    }

    @Override
    public Tarefa findByTitle(String title) {
        return dataset.stream()
                .filter(tarefa -> tarefa.getNomeDaTarefa().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Tarefa> findAll() {
        return dataset;
    }
}

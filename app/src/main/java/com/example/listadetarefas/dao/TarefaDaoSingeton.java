package com.example.listadetarefas.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.utils.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TarefaDaoSingeton implements ITarefaDao {
    private static TarefaDaoSingeton instance = null;
    private List<Tarefa> dataset;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private TarefaDaoSingeton(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
        dataset = new ArrayList<>();
        loadDataFromSharedPreferences();
    }

    public static TarefaDaoSingeton getInstance(Context context){
        if (instance == null)
            instance = new TarefaDaoSingeton(context);
        return instance;
    }


    @Override
    public void create(Tarefa tarefa) {
        if(tarefa != null) {
            dataset.add(tarefa);
            saveDataToSharedPreferences();
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
            saveDataToSharedPreferences();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Tarefa article) {
        saveDataToSharedPreferences();
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
        dataset.sort((t1, t2) -> {
            boolean isPrioridade1 = t1.isPrioridade();
            boolean isPrioridade2 = t2.isPrioridade();

            if (isPrioridade1 && !isPrioridade2) {
                return -1; // t1 vem antes de t2
            } else if (!isPrioridade1 && isPrioridade2) {
                return 1; // t2 vem antes de t1
            } else {
                return 0; // Manter a ordem original
            }
        });
        return dataset;
    }
    private void loadDataFromSharedPreferences() {
        String tarefasJson = sharedPreferences.getString(Constant.DATABASE_SHARED, "");
        if (!tarefasJson.isEmpty()) {
            Tarefa[] tarefasArray = gson.fromJson(tarefasJson, Tarefa[].class);
            dataset.clear();
            for (Tarefa tarefa : tarefasArray) {
                dataset.add(tarefa);
            }
        }
    }

    private void saveDataToSharedPreferences() {
        String tarefasJson = gson.toJson(dataset);
        sharedPreferences.edit().putString(Constant.DATABASE_SHARED, tarefasJson).apply();
    }
}

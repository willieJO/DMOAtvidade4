package com.example.listadetarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.dao.ITarefaDao;
import com.example.listadetarefas.dao.TarefaDaoSingeton;
import com.example.listadetarefas.view.ListTarefaActivity;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.MainMVP;

public class MainPresenter implements  MainMVP.Presenter{
    private MainMVP.View view;
    private ITarefaDao dao;
    Tarefa article;
    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TarefaDaoSingeton.getInstance();
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), ListTarefaActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {

    }

    @Override
    public void favoriteArticle(Tarefa tarefa) {
        tarefa.setPrioridade(!tarefa.isPrioridade());
        dao.update(tarefa.getNomeDaTarefa(), tarefa);
    }
}

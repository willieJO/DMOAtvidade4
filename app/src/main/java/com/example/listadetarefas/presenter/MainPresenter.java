package com.example.listadetarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.dao.ITarefaDao;
import com.example.listadetarefas.dao.TarefaDaoSingeton;
import com.example.listadetarefas.utils.Constant;
import com.example.listadetarefas.view.CreateTarefaActivity;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.MainMVP;
import com.example.listadetarefas.view.DetailsActivity;
import com.example.listadetarefas.view.RecyclerViewItemClickListener;
import com.example.listadetarefas.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements  MainMVP.Presenter{
    private MainMVP.View view;
    private ITarefaDao dao;
    Tarefa article;
    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TarefaDaoSingeton.getInstance(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), CreateTarefaActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails(Tarefa article) {
        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        intent.putExtra(Constant.OBJETO_TAREFA, article);
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                article = dao.findAll().get(position);
                openDetails(article);

            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void favoriteArticle(Tarefa tarefa) {
        tarefa.setPrioridade(!tarefa.isPrioridade());
        dao.update(tarefa.getNomeDaTarefa(), tarefa);
    }

    @Override
    public void deleteTask(Tarefa task) {
        dao.delete(task);
    }
}

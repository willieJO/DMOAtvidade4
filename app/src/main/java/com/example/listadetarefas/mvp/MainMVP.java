package com.example.listadetarefas.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.model.Tarefa;

public interface MainMVP {
    interface View  {
        Context getContext();
        void finViewById();

        void setOnCLick();
    }
    interface Presenter {
        void deatach();
        void openDetails();

        void populateList(RecyclerView recyclerView);
        void favoriteArticle(Tarefa article);
    }
}

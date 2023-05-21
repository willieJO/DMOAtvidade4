package com.example.listadetarefas.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.listadetarefas.dao.ITarefaDao;
import com.example.listadetarefas.dao.TarefaDaoSingeton;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.TarefaCreateMVP;
import com.example.listadetarefas.utils.Constant;

import java.util.Date;

public class TarefaCreatePresenter implements TarefaCreateMVP.Presenter {
    private TarefaCreateMVP.View view;
    private Tarefa task;
    private ITarefaDao dao;

    public TarefaCreatePresenter(TarefaCreateMVP.View view, Context context) {
        this.view = view;
        task = null;
        dao = TarefaDaoSingeton.getInstance(context);
    }


    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String nomeTarefa;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            nomeTarefa = bundle.getString(Constant.ATTR_NOME);
            task = dao.findByTitle(nomeTarefa);
            view.updateUI(task.getNomeDaTarefa(),task.getDescricao(), task.isPrioridade());
        }
    }

    @Override
    public void saveTask(String nomeTarefa,String descricao, Boolean prioridade) {
        if(task == null){
            task = new Tarefa(nomeTarefa,descricao, prioridade,new Date());
            dao.create(task);
            view.showToast("Novo artigo adicionado com sucesso.");
            view.close();
        } else {
            String oldName = task.getNomeDaTarefa();
            Tarefa newArticle = new Tarefa(nomeTarefa, descricao, prioridade);
            if(dao.update(oldName, newArticle)){
                view.showToast("Artigo atualizado com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar o artigo.");
            }
        }
    }
}

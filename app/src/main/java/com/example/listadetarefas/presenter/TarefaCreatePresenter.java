package com.example.listadetarefas.presenter;

import android.os.Bundle;

import com.example.listadetarefas.dao.ITarefaDao;
import com.example.listadetarefas.dao.TarefaDaoSingeton;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.TarefaCreateMVP;
import com.example.listadetarefas.utils.Constant;

public class TarefaCreatePresenter implements TarefaCreateMVP.Presenter {
    private TarefaCreateMVP.View view;
    private Tarefa article;
    private ITarefaDao dao;

    public TarefaCreatePresenter(TarefaCreateMVP.View view) {
        this.view = view;
        article = null;
        dao = TarefaDaoSingeton.getInstance();
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String title;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            title = bundle.getString(Constant.ATTR_NOME);
            article = dao.findByTitle(title);
            view.updateUI(article.getNomeDaTarefa(), article.getNomeDaTarefa()); // alterar depois
        }
    }

    @Override
    public void saveArticle(String title, String url) {
        if(article == null){
            //New article
            article = new Tarefa(url, title);
            dao.create(article);
            view.showToast("Novo artigo adicionado com sucesso.");
            view.close();
        } else {
            String oldName = article.getNomeDaTarefa();
            Tarefa newArticle = new Tarefa(title ,article.isPrioridade());
            if(dao.update(oldName, newArticle)){
                view.showToast("Artigo atualizado com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar o artigo.");
            }
        }
    }
}

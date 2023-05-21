package com.example.listadetarefas.mvp;

import android.os.Bundle;

public interface TarefaCreateMVP {
    interface View {
        void updateUI(String title,String descr, Boolean isPrioridade);

        Bundle getBundle();

        void showToast(String message);

        void close();

        void findVyById();

        void setOnClick();
    }

    interface Presenter{
        void deatach();

        void verifyUpdate();

        void saveTask(String title,String description, Boolean prioridade);
    }
}

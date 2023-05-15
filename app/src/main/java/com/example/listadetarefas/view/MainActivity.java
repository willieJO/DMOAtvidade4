package com.example.listadetarefas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.listadetarefas.R;
import com.example.listadetarefas.mvp.MainMVP;
import com.example.listadetarefas.presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  implements MainMVP.View {

    private MainMVP.Presenter presenter;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
    }

    @Override
    protected  void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finViewById() {
        actionButton = findViewById(R.id.floatingActionButton);
    }

    @Override
    public void setOnCLick() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openDetails();
            }
        });
    }
}
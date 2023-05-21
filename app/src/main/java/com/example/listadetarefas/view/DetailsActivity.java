package com.example.listadetarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.TarefaDetailsMVP;
import com.example.listadetarefas.utils.Constant;

public class DetailsActivity extends AppCompatActivity implements TarefaDetailsMVP.View{

    EditText nomeTarefa;
    EditText descricaoTarefa;
    EditText horaDeCriacaoTarefa;
    CheckBox prioridadeTarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findById();
        montarDados();
    }

    @Override
    public void findById() {
        nomeTarefa = findViewById(R.id.detailsNome);
        descricaoTarefa = findViewById(R.id.detailsDescricao);
        horaDeCriacaoTarefa = findViewById(R.id.detailsDiaDeCriacao);
        prioridadeTarefa = findViewById(R.id.detailsPrioridade);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void montarDados() {
        Bundle bundle = getBundle();
        if (bundle != null) {
            Tarefa task = (Tarefa) bundle.getSerializable(Constant.OBJETO_TAREFA);
            if (task != null) {
                nomeTarefa.setText(task.getNomeDaTarefa());
                descricaoTarefa.setText(task.getDescricao());
                horaDeCriacaoTarefa.setText(task.getDataDeCriacao().toString());
                prioridadeTarefa.setChecked(task.isPrioridade());
            }
        }
    }
}
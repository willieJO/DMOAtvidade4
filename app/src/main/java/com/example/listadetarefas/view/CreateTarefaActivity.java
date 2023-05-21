package com.example.listadetarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.mvp.TarefaCreateMVP;
import com.example.listadetarefas.presenter.TarefaCreatePresenter;

public class CreateTarefaActivity extends AppCompatActivity implements TarefaCreateMVP.View{
    EditText nomeDaTarefa;
    EditText descricaoDaTarefa;
    CheckBox isPrioridade;
    TarefaCreateMVP.Presenter presenter;
    Button salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tarefa);
        presenter = new TarefaCreatePresenter(this,this.getApplicationContext());
        setToolbar();
        findVyById();
        setOnClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void updateUI(String title,String desc, Boolean url) {
        nomeDaTarefa.setText(title);
        descricaoDaTarefa.setText(desc);
        isPrioridade.setChecked(url);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    @Override
    public void findVyById() {
        nomeDaTarefa = findViewById(R.id.editNomeTarefa);
        isPrioridade = findViewById(R.id.checkPrioridade);
        descricaoDaTarefa = findViewById(R.id.descricaoTarefa);
        salvar = findViewById(R.id.saveButton);
    }

    @Override
    public void setOnClick() {
        salvar.setOnClickListener(view -> SalvarTitulo());
    }
    public void SalvarTitulo() {
        presenter.saveTask(
                nomeDaTarefa.getText().toString(),
                descricaoDaTarefa.getText().toString(),
                isPrioridade.isChecked());

    }
}
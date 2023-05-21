package com.example.listadetarefas.mvp;

import android.os.Bundle;

public interface TarefaDetailsMVP {
    interface  View {
        void findById();
        Bundle getBundle();
        void montarDados();
    }
}

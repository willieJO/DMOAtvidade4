package com.example.listadetarefas.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CombinedVibration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;
import com.example.listadetarefas.mvp.MainMVP;
import com.example.listadetarefas.utils.Constant;
import com.example.listadetarefas.view.CreateTarefaActivity;
import com.example.listadetarefas.view.DetailsActivity;
import com.example.listadetarefas.view.RecyclerViewItemClickListener;

import java.util.List;

public class ItemPocketRecyclerAdapter extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder>{
    private Context context;
    private MainMVP.Presenter presenter;
    private List<Tarefa> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Tarefa> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_tarefa, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarefa task = data.get(position);
        holder.titleTextView.setText(task.getNomeDaTarefa());

        if (task.isPrioridade()) {
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.RED));
        } else {
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.gray));
        }
        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartClick(task);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deletaClick(task);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateTarefaActivity.class);
                intent.putExtra(Constant.ATTR_NOME, task.getNomeDaTarefa());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }
    private void deletaClick(Tarefa task) {
        presenter.deleteTask(task);
        notifyDataSetChanged();
    }

    private void heartClick(Tarefa task){
        presenter.favoriteArticle(task);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTextView;

        public ImageView favoriteImageView;
        public ImageView delete;
        public ImageView edit;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tarefa_name);
            delete = itemView.findViewById(R.id.image_delete);
            edit = itemView.findViewById(R.id.image_edit);
            favoriteImageView = itemView.findViewById(R.id.image_prioridade);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}

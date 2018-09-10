package br.com.gew.smartplan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.holders.TurmaViewHolder;
import br.com.gew.smartplan.model.Turma;

public class TurmasAdapter extends RecyclerView.Adapter {

    List<Turma> turmas;
    private Context context;

    public TurmasAdapter(List<Turma> turmas, Context context) {
        this.turmas = turmas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_tabbed, parent, false);

        TurmaViewHolder turmaViewHolder = new TurmaViewHolder(view);
        return turmaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TurmaViewHolder turmaViewHolder = (TurmaViewHolder) holder;

        Turma turma = turmas.get(position);

        //turmaViewHolder.nome.setText(turma.getNome());
        //turmaViewHolder.sala.setText(turma.getSala());
    }

    @Override
    public int getItemCount() {
        return turmas.size();
    }
}

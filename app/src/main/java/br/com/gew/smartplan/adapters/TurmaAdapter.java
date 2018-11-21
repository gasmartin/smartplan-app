package br.com.gew.smartplan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.model.Turma;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.ViewHolder>{

    private static final String TAG = "TurmaAdapter";

    private Context context;
    private List<Turma> turmas;

    public TurmaAdapter(Context context, List<Turma> turmas) {
        this.context = context;
        this.turmas = turmas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.turma_itemlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Turma turma = turmas.get(position);

        holder.nomeTurma.setText(turma.getNome());
        holder.salaTurma.setText("Sala " + Integer.toString(turma.getSala()));
    }

    @Override
    public int getItemCount() {
        return (turmas == null) ? 0 : turmas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout parentLayout;
        private TextView nomeTurma;
        private TextView salaTurma;;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            nomeTurma = itemView.findViewById(R.id.nome_turma);
            salaTurma = itemView.findViewById(R.id.sala_turma);
        }
    }
}

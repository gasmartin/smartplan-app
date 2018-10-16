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

import br.com.gew.smartplan.R;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.ViewHolder>{

    private static final String TAG = "TurmaAdapter";

    private Context context;
    private ArrayList<String> nomes;
    private ArrayList<Integer> salas;

    public TurmaAdapter(Context context, ArrayList<String> nomes, ArrayList<Integer> salas) {
        this.context = context;
        this.nomes = nomes;
        this.salas = salas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.turma_itemlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.nomeTurma.setText(nomes.get(position));
        //if(holder.salaTurma == null) Log.d(TAG, "Masoq??");
        holder.salaTurma.setText(Integer.toString(salas.get(position)));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + nomes.get(position));
                Toast.makeText(context, nomes.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nomes.size();
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

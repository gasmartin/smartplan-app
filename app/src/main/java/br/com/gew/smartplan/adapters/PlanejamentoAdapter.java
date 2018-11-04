package br.com.gew.smartplan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.ViewHolder>{

    private static final String TAG = "PlanejamentoAdapter";

    public Context context;
    public List<Planejamento> planejamentos;

    public PlanejamentoAdapter(Context context, List<Planejamento> planejamentos) {
        this.context = context;
        this.planejamentos = planejamentos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planejamento_itemlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Planejamento p = planejamentos.get(position);

        holder.nomePlanejamento.setText(p.getNome());
        String aux = "";
        aux += p.getDataInicio().toString() + " até " + p.getDataFinal().toString();
        holder.periodoPlanejamento.setText(aux);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + planejamentos.get(position));
                Utils.showMessage(context, planejamentos.get(position).getNome(), Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return planejamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout parentLayout;
        private TextView nomePlanejamento;
        private TextView periodoPlanejamento;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout_plan);
            nomePlanejamento = itemView.findViewById(R.id.planejamento_nome);
            periodoPlanejamento = itemView.findViewById(R.id.planejamento_periodo);
        }
    }
}

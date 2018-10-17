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

import br.com.gew.smartplan.R;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.ViewHolder>{

    private static final String TAG = "PlanejamentoAdapter";

    public Context context;
    public ArrayList<String> nomes;

    public PlanejamentoAdapter(Context context, ArrayList<String> nomes) {
        this.context = context;
        this.nomes = nomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planejamento_itemlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.nomePlanejamento.setText(nomes.get(position));

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
        private TextView nomePlanejamento;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout_plan);
            nomePlanejamento = itemView.findViewById(R.id.planejamento_nome);
        }
    }
}

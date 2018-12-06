package br.com.gew.smartplan.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.activities.AddPlanejamentoActivity;
import br.com.gew.smartplan.activities.PlanejamentoActivity;
import br.com.gew.smartplan.fragments.PlanejamentoFragment;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import br.com.gew.smartplan.task.PlanejamentoTask;

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
        holder.nome.setText(p.getNome());
        holder.range.setText(p.getDataInicio() + " - " + p.getDataFinal());
        holder.constraintLayout.setOnClickListener(v -> {
            Intent planejamentoActivity = new Intent(context, PlanejamentoActivity.class);
            planejamentoActivity.putExtra("planejamento", p);
            context.startActivity(planejamentoActivity);
        });
        holder.update.setOnClickListener(view -> {
            Intent alterar = new Intent(context, AddPlanejamentoActivity.class);
            alterar.putExtra("planejamento", p);
            context.startActivity(alterar);
        });
        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Deletando planejamento...");
            builder.setMessage("Você deseja deletar este planejamento?");
            builder.setPositiveButton("Sim", (dialogInterface, i) -> {
                try {
                    new PlanejamentoTask.DeletePlanejamento().execute(Long.toString(p.getId())).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                planejamentos.remove(p);
                notifyItemChanged(position);
            }).setNegativeButton("Não", (dialogInterface, i) -> {});
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return planejamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayout;
        private TextView nome;
        private TextView range;
        private ImageButton update;
        private ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.plan_layout);
            nome = itemView.findViewById(R.id.plan_nome);
            range = itemView.findViewById(R.id.plan_range);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

package br.com.gew.smartplan.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.activities.AddTurmaActivity;
import br.com.gew.smartplan.model.Turma;
import br.com.gew.smartplan.task.TurmaTask;

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
        Turma t = turmas.get(position);

        holder.nome.setText(t.getNome());
        holder.sala.setText("Sala " + t.getSala());

        holder.update.setOnClickListener(v -> {
            Intent alterar = new Intent(context, AddTurmaActivity.class);
            alterar.putExtra("turma", t);
            context.startActivity(alterar);
        });

        holder.delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Deletando turma...");
            builder.setMessage("Você deseja deletar esta turma?");
            builder.setPositiveButton("Sim", (dialogInterface, i) -> {
                try {
                    new TurmaTask.DeleteTurma().execute(Long.toString(t.getId())).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                turmas.remove(t);
                notifyItemChanged(position);
            }).setNegativeButton("Não", (dialogInterface, i) -> {});
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return (turmas == null) ? 0 : turmas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraint;
        private TextView nome;
        private  TextView sala;
        private ImageButton update;
        private ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);
            constraint = itemView.findViewById(R.id.turma_layout);
            nome = itemView.findViewById(R.id.turma_nome);
            sala = itemView.findViewById(R.id.turma_sala);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

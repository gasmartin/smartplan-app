package br.com.gew.smartplan.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.activities.CadastroActivity;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.task.AlunoTask;
import br.com.gew.smartplan.task.PlanejamentoTask;
import br.com.gew.smartplan.task.ProfessorTask;
import br.com.gew.smartplan.task.TurmaTask;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private CircleImageView img;
    private TextView nome;
    private TextView email;
    private TextView totalPlanejamentos;
    private TextView totalTurmas;
    private TextView totalAlunos;
    private ImageButton edit;

    private long id;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        img = view.findViewById(R.id.img_professor);
        nome = view.findViewById(R.id.nome_professor);
        email = view.findViewById(R.id.email_professor);
        totalPlanejamentos = view.findViewById(R.id.number_planejamentos);
        totalTurmas = view.findViewById(R.id.number_turmas);
        totalAlunos = view.findViewById(R.id.number_alunos);
        edit = view.findViewById(R.id.edit);

        Professor professor = null;
        Integer totalP = 0, totalT = 0, totalA = 0;

        try {
            professor = new ProfessorTask.GetProfessor().execute(id).get();
            totalP = new PlanejamentoTask.CountPlanejamento().execute(Long.toString(id)).get();
            totalT = new TurmaTask.CountTurma().execute(Long.toString(id)).get();
            totalA = new AlunoTask.CountAluno().execute(Long.toString(id)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        nome.setText(professor.getNome());
        email.setText(professor.getEmail());
        totalPlanejamentos.setText(Integer.toString(totalP));
        totalTurmas.setText(Integer.toString(totalT));
        totalAlunos.setText(Integer.toString(totalA));

        Professor finalProfessor = professor;
        edit.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), CadastroActivity.class);
            intent.putExtra("professor", finalProfessor);
            startActivity(intent);
        });

    }
}

package br.com.gew.smartplan.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.TurmaAdapter;
import br.com.gew.smartplan.model.Turma;

import static android.content.Context.MODE_PRIVATE;

public class TurmaFragment extends Fragment {

    private static final String TAG = "TurmaFragment";

    RecyclerView rvTurmas;

    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayList<Integer> salas = new ArrayList<>();

    private Long id;

    public TurmaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_turma, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTurmas = getView().findViewById(R.id.rv_turmas);

        SharedPreferences preferences = getContext().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = preferences.getLong("professor_id", 0);

        List<Turma> turmaList = null;

        //Retornar lista

        if(turmaList != null){
            for (Turma turma : turmaList){
                nomes.add(turma.getNome());
                salas.add(turma.getSala());
            }
        }

        Log.d("Fragment", Integer.toString(nomes.size()));
        Log.d("Fragment", Integer.toString(salas.size()));

        TurmaAdapter adapter = new TurmaAdapter(getContext(), nomes, salas);
        rvTurmas.setAdapter(adapter);
        rvTurmas.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

package br.com.gew.smartplan.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.PlanejamentoAdapter;
import br.com.gew.smartplan.model.Planejamento;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanejamentoFragment extends Fragment {

    private static final String TAG = "PlanejamentoFragment";
    private List<Planejamento> planejamentos = new ArrayList<>();

    RecyclerView recyclerView;

    public PlanejamentoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_planejamento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.rv_planejamentos);

        SharedPreferences preferences = getContext().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        Long id = preferences.getLong("professor_id", 0);

        List<Planejamento> planejamentoList = new ArrayList<>();

        //Retornar lista

        if(planejamentos != null){
            PlanejamentoAdapter adapter = new PlanejamentoAdapter(getContext(), planejamentos);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else{
            Toast.makeText(getContext(), "Tá vazio, jão", Toast.LENGTH_SHORT).show();
        }
    }
}

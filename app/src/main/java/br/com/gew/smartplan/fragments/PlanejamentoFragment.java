package br.com.gew.smartplan.fragments;


import android.content.SharedPreferences;
import android.media.Image;
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
import br.com.gew.smartplan.tasks.PlanejamentoListTask;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanejamentoFragment extends Fragment {

    private static final String TAG = "PlanejamentoFragment";

    private ArrayList<String> nomes = new ArrayList<>();

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

        List<Planejamento> planejamentoList = null;

        try {
            planejamentoList = new PlanejamentoListTask().execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(planejamentoList != null){
            for (Planejamento planejamento : planejamentoList){
                nomes.add(planejamento.getNome());
            }
        }
        else{
            Toast.makeText(getContext(), "Tá vazio, jão", Toast.LENGTH_SHORT).show();
        }

        PlanejamentoAdapter adapter = new PlanejamentoAdapter(getContext(), nomes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

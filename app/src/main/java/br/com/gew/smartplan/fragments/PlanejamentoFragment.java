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
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.PlanejamentoAdapter;
import br.com.gew.smartplan.helpers.Utils;
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
    private List<Planejamento> planejamentos = new ArrayList<>();

    RecyclerView recyclerView;

    Button botaoTeste;

    public PlanejamentoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.vp_planejamento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.rv_planejamentos);
        botaoTeste = getView().findViewById(R.id.botaoTeste);
        botaoTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showMessage(getContext(), "Foi!", 2);
            }
        });

        SharedPreferences preferences = getContext().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        Long id = preferences.getLong("professor_id", 0);

        List<Planejamento> planejamentoList = new ArrayList<>();

        try {
            planejamentos = new PlanejamentoListTask().execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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

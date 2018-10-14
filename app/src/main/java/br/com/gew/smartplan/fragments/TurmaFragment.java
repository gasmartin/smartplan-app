package br.com.gew.smartplan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.TurmaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TurmaFragment extends Fragment {

    private static final String TAG = "TurmaFragment";

    RecyclerView rvTurmas;

    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayList<Integer> salas = new ArrayList<>();

    public TurmaFragment() {

    }

    private void init(){
        nomes.add("IINF31-A");
        salas.add(13);

        nomes.add("IINF31-B");
        salas.add(14);

        nomes.add("IINF21-A");
        salas.add(8);

        nomes.add("IINF21-B");
        salas.add(9);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started.");

        rvTurmas = getView().findViewById(R.id.rv_turmas);

        init();
        //TurmaAdapter adapter = new TurmaAdapter(getContext(), nomes, salas);
        //rvTurmas.setAdapter(adapter);
        //rvTurmas.setLayoutManager(new LinearLayoutManager(getContext()));

        return inflater.inflate(R.layout.fragment_turma, container, false);
    }

}

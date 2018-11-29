package br.com.gew.smartplan.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.Button;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.activities.AddTurmaActivity;
import br.com.gew.smartplan.adapters.TurmaAdapter;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Turma;

import static android.content.Context.MODE_PRIVATE;

public class TurmaFragment extends Fragment {

    private static final String TAG = "TurmaFragment";

    RecyclerView rvTurmas;
    List<Turma> turmas;
    Button add;

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

        add = getView().findViewById(R.id.add);
        add.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), AddTurmaActivity.class));
        });

        SharedPreferences preferences = getContext().getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = preferences.getLong("professor_id", 0);

        try {
            turmas = new GetTurmas().execute(id).get();
            Log.d("DEBUG:", turmas.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(turmas == null) Utils.showMessage(getContext(), "null", 0);
        else Utils.showMessage(getContext(), "não null", 0);

        TurmaAdapter adapter = new TurmaAdapter(getContext(), turmas);
        rvTurmas.setAdapter(adapter);
        rvTurmas.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private class GetTurmas extends AsyncTask<Long, Void, List<Turma>>{
        @Override
        protected List<Turma> doInBackground(Long... longs) {
            return new ProfessorClient().getTurmasByProfessorId(longs[0]);
        }
        @Override
        protected void onPostExecute(List<Turma> turmas) {
            super.onPostExecute(turmas);
        }
    }
}

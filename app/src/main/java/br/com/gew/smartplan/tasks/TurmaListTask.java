package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.gew.smartplan.client.TurmaRestClient;
import br.com.gew.smartplan.model.Turma;

public class TurmaListTask extends AsyncTask<Long, Void, List<Turma>> {

    private TurmaRestClient turmaRestClient;

    @Override
    protected List<Turma> doInBackground(Long...longs) {
        turmaRestClient = new TurmaRestClient();
        return turmaRestClient.getAllTurmasByProfessorId(longs[0]);
    }

    @Override
    protected void onPostExecute(List<Turma> turmas) {
        super.onPostExecute(turmas);
    }
}

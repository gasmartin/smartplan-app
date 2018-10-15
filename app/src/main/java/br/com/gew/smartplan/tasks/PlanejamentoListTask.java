package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.gew.smartplan.client.PlanejamentoRestClient;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoListTask extends AsyncTask<Long, Void, List<Planejamento>> {

    private PlanejamentoRestClient planejamentoRestClient;

    @Override
    protected List<Planejamento> doInBackground(Long...longs) {
        planejamentoRestClient = new PlanejamentoRestClient();
        return planejamentoRestClient.getAllPlanejamentosByProfessorId(longs[0]);
    }

    @Override
    protected void onPostExecute(List<Planejamento> planejamentos) {
        super.onPostExecute(planejamentos);
    }
}

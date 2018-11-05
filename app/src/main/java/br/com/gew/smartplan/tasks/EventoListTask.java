package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.gew.smartplan.client.PlanejamentoRestClient;
import br.com.gew.smartplan.model.Evento;

public class EventoListTask extends AsyncTask<Long, Void, List<Evento>> {

    private PlanejamentoRestClient planejamentoRestClient;

    @Override
    protected List<Evento> doInBackground(Long... longs) {
        planejamentoRestClient = new PlanejamentoRestClient();
        return planejamentoRestClient.getEventosByPlanejamentoId(longs[0]);
    }

    @Override
    protected void onPostExecute(List<Evento> resultado) {
        super.onPostExecute(resultado);
    }
}

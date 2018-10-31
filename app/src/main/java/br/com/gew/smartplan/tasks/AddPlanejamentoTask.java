package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.PlanejamentoRestClient;

public class AddPlanejamentoTask extends AsyncTask<String, Void, Boolean> {

    private PlanejamentoRestClient planejamentoRestClient;

    @Override
    protected Boolean doInBackground(String... strings) {
        planejamentoRestClient = new PlanejamentoRestClient();
        return planejamentoRestClient.insertPlanejamento(strings[0], strings[1], strings[2], strings[3], strings[4], Long.parseLong(strings[5]));
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        super.onPostExecute(resultado);
    }
}

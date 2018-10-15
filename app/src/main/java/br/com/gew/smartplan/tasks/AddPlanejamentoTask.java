package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.PlanejamentoRestClient;

public class AddPlanejamentoTask extends AsyncTask<String, Void, Boolean> {

    private PlanejamentoRestClient planejamentoRestClient;

    @Override
    protected Boolean doInBackground(String...strings) {
        planejamentoRestClient = new PlanejamentoRestClient();
        return planejamentoRestClient.insertPlanejamento(Integer.parseInt(strings[0]), strings[1], strings[2], Long.parseLong(strings[3]));
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        super.onPostExecute(resultado);
    }
}

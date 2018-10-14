package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.TurmaRestClient;

public class AddTurmaTask extends AsyncTask<String, Void, Boolean> {

    private TurmaRestClient turmaRestClient;

    @Override
    protected Boolean doInBackground(String...strings) {
        turmaRestClient = new TurmaRestClient();
        return turmaRestClient.insertTurma(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), strings[2], strings[3], Long.parseLong(strings[4]));
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        super.onPostExecute(resultado);
    }
}

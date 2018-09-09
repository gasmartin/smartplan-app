package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.ProfessorRestClient;

public class RegisterTask extends AsyncTask<String, Void, Boolean> {

    private ProfessorRestClient professorRestClient;

    @Override
    protected Boolean doInBackground(String...strings) {
        professorRestClient = new ProfessorRestClient();
        return professorRestClient.insertProfessor(strings[0], strings[1], strings[2]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }
}

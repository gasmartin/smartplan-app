package br.com.gew.smartplan.tasks;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.ProfessorRestClient;
import br.com.gew.smartplan.model.Professor;

public class LoginTask extends AsyncTask<String, Void, Professor> {

    private ProfessorRestClient professorRestClient;

    @Override
    protected Professor doInBackground(String...strings) {
        professorRestClient = new ProfessorRestClient();
        return professorRestClient.executarLogin(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(Professor professor) {
        super.onPostExecute(professor);
    }
}

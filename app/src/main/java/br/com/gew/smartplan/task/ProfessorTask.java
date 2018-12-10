package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.model.Professor;

public class ProfessorTask {

    public static class AddProfessor extends AsyncTask<String, Void, Professor> {
        @Override
        protected Professor doInBackground(String... strings) {
            return new ProfessorClient().cadastrar(new Professor(strings[0], strings[1]));
        }

        @Override
        protected void onPostExecute(Professor p) {
            super.onPostExecute(p);
        }
    }

    public static class GetProfessor extends AsyncTask<Long, Void, Professor>{
        @Override
        protected Professor doInBackground(Long... longs) {
            return new ProfessorClient().getProfessor(longs[0]);
        }
    }

    public static class PutProfessor extends AsyncTask<Professor, Void, Void>{
        @Override
        protected Void doInBackground(Professor... professores) {
            new ProfessorClient().alterarDados(professores[0]);
            return null;
        }
    }
}

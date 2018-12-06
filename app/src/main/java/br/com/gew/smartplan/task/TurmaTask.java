package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.client.TurmaClient;
import br.com.gew.smartplan.model.Turma;

public class TurmaTask {

    public static class InsertTurma extends AsyncTask<String, Void, Turma>{
        @Override
        protected Turma doInBackground(String... strings) {
            return new TurmaClient().insert(strings[0], new Turma(Integer.parseInt(strings[1]), strings[2], null));
        }
        @Override
        protected void onPostExecute(Turma t) {
            super.onPostExecute(t);
        }
    }

    public static class GetTurmas extends AsyncTask<Long, Void, List<Turma>>{
        @Override
        protected List<Turma> doInBackground(Long... longs) {
            return new ProfessorClient().getTurmasByProfessorId(longs[0]);
        }
        @Override
        protected void onPostExecute(List<Turma> turmas) {
            super.onPostExecute(turmas);
        }
    }

    public static class UpdateTurma extends AsyncTask<Turma, Void, Void>{
        @Override
        protected Void doInBackground(Turma... turmas) {
            new TurmaClient().update(turmas[0]);
            return null;
        }
    }

    public static class DeleteTurma extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            new TurmaClient().delete(strings[0]);
            return null;
        }
    }
}

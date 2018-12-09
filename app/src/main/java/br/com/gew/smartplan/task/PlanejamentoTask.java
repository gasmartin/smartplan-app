package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.gew.smartplan.client.PlanejamentoClient;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoTask {

    public static class InsertPlanejamento extends AsyncTask<String, Void, Planejamento>{
        @Override
        protected Planejamento doInBackground(String... strings) {
            return new PlanejamentoClient().insert(strings[0], new Planejamento(strings[1], strings[2], strings[3], strings[4], null));
        }
        @Override
        protected void onPostExecute(Planejamento p) {
            super.onPostExecute(p);
        }
    }

    public static class GetPlanejamentos extends AsyncTask<Long, Void, List<Planejamento>>{
        @Override
        protected List<Planejamento> doInBackground(Long... longs) {
            return new ProfessorClient().getPlanejamentosByProfessorId(longs[0]);
        }
        @Override
        protected void onPostExecute(List<Planejamento> planejamentos) {
            super.onPostExecute(planejamentos);
        }
    }

    public static class UpdatePlanejamento extends AsyncTask<Planejamento, Void, Void>{
        @Override
        protected Void doInBackground(Planejamento... planejamentos) {
            new PlanejamentoClient().update(planejamentos[0]);
            return null;
        }
    }

    public static class DeletePlanejamento extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            new PlanejamentoClient().delete(strings[0]);
            return null;
        }
    }

    public static class CountPlanejamento extends AsyncTask<String, Void, Integer>{
        @Override
        protected Integer doInBackground(String... strings) {
            return new PlanejamentoClient().count(strings[0]);
        }
    }

}

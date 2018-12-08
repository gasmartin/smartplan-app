package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.model.Professor;

public class ProfessorTask {

    public static class GetProfessor extends AsyncTask<Long, Void, Professor>{
        @Override
        protected Professor doInBackground(Long... longs) {
            return new ProfessorClient().getProfessor(longs[0]);
        }
    }
}

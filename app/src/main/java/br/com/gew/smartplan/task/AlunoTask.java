package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.AlunoClient;

public class AlunoTask {

    public static class CountAluno extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {
            return new AlunoClient().count(strings[0]);
        }
    }
}

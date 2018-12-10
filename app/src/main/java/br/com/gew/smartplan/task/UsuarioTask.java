package br.com.gew.smartplan.task;

import android.os.AsyncTask;

import br.com.gew.smartplan.client.UsuarioClient;
import br.com.gew.smartplan.model.Usuario;

public class UsuarioTask {

    public static class AddUsuario extends AsyncTask<String, Void, Usuario> {

        @Override
        protected Usuario doInBackground(String... strings) {
            return new UsuarioClient().insert(strings[0], new Usuario(strings[1], strings[2]));
        }

        @Override
        protected void onPostExecute(Usuario u) {
            super.onPostExecute(u);
        }
    }

}

package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.client.UsuarioClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        try {
            if (new Login().execute(preferences.getString("professor_username", ""), preferences.getString("professor_password", "")).get() != null) {
                Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeActivity);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        txtUser = findViewById(R.id.txtUser);
        txtSenha = findViewById(R.id.txtSenha);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {

            Usuario u = null;
            try {
                u = new Login().execute(txtUser.getText().toString(), txtSenha.getText().toString()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(u != null){
                //MUDAR DEPOIS
                SharedPreferences sp = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                sp.edit().putLong("professor_id", u.getProfessor().getId())
                        .putString("professor_username", u.getUsername()).putString("professor_password", u.getPassword()).apply();

                Intent home = new Intent(this, HomeActivity.class);
                startActivity(home);
            }
            else{
                Utils.showMessage(getApplicationContext(), "Usuário não encontrado", 0);
            }
        });

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> {
            Intent telaCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(telaCadastrar);
            finish();
        });
    }

    private class Login extends AsyncTask<String, Void, Usuario>{

        @Override
        protected Usuario doInBackground(String... strings) {
            return new UsuarioClient().login(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Usuario u) {
            super.onPostExecute(u);
        }
    }
}

package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;

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
        Long verify = preferences.getLong("professor_id", 0);

        if (verify != 0) {
            Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivity);
        }

        txtUser = findViewById(R.id.txtUser);
        txtSenha = findViewById(R.id.txtSenha);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {

            Professor p = null;
            try {
                p = new Login().execute(txtUser.getText().toString(), txtSenha.getText().toString()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(p != null){
                SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.shared), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("professor_id", p.getId());
                editor.apply();

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
        });
    }

    private class Login extends AsyncTask<String, Void, Professor>{

        @Override
        protected Professor doInBackground(String... strings) {
            return new ProfessorClient().login(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Professor p) {
            super.onPostExecute(p);
        }
    }
}

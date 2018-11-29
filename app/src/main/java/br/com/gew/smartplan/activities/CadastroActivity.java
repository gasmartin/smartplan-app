package br.com.gew.smartplan.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.client.UsuarioClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtUser;
    private EditText txtSenha;
    private EditText txtConfirmar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastrar");

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtUser = findViewById(R.id.txtUser);
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmar = findViewById(R.id.txtConfirmar);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> {
            if (!"".equals(txtNome.getText().toString()) || !"".equals(txtUser.getText().toString()) ||
                    !"".equals(txtSenha.getText().toString()) || !"".equals(txtConfirmar.getText().toString())) {
                if (txtSenha.getText().toString().equals(txtConfirmar.getText().toString())) {

                    Professor p = null;
                    try {
                        p = new AddProfessor().execute(txtNome.getText().toString(), txtEmail.getText().toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    if (p != null) {
                        Usuario u = null;
                        try {
                            u = new AddUsuario().execute(Long.toString(p.getId()), txtUser.getText().toString(), txtSenha.getText().toString()).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        if(u != null){
                            SharedPreferences sp = getSharedPreferences(String.valueOf(R.string.shared), MODE_PRIVATE);
                            sp.edit().putLong("professor_id", p.getId()).putString("professor_username", u.getUsername())
                                    .putString("professor_password", u.getPassword()).apply();

                            Utils.showMessage(getApplicationContext(), "Oba!", 0);
                            Intent mainActivity = new Intent(this, MainActivity.class);
                            startActivity(mainActivity);
                            finish();
                        }
                    }
                } else {
                    Utils.showMessage(getApplicationContext(), "As senhas devem ser iguais.", 0);
                }
            } else {
                Utils.showMessage(getApplicationContext(), "Um campo obrigatório não está preenchido.", 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent mainActivity = new Intent(this, MainActivity.class);
                startActivity(mainActivity);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    private class AddProfessor extends AsyncTask<String, Void, Professor> {

        @Override
        protected Professor doInBackground(String... strings) {
            return new ProfessorClient().cadastrar(new Professor(strings[0], strings[1]));
        }

        @Override
        protected void onPostExecute(Professor p) {
            super.onPostExecute(p);
        }
    }

    private class AddUsuario extends AsyncTask<String, Void, Usuario> {

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


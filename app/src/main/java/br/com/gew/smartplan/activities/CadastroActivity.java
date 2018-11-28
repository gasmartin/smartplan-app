package br.com.gew.smartplan.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;

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
                    Professor p = new Professor(txtNome.getText().toString(), txtEmail.getText().toString());
                    try {
                        p = new AddProfessor().execute(p).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (p != null) {

                        //USAR O SHARED PREFERENCES AQUI E FAZER UM IF NA MAINACTIVITY

                        Utils.showMessage(getApplicationContext(), "Oba!", 0);
                        finish();
                    } else {
                        Utils.showMessage(getApplicationContext(), "Oops! É nulo.", 0);
                    }
                }
                else{
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AddProfessor extends AsyncTask<Professor, Void, Professor> {

        @Override
        protected Professor doInBackground(Professor... professores) {
            return new ProfessorClient().cadastrar(professores[0]);
        }

        @Override
        protected void onPostExecute(Professor p) {
            super.onPostExecute(p);
        }
    }
}


package br.com.gew.smartplan.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.client.UsuarioClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.model.Usuario;
import br.com.gew.smartplan.task.ProfessorTask;
import br.com.gew.smartplan.task.UsuarioTask;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtUser;
    private EditText txtSenha;
    private EditText txtConfirmar;
    private Button btnCadastrar;
    private TextView labelUsername;
    private TextView labelPassword;
    private TextView labelConfirm;

    private Bundle extras;

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
        labelUsername = findViewById(R.id.label_username);
        labelPassword = findViewById(R.id.label_password);
        labelConfirm = findViewById(R.id.label_confirm);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        extras = getIntent().getExtras();
        if(extras == null){
            btnCadastrar.setOnClickListener(v -> {
                if (!"".equals(txtNome.getText().toString()) || !"".equals(txtUser.getText().toString()) ||
                        !"".equals(txtSenha.getText().toString()) || !"".equals(txtConfirmar.getText().toString())) {
                    if (txtSenha.getText().toString().equals(txtConfirmar.getText().toString())) {

                        Professor p = null;
                        try {
                            p = new ProfessorTask.AddProfessor().execute(txtNome.getText().toString(), txtEmail.getText().toString()).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        if (p != null) {
                            Usuario u = null;
                            try {
                                u = new UsuarioTask.AddUsuario().execute(Long.toString(p.getId()), txtUser.getText().toString(), txtSenha.getText().toString()).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            if(u != null){
                                SharedPreferences sp = getSharedPreferences("UserPreferences", MODE_PRIVATE);
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
        else{
            Professor professor = (Professor) extras.getSerializable("professor");
            btnCadastrar.setText("Alterar");
            btnCadastrar.setEnabled(false);
            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    btnCadastrar.setEnabled(true);
                }
            };

            txtNome.setText(professor.getNome());
            txtNome.addTextChangedListener(watcher);

            txtEmail.setText(professor.getEmail());
            txtEmail.addTextChangedListener(watcher);

            labelUsername.setVisibility(View.INVISIBLE);
            txtUser.setEnabled(false);
            txtUser.setVisibility(View.INVISIBLE);

            labelPassword.setVisibility(View.INVISIBLE);
            txtSenha.setEnabled(false);
            txtSenha.setVisibility(View.INVISIBLE);

            labelConfirm.setVisibility(View.INVISIBLE);
            txtConfirmar.setEnabled(false);
            txtConfirmar.setVisibility(View.INVISIBLE);

            btnCadastrar.setOnClickListener(v -> {
                Professor professor2 = new Professor(txtNome.getText().toString(), txtEmail.getText().toString());
                professor2.setId(professor.getId());
                try {
                    new ProfessorTask.PutProfessor().execute(professor2).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                finish();
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(extras == null){
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                    finish();
                }
                else finish();
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

}


package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorRestClient;
import br.com.gew.smartplan.tasks.RegisterTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity {

    //Inicializando os componentes
    @BindView(R.id.txtNome) EditText txtNome;
    @BindView(R.id.txtUser) EditText txtUser;
    @BindView(R.id.txtSenha) EditText txtSenha;
    @BindView(R.id.txtConfirmar) EditText txtConfirmar;
    @BindView(R.id.btnCadastrar) Button btnCadastrar;
    @BindView(R.id.btnCancelar) Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Normal
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastrar");

        //ButterKnife
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCadastrar)
    public void cadastrarUsuario(View view){

        String nome, username, senha, confirmar;
        nome = txtNome.getText().toString();
        username = txtUser.getText().toString();
        senha = txtSenha.getText().toString();
        confirmar = txtConfirmar.getText().toString();


        if(senha.equals(confirmar)){
            try{
                if(new RegisterTask().execute(nome, username, senha).get()){
                    showMessage("Oba! Agora você pode fazer login!");
                    finish();
                }
                else showMessage("Retornou null");

            }
            catch (Exception ex){
                ex.printStackTrace();
                showMessage("Opa! Aconteceu alguma coisa estranha.");
            }
        }
        else{
            showMessage("As senhas precisam ser iguais.");
        }
    }

    @OnClick(R.id.btnCancelar)
    public void cancelar(View view){
        Intent mainActivity = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(mainActivity);
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

    private void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}


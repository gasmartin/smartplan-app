package br.com.gew.smartplan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.gew.smartplan.client.ProfessorRestClient;
import br.com.gew.smartplan.model.Professor;

public class ProfessorCadActivity extends AppCompatActivity {

    private ProfessorRestClient professorRestClient;

    private  android.widget.EditText campo_nome;
    private  android.widget.EditText campo_email;
    private  android.widget.EditText campo_senha;
    private  android.widget.EditText campo_confirmar;

    private  android.widget.Button cadastrar;
    private  android.widget.Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_cad);

        campo_nome = findViewById(R.id.campo_nome_cad);
        campo_email = findViewById(R.id.campo_email_cad);
        campo_senha = findViewById(R.id.campo_senha_cad);
        campo_confirmar = findViewById(R.id.campo_confirmar_cad);

        cadastrar = findViewById(R.id.btn_cadastrar);
        voltar = findViewById(R.id.btn_cancelar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome, email, senha, confirmar;
                nome = campo_nome.getText().toString();
                email = campo_email.getText().toString();
                senha = campo_senha.getText().toString();
                confirmar = campo_confirmar.getText().toString();


                if(senha.equals(confirmar)){
                    try{
                        if(new HttpAddProfessor().execute(nome, email, senha).get()){
                            showMessage("Oba! Agora você pode fazer login!");
                            finish();
                        }
                        else{
                            showMessage("Retornou null");
                        }
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
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class HttpAddProfessor extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String...strings) {
            professorRestClient = new ProfessorRestClient();
            return professorRestClient.insertProfessor(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }

    private void showMessage(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

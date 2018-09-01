package br.com.gew.smartplan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.gew.smartplan.client.ProfessorRestClient;

public class LoginActivity extends AppCompatActivity {

    private android.support.v7.widget.CardView btn_login;
    private android.support.v7.widget.CardView btn_cadastrar;

    private android.widget.EditText campo_email;
    private android.widget.EditText campo_senha;

    private ProfessorRestClient professorRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        campo_email = findViewById(R.id.campo_email);
        campo_senha = findViewById(R.id.campo_senha);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, senha;
                email = campo_email.getText().toString();
                senha = campo_senha.getText().toString();

                if("".equals(email) || "".equals(senha)){
                    showMessage("Todos os campos devem ser preenchidos.");
                }
                else{
                    Boolean result = false;
                    try{
                        result = new HttpLogin().execute(email, senha).get();
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                    if(result){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else{
                        showMessage("E-mail ou senha incorretos.");
                    }
                }
            }
        });

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }

    private void showMessage(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private class HttpLogin extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String...strings) {
            professorRestClient = new ProfessorRestClient();
            return professorRestClient.executarLogin(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }

}

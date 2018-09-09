package br.com.gew.smartplan.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorRestClient;
import br.com.gew.smartplan.tasks.RegisterTask;

public class CadastroActivity extends AppCompatActivity {

    private  android.widget.EditText campo_nome;
    private  android.widget.EditText campo_email;
    private  android.widget.EditText campo_senha;
    private  android.widget.EditText campo_confirmar;

    private  android.support.v7.widget.CardView cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campo_nome = findViewById(R.id.campo_nome_cad);
        campo_email = findViewById(R.id.campo_email_cad);
        campo_senha = findViewById(R.id.campo_senha_cad);
        campo_confirmar = findViewById(R.id.campo_confirmar_cad);

        cadastrar = findViewById(R.id.btn_post);

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
                        if(new RegisterTask().execute(nome, email, senha).get()){
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
    }

    private void showMessage(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}


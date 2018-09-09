package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.tasks.LoginTask;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.CardView btn_login;
    private android.support.v7.widget.CardView btn_cadastrar;

    private android.widget.EditText campo_email;
    private android.widget.EditText campo_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        Long verificar = preferences.getLong("professor_id", 0);

        if(verificar != 0){
            showMessage("Tava salvo já, cara!");
            Intent tabbedActivity = new Intent(MainActivity.this, TabbedActivity.class);
            startActivity(tabbedActivity);
        }

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
                    Professor professor = Professor.getInstance();
                    try{
                        professor = new LoginTask().execute(email, senha).get();
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                    if(professor != null){

                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putLong("professor_id", professor.getId());
                        editor.putString("professor_name", professor.getNome());
                        editor.putString("professor_email", professor.getEmail());
                        editor.putString("professor_password", professor.getSenha());

                        editor.commit();

                        Intent tabbedScreen = new Intent(MainActivity.this, TabbedActivity.class);
                        startActivity(tabbedScreen);
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
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });
    }

    private void showMessage(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}

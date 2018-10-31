package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.tasks.LoginTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtUser)
    EditText txtUser;
    @BindView(R.id.txtSenha)
    EditText txtSenha;

    @BindView(R.id.btnEntrar)
    Button btnEntrar;
    @BindView(R.id.btnCadastrar)
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        Long verify = preferences.getLong("professor_id", 0);

        if (verify != 0) {
            Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivity);
        }
    }

    @OnClick(R.id.btnEntrar)
    public void fazerLogin(View view) {
        String username, senha;
        username = txtUser.getText().toString();
        senha = txtSenha.getText().toString();

        if ("".equals(username) || "".equals(senha)) {
            showMessage("Todos os campos devem ser preenchidos.");
        } else {
            Professor professor = Professor.getInstance();
            try {
                professor = new LoginTask().execute(username, senha).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (professor != null) {

                SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putLong("professor_id", professor.getId());
                editor.putString("professor_name", professor.getNome());
                editor.putString("professor_username", professor.getUsername());
                editor.putString("professor_password", professor.getSenha());

                editor.commit();

                Intent tabbedScreen = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(tabbedScreen);
                finish();
            } else {
                showMessage("E-mail ou senha incorretos.");
            }
        }
    }

    @OnClick(R.id.btnCadastrar)
    public void cadastrarUsuario(View view) {
        Intent telaCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(telaCadastrar);
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}

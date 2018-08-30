package br.com.gew.smartplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfessorCadActivity extends AppCompatActivity {
    //variaves de teste
    private  android.widget.EditText nome;
    private  android.widget.EditText email;
    private  android.widget.EditText senha;
    private  android.widget.EditText senhaConf;


    //butoes de test
    private  android.widget.Button confirmar;
    private  android.widget.Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_cad);
        //edit text
        nome=findViewById(R.id.campo_nome_cad);
        email=findViewById(R.id.campo_email_cad);
        senha=findViewById(R.id.campo_senha_cad);
        senhaConf=findViewById(R.id.campo_confirmar_cad);
        //botoes
        confirmar=findViewById(R.id.btn_cadastrar);
        voltar=findViewById(R.id.btn_cancelar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar senha
                //ir para tela cadastrado
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

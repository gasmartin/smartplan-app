package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;


public class AddTurmaActivity extends AppCompatActivity {

    private Long id;

    private Spinner cores;

    private EditText turmaNome;
    private EditText turmaSala;
    private EditText turmaDescricao;

    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turma);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Turma");

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        cores = findViewById(R.id.spinner_cores);

        turmaNome = findViewById(R.id.txt_turma_nome);
        turmaSala = findViewById(R.id.txt_turma_sala);
        turmaDescricao = findViewById(R.id.txt_turma_descricao);

        insert = findViewById(R.id.insert_turma);
        insert.setOnClickListener(v -> {

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
}

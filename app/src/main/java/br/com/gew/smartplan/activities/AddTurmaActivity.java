package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.TurmaClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import br.com.gew.smartplan.model.Turma;
import br.com.gew.smartplan.model.Usuario;
import br.com.gew.smartplan.task.TurmaTask;

public class AddTurmaActivity extends AppCompatActivity {

    private Long id;

    private EditText turmaNome;
    private EditText turmaSala;
    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turma);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Turma");

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        turmaNome = findViewById(R.id.txt_turma_nome);
        turmaSala = findViewById(R.id.txt_turma_sala);
        insert = findViewById(R.id.insert_turma);

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            insert.setOnClickListener(v -> {
                Turma turma = null;
                try {
                    turma = new TurmaTask.InsertTurma().execute(Long.toString(id), turmaSala.getText().toString(), turmaNome.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(turma != null){
                    Utils.showMessage(getApplicationContext(), "Inserido com sucesso!", 0);
                    finish();
                }
                else Utils.showMessage(getApplicationContext(), "Algum erro!", 0);
            });
        }
        else{
            Turma turma = (Turma) extras.getSerializable("turma");
            insert.setEnabled(false);
            insert.setText("Alterar");

            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    insert.setEnabled(true);
                }
            };

            turmaNome.setText(turma.getNome());
            turmaNome.addTextChangedListener(watcher);

            turmaSala.setText(Integer.toString(turma.getSala()));
            turmaSala.addTextChangedListener(watcher);

            insert.setOnClickListener(v -> {
                Turma turma2 = new Turma(Integer.parseInt(turmaSala.getText().toString()), turmaNome.getText().toString(), turma.getAlunos());
                turma2.setId(turma.getId());
                try {
                    new TurmaTask.UpdateTurma().execute(turma2).get();
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

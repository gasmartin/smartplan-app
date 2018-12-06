package br.com.gew.smartplan.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.PlanejamentoClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import br.com.gew.smartplan.task.PlanejamentoTask;

public class AddPlanejamentoActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtDescricao;
    private EditText dataInicio;
    private EditText dataFinal;
    private Button insert;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planejamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Planejamento");

        SharedPreferences sp = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = sp.getLong("professor_id", 0);

        txtNome = findViewById(R.id.txt_nome_planejamento);
        txtDescricao = findViewById(R.id.txt_descricao_planejamento);
        dataInicio = findViewById(R.id.data_inicio);
        dataFinal = findViewById(R.id.data_final);
        insert = findViewById(R.id.insert_planejamento);

        dataInicio.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                final Calendar c = Calendar.getInstance();
                new DatePickerDialog(AddPlanejamentoActivity.this, (view, year, month, dayOfMonth) -> {
                    c.set(year, month, dayOfMonth);
                    dataInicio.setText(Utils.dateToString(c.getTime()));
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dataFinal.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                final Calendar c = Calendar.getInstance();
                new DatePickerDialog(AddPlanejamentoActivity.this, (view, year, month, dayOfMonth) -> {
                    c.set(year, month, dayOfMonth);
                    dataFinal.setText(Utils.dateToString(c.getTime()));
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            insert.setOnClickListener(v -> {
                Planejamento planejamento = null;
                try {
                    planejamento = new PlanejamentoTask.InsertPlanejamento().execute(Long.toString(id), txtNome.getText().toString(), txtDescricao.getText().toString(),
                            dataInicio.getText().toString(), dataFinal.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (planejamento != null){
                    finish();
                }
                else{
                    Utils.showMessage(getBaseContext(), "error", 0);
                }
            });
        }
        else{
            Planejamento planejamento = (Planejamento) extras.getSerializable("planejamento");
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

            txtNome.setText(planejamento.getNome());
            txtNome.addTextChangedListener(watcher);

            txtDescricao.setText(planejamento.getDescricao());
            txtDescricao.addTextChangedListener(watcher);

            dataInicio.setText(planejamento.getDataInicio());
            dataInicio.addTextChangedListener(watcher);

            dataFinal.setText(planejamento.getDataFinal());
            dataFinal.addTextChangedListener(watcher);

            insert.setOnClickListener(v -> {
                Planejamento planejamento2 = new Planejamento(txtNome.getText().toString(), txtDescricao.getText().toString(),
                        dataInicio.getText().toString(), dataFinal.getText().toString(), planejamento.getEventos());
                planejamento2.setId(planejamento.getId());
                try {
                    new PlanejamentoTask.UpdatePlanejamento().execute(planejamento2).get();
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

package br.com.gew.smartplan.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.tasks.AddPlanejamentoTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPlanejamentoActivity extends AppCompatActivity {

    @BindView(R.id.txt_nome_planejamento)
    EditText txtNome;
    @BindView(R.id.txt_descricao_planejamento)
    EditText txtDescricao;
    @BindView(R.id.spinner_cores)
    Spinner spinner;
    @BindView(R.id.insert_planejamento)
    Button insert;

    EditText dataInicio;
    EditText dataFinal;

    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planejamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Planejamento");

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        dataInicio = findViewById(R.id.data_inicio);
        dataInicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    new DatePickerDialog(AddPlanejamentoActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            c.set(year, month, dayOfMonth);
                            dataInicio.setText(Utils.dateToString(c.getTime()));
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        dataFinal = findViewById(R.id.data_final);
        dataFinal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    new DatePickerDialog(AddPlanejamentoActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            c.set(year, month, dayOfMonth);
                            dataFinal.setText(Utils.dateToString(c.getTime()));
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        ButterKnife.bind(this);
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

    @OnClick(R.id.insert_planejamento)
    public void insertPlanejamento()
    {
        boolean resultado = false;
        try {
            resultado = new AddPlanejamentoTask().execute("0", txtNome.getText().toString(), txtDescricao.getText().toString(),
                    dataInicio.getText().toString(), dataFinal.getText().toString(), Long.toString(id)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(!resultado) Utils.showMessage(getBaseContext(), "Alguma coisa de errado deu", 0);
    }

    @OnClick({R.id.data_inicio, R.id.data_final})
    public void pickDate(final EditText txt){

    }
}

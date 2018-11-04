package br.com.gew.smartplan.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.squareup.timessquare.CalendarPickerView;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanejamentoActivity extends AppCompatActivity {

    private Planejamento planejamento;

    @BindView(R.id.calendar)
    public CalendarPickerView calendarPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento);

        planejamento = (Planejamento) getIntent().getExtras().getSerializable("planejamento");
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(planejamento.getNome());

        calendarPickerView.init(Utils.stringToDate(planejamento.getDataInicio()), Utils.stringToDate(planejamento.getDataFinal()));
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

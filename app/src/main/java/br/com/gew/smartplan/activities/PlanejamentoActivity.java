package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.ViewPagerAdapter;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Evento;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoActivity extends AppCompatActivity {

    private Planejamento planejamento;
    private Long id;

    private TabLayout tabLayout;
    private TabItem tabCalendario;
    private TabItem tabEventos;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento);

        Bundle extras = getIntent().getExtras();
        if(extras != null) planejamento = (Planejamento) extras.getSerializable("planejamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(planejamento.getNome());

        tabLayout = findViewById(R.id.tabLayout);
        tabCalendario = findViewById(R.id.tabCalendario);
        tabEventos = findViewById(R.id.tabEventos);
        vp = findViewById(R.id.vp);

        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), planejamento);
        vp.setAdapter(vpa);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
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

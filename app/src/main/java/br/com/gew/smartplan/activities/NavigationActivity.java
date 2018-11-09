package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.Consumer;

import java.util.ArrayList;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.fragments.PlanejamentoFragment;
import br.com.gew.smartplan.fragments.TurmaFragment;
import br.com.gew.smartplan.helpers.Utils;
import devlight.io.library.ntb.NavigationTabBar;

public class NavigationActivity extends AppCompatActivity {

    private ViewPager view;
    private NavigationTabBar ntb;
    private FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        btn = findViewById(R.id.add_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanejamento = new Intent(NavigationActivity.this, AddPlanejamentoActivity.class);
                startActivity(addPlanejamento);
            }
        });

        view = findViewById(R.id.vp_horizontal_ntb);
        view.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.planejamento),
                        Color.parseColor("#224373"))
                        .title("Planejamento")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_action_name),
                        Color.parseColor("#224373"))
                        .title("Turma")
                        .build()
        );

        ntb = findViewById(R.id.ntb_horizontal);
        ntb.setModels(models);
        ntb.setViewPager(view);
        ntb.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if(position == 0){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent addPlanejamento = new Intent(NavigationActivity.this, AddPlanejamentoActivity.class);
                            startActivity(addPlanejamento);
                        }
                    });
                }
                else if(position == 1){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent addTurma = new Intent(NavigationActivity.this, AddTurmaActivity.class);
                            startActivity(addTurma);
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        ntb.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ntb.getModels().size(); i++) {
                    final NavigationTabBar.Model model = ntb.getModels().get(i);
                    ntb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new PlanejamentoFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new TurmaFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}

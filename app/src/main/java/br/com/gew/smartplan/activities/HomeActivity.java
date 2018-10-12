package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.fragments.PlanejamentoFragment;
import br.com.gew.smartplan.fragments.TurmaFragment;

public class HomeActivity extends AppCompatActivity {

    private Fragment selectedFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new TurmaFragment();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new PlanejamentoFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        selectedFragment = new TurmaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_logout:
                SharedPreferences.Editor prefsEditor = getSharedPreferences("UserPreferences", MODE_PRIVATE).edit();
                prefsEditor.clear();
                prefsEditor.commit();
                Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

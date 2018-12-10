package br.com.gew.smartplan.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.gew.smartplan.fragments.CalendarFragment;
import br.com.gew.smartplan.fragments.EventosFragment;
import br.com.gew.smartplan.model.Planejamento;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private Planejamento planejamento;

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs, Planejamento planejamento) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.planejamento = planejamento;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle extras = new Bundle();
        extras.putSerializable("planejamento", planejamento);
        switch (position) {
            case 0:
                CalendarFragment cf = new CalendarFragment();
                cf.setArguments(extras);
                return cf;
            case 1:
                EventosFragment ef = new EventosFragment();
                ef.setArguments(extras);
                return ef;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

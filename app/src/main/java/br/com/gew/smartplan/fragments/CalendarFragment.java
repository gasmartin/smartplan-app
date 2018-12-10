package br.com.gew.smartplan.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.helpers.EventDecorator;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Evento;
import br.com.gew.smartplan.model.Planejamento;

import static br.com.gew.smartplan.R.color.colorPrimary;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private Planejamento planejamento;

    private MaterialCalendarView calendar;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null) planejamento = (Planejamento) bundle.getSerializable("planejamento");

        calendar = view.findViewById(R.id.calendar);

        Date dataInicio = Utils.stringToDate(planejamento.getDataInicio());
        Calendar cDataInicio = Calendar.getInstance();
        cDataInicio.setTime(dataInicio);
        calendar.state().edit().setMinimumDate(CalendarDay.from(cDataInicio.get(Calendar.YEAR), cDataInicio.get(Calendar.MONTH)+1, cDataInicio.get(Calendar.DAY_OF_MONTH))).commit();

        Date dataFinal = Utils.stringToDate(planejamento.getDataFinal());
        Calendar cDataFinal = Calendar.getInstance();
        cDataFinal.setTime(dataFinal);
        calendar.state().edit().setMaximumDate(CalendarDay.from(cDataFinal.get(Calendar.YEAR), cDataFinal.get(Calendar.MONTH)+1, cDataFinal.get(Calendar.DAY_OF_MONTH))).commit();

        Set<CalendarDay> dates = new HashSet<>();
        for(Evento e : planejamento.getEventos()){
            Log.d("Teste", e.getDataEvento());
            Calendar c = Calendar.getInstance();
            Date d = Utils.stringToDate(e.getDataEvento());
            c.setTime(d);
            dates.add(CalendarDay.from(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)));
        }
        calendar.addDecorator(new EventDecorator(R.color.green, dates));
    }

}

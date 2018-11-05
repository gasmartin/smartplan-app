package br.com.gew.smartplan.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DrawableUtils;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.EventDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.model.Evento;

public class Utils {

    public enum Cores{
        AZUl(0), VERDE(1), VERMELHO(2);

        public int value;

        Cores(int i) {
            this.value = i;
        }
    }

    //Avisos na tela
    public static void showMessage(Context context, String message,  int duration){
        Toast.makeText(context, message, duration).show();
    }

    //Converte uma objeto java.util.Date para java.lang.String
    public static String dateToString(Date date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    //Converte uma objeto java.lang.String para java.util.Date
    public static Date stringToDate(String s){
        try{
            return new SimpleDateFormat("dd/MM/yyyy").parse(s);
        }
        catch (ParseException ex){
            ex.printStackTrace();
        }

        return null;
    }

    //Retorna uma lista de EventDay, dado uma lista de Eventos.
    public static List<EventDay> toEventDay(Context context, List<Evento> eventos){
        List<EventDay> eventDays = new ArrayList<>();
        for(Evento e : eventos){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(stringToDate(e.getDataEvento()));
            eventDays.add(new EventDay(calendar, getCircleDrawableWithText(context, "Teste")));
        }
        return eventDays;
    }

    public static Drawable getCircleDrawableWithText(Context context, String string) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.shape);
        Drawable text = CalendarUtils.getDrawableText(context, string, null, android.R.color.white, 12);

        Drawable[] layers = {background, text};
        return new LayerDrawable(layers);
    }
}

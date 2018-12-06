package br.com.gew.smartplan.helpers;

import android.content.Context;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String BASE_URL = "http://192.168.56.1:3000/api/";

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
}

package br.com.gew.smartplan.helpers;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void showMessage(Context context, String message,  int duration){
        Toast.makeText(context, message, duration).show();
    }

    public static String dateToString(Date date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}

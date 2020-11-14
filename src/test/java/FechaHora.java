import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaHora {

    public Date[]  completarHoras() {
        Date[] list = new Date[24];
        String horaDia="";
        
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < 24; i++) {
            horaDia = i + ":00:00";
            try {
                list[i] = hourFormat.parse(horaDia);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}

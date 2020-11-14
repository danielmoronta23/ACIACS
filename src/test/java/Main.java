import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DateFormat hourFormat = new SimpleDateFormat("HH");
        FechaHora a = new FechaHora();
        Date[] aux = a.completarHoras();
        Date hora = new Date();
        try {
            String horaString = hourFormat.format(hora);
            Date horaAux = hourFormat.parse(hourFormat.format(hora));
            Date horaAcomprar = hourFormat.parse(hourFormat.format(aux[0]));
            System.out.println(">> "+horaString+"mdksna\n");
           // if(horaAux.compareTo(horaAcomprar)){
            String b = hourFormat.format(hora);
            int c = Integer.parseInt(b);
           System.out.println("ds,as"+c);

           // }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(hourFormat.format(aux[0])+hora);



    }
}

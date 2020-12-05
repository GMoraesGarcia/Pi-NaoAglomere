package DataHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Matheus
 */
public class DataHelper {

    private GregorianCalendar gc;
    private Date dataParaManipular;
    SimpleDateFormat formatter;

    public DataHelper(Date data) {
        this.formatter = new SimpleDateFormat("HH:mm");
        this.gc = new GregorianCalendar();
        this.gc.setTime(data);
        this.dataParaManipular = data;
    }

    public DataHelper(String data) {
        this.formatter = new SimpleDateFormat("HH:mm");
        try {
            this.gc = new GregorianCalendar();
            this.gc.setTime(formatter.parse(data));
            this.dataParaManipular = formatter.parse(data);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Date getData() {
        return this.dataParaManipular;
    }

    public int getMinutos() {
        return getCampo(Calendar.MINUTE);
    }

    public int getHoras() {
        return getCampo(Calendar.HOUR_OF_DAY);
    }

    private int getCampo(int tipoCampo) {
        return gc.get(tipoCampo);
    }

    public Integer comparar(String data) {
        try {
            return comparar(formatter.parse(data));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int comparar(Date data) {
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        return gc.compareTo(c);
    }

    public String getAsString() {
        return formatter.format(dataParaManipular);
    }

    public void adicionarHoras(int quantidade) {
        adicionar(quantidade, Calendar.HOUR_OF_DAY);
    }

    public void adicionarMinutos(int quantidade) {
        adicionar(quantidade, Calendar.MINUTE);
    }
    
    public void adicionarHorasEMinutos(int quantidadeHoras, int quantidadeMinutos) {
        adicionar(quantidadeHoras, Calendar.HOUR_OF_DAY);
        adicionar(quantidadeMinutos, Calendar.MINUTE);
    }

    private void adicionar(int quantidade, int tipoCampo) {
        gc.add(tipoCampo, quantidade);
        dataParaManipular = gc.getTime();
    }

}

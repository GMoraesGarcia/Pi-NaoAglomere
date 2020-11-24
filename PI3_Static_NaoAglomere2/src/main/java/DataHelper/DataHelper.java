/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        this.formatter = new SimpleDateFormat("hh:mm");
        this.gc = new GregorianCalendar();
        this.gc.setTime(data);
        this.dataParaManipular = data;
    }

    public DataHelper(String data) {
        this.formatter = new SimpleDateFormat("hh:mm");
        try {
            this.gc = new GregorianCalendar();
            this.gc.setTime(formatter.parse(data));
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
        return getCampo(Calendar.HOUR);
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

}

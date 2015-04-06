package msisproject1.com.project1;

import android.content.ContentValues;

import org.w3c.dom.Text;

/**
 * Created by shanky on 4/6/15.
 */
public class AssignShiftDB {

    DatePickerFragment dpf = new DatePickerFragment();
    TimePickerFragment tpf = new TimePickerFragment();
    public String _name;
    public int _id;
    public ContentValues _date = dpf.values_DatePickerFragment;
    public ContentValues _sTime = tpf.values_TimePickerFragment;
    public ContentValues _eTime = tpf.values_TimePickerFragment;

    /*public AssignShiftDB(int id, String name, Text date, int sTime, int eTime){

        _id = id;
        _name = name;
        _date = date;
        _sTime = sTime;
        _eTime = eTime;
    } */


    public int get_id(){
        return _id;
    }

    public String get_name(){
        return _name;
    }

    public ContentValues get_date(){
        return _date;
    }

    public ContentValues get_sTime(){
        return _sTime;
    }

    public ContentValues get_eTime(){
        return _eTime;
    }
}

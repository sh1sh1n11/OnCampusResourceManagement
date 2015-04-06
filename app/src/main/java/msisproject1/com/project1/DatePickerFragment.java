package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by shanky on 4/3/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    //public int Year, Month, Day;
    public ContentValues values_DatePickerFragment = new ContentValues();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


        values_DatePickerFragment.put("Day",dayOfMonth);
        values_DatePickerFragment.put("Month",monthOfYear);
        values_DatePickerFragment.put("Year",year);
    }
}

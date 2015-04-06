package msisproject1.com.project1;


import android.content.ContentValues;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.app.Dialog;
import android.os.Bundle;
import java.util.Calendar;
import android.text.format.DateFormat;

/**
 * Created by shanky on 4/3/15.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public ContentValues values_TimePickerFragment = new ContentValues();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user


        values_TimePickerFragment.put("Hour",hourOfDay);
        values_TimePickerFragment.put("Minute",minute);
    }
}
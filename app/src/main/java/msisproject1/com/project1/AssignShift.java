package msisproject1.com.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AssignShift extends Activity {

    Calendar calendar = Calendar.getInstance();
    Date date;
    String sTime ="";
    String eTime="";
    String itemSelectedInNameSpinner;
    String itemSelectedInIDSpinner;
    Spinner employeeNameSpinner, employeeIdSpinner;
    ImageButton pickDate, pickStartTime, pickEndTime, Confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_shift);
        initializeWidgets();
        spinnersUserInput();
        setListeners();

    }

    private void setListeners() {
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(AssignShift.this,dateListener,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        pickStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AssignShift.this,startTimeListener,calendar.get(Calendar.HOUR),calendar.get(calendar.MINUTE),false).show();
            }
        });

        pickEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AssignShift.this,EndTimeListener,calendar.get(Calendar.HOUR),calendar.get(calendar.MINUTE),false).show();
            }
        });
    }


    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            calendar.set(year,monthOfYear,dayOfMonth);
            date = calendar.getTime();

        }
    };

    TimePickerDialog.OnTimeSetListener startTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            sTime = sTime + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        }
    };

    TimePickerDialog.OnTimeSetListener EndTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(calendar.MINUTE, minute);
            eTime = eTime + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        }
    };


    private void initializeWidgets() {
        pickDate = (ImageButton) findViewById(R.id.AssignShiftPickDate);
        pickStartTime = (ImageButton) findViewById(R.id.AssignShiftPickStartTime);
        pickEndTime = (ImageButton) findViewById(R.id.AssignShiftPickEndTime);
        Confirm = (ImageButton) findViewById(R.id.assignShiftConfirm );
        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);
        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);
    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    private void addItemsToEmployeeIDSpinner() {

        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);
        List<String> labels = new ArrayList<String>();

        // Creating adapter for spinner
        ArrayAdapter<String> employeeIDSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        employeeIDSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        employeeIdSpinner.setAdapter(employeeIDSpinnerAdapter);

    }

    private void addItemsToEmployeeNameSpinner() {

        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);
        List<String> labels = new ArrayList<String>();

        // Creating adapter for spinner
        ArrayAdapter<String> employeeIDSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        employeeIDSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        employeeNameSpinner.setAdapter(employeeIDSpinnerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assign_shift, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void spinnersUserInput(){

        employeeNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                itemSelectedInNameSpinner = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

                itemSelectedInNameSpinner = null;
            }
        });

        employeeIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                itemSelectedInIDSpinner = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                itemSelectedInIDSpinner=null;
            }
        });
    }

    public void assignShiftOnConfirm(View view) {
    }



}
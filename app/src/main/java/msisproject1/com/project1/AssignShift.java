package msisproject1.com.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;


public class AssignShift extends Activity {

    Calendar calendar = Calendar.getInstance();
    Date date;
    String sTime ="";
    String eTime="";
    String itemSelectedInNameSpinner;
    String itemSelectedInIDSpinner;
    Spinner employeeNameSpinner, employeeIdSpinner;
    ImageButton pickDate, pickStartTime, pickEndTime, Confirm;
    TextView resultArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_shift);
        initializeWidgets();
        addItemsToEmployeeIDSpinner();
        addItemsToEmployeeNameSpinner();
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
        resultArea = (TextView) findViewById(R.id.resultArea);
    }


    private void addItemsToEmployeeIDSpinner() {

        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> employeeIDSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.userIDs, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        employeeIDSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        employeeIdSpinner.setAdapter(employeeIDSpinnerAdapter);

    }

    private void addItemsToEmployeeNameSpinner() {

        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> employeeNameSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.userNames, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        employeeNameSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        employeeNameSpinner.setAdapter(employeeNameSpinnerAdapter);

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
        resultArea.setText(itemSelectedInIDSpinner + " " + itemSelectedInNameSpinner + " " + date + " " + sTime + " " + eTime);
    }



}
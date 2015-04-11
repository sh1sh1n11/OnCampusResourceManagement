package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import msisproject1.com.project1.R;

public class AssignShift extends ActionBarActivity {
     SQLiteDatabase OnCampusResourceManagementDB = null;
    Calendar calendar = Calendar.getInstance();
    Date date;
    String sTime ="";
    String eTime="";

    Spinner employeeNameSpinner, employeeIdSpinner;
    Button pickDate, pickStartTime, pickEndTime, Confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_shift);

        addItemsToEmployeeNameSpinner();
        addItemsToEmployeeIDSpinner();
        intializeButtons();
        createDatabase();
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

    public void createDatabase(){
        try {
            OnCampusResourceManagementDB = this.openOrCreateDatabase("OnCampusResourceManagementDB",MODE_PRIVATE,null);
            OnCampusResourceManagementDB.execSQL("CREATE TABLE IF NOT EXISTS assignShift " + "(employeeId integer primary key autoincrement, " +
                    "employeeName VARCHAR, shiftDate text, startTime text, endTime text);");
            File database = getApplicationContext().getDatabasePath("OnCampusResourceManagementDB.db");
            if(database.exists()){
                Toast.makeText(this,"Database Created", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this,"Database Missing",Toast.LENGTH_SHORT).show();
            }
        }

        catch (Exception e){
            Log.e("AssignShift ERROR", "Error Creating Database");
        }
    }

    private void intializeButtons() {
        pickDate = (Button) findViewById(R.id.AssignShiftPickDate);
        pickStartTime = (Button) findViewById(R.id.AssignShiftPickStartTime);
        pickEndTime = (Button) findViewById(R.id.AssignShiftPickEndTime);
        Confirm = (Button) findViewById(R.id.assignShiftConfirm );
    }

    private void addItemsToEmployeeIDSpinner() {
        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);
        ArrayAdapter<CharSequence> employeeIDSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.userIDs, android.R.layout.simple_spinner_item);
        employeeIDSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        employeeIdSpinner.setAdapter(employeeIDSpinnerAdapter);
    }

    private void addItemsToEmployeeNameSpinner() {
        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);
        ArrayAdapter<CharSequence> employeeNameSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.userNames,android.R.layout.simple_spinner_item);
        employeeNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
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
        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);
        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);

        employeeNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        employeeIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void assignShiftOnConfirm(View view) {

    }
}

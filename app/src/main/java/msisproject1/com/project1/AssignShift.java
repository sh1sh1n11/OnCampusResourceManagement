package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AssignShift extends ActionBarActivity {

    Calendar calendar = Calendar.getInstance();

    Date date;
    String sTime ="";
    String eTime="";
    String itemSelectedInNameSpinner;
    String itemSelectedInIDSpinner;

    Spinner employeeNameSpinner, employeeIdSpinner;
    Button pickDate, pickStartTime, pickEndTime, Confirm;
    TextView result;
    ProgressBar pb;
    List<MyTask> tasks;
    List<assignShiftPlainOldJavaObjects> assignShiftPlainOldJavaObjectsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_shift);

        addItemsToEmployeeNameSpinner();
        addItemsToEmployeeIDSpinner();
        initializeWidgets();
        pb.setVisibility(View.INVISIBLE);
        createDatabase();
        spinnersUserInput();
        setListeners();
        checkOnlineInitializeAsyncTask();

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

    public void checkOnlineInitializeAsyncTask() {
        if (isOnline()) {
            requestData("http://localhost:63342/OnCampusResourceManagementPHP/assignShift.php");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    public void createDatabase(){

    }

    private void initializeWidgets() {
        pickDate = (Button) findViewById(R.id.AssignShiftPickDate);
        pickStartTime = (Button) findViewById(R.id.AssignShiftPickStartTime);
        pickEndTime = (Button) findViewById(R.id.AssignShiftPickEndTime);
        Confirm = (Button) findViewById(R.id.assignShiftConfirm );
        pb = (ProgressBar) findViewById(R.id.progressBar);
        result = (TextView) findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());
        employeeNameSpinner = (Spinner) findViewById(R.id.EmployeeNameSpinner);
        employeeIdSpinner = (Spinner) findViewById(R.id.EmployeeIDSpinner);
        tasks = new ArrayList<>();
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

    protected void updateDisplay() {

        if (assignShiftPlainOldJavaObjectsList != null) {
            for (assignShiftPlainOldJavaObjects as_POJO : assignShiftPlainOldJavaObjectsList) {
                result.append(as_POJO.getEmployee_name() + "\n");
            }
        }

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

    public void assignShiftOnConfirm(View view) {
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
//            updateDisplay("Starting task");

            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            updateDisplay();
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            assignShiftPlainOldJavaObjectsList = assignShiftJSONParser.parseFeed(result);
            updateDisplay();

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values)
        {
//            updateDisplay(values[0]);
        }

    }


}

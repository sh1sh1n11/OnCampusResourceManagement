package msisproject1.com.project1;


        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.view.View;
        import android.view.View.OnClickListener;
        import java.util.Calendar;
        import android.widget.DatePicker;
        import android.widget.TimePicker;
        import android.app.DatePickerDialog;
        import android.app.TimePickerDialog;
        import java.text.DateFormat;
        import android.widget.AdapterView;

        import com.parse.Parse;
        import com.parse.ParseObject;


public class cafe_hours extends ActionBarActivity {

    Button start_date;
    Button end_date;
    Button open_time;
    Button close_time;
    Spinner weekday;
    Button submit;

    TimePickerDialog.OnTimeSetListener tpd,tpd2;
    DatePickerDialog.OnDateSetListener dpd,dpd2;
    Calendar cal = Calendar.getInstance();
    DateFormat dt = DateFormat.getInstance();

    String date_update,end_date_up,start_date_up;
    String time_update,open_time_up,close_time_up;
    String selected_day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_hours);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "14cEQnuwGGemEz0qh5QVqiOe23YAKPRkxioNRKmo", "n1KXKovLsVwufDgIRU77kjAkhrd13OUEeI56gzRD");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        ParseObject cafe_hours_data_base = new ParseObject("cafe_hours_data_base");

        start_date = (Button) findViewById(R.id.ch_start_date_button);

        start_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new DatePickerDialog(cafe_hours.this,dpd,
                        cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                //start_date_up = date_update;


            }
        });

        end_date   = (Button) findViewById(R.id.ch_end_date_button);

        end_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new DatePickerDialog(cafe_hours.this,dpd2,
                        cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                //end_date_up = date_update;

            }
        });

        open_time = (Button) findViewById(R.id.ch_open_time_button);

        open_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(cafe_hours.this,tpd,
                        cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),
                        false).show();
                //open_time_up = time_update;

            }

        });


        close_time  = (Button) findViewById(R.id.ch_close_time_button);

        close_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(cafe_hours.this,tpd2,
                        cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),
                        false).show();
                //close_time_up = time_update;


            }
        });

        // Initialize the Spinner

        weekday = (Spinner) findViewById(R.id.ch_day_spinner);

        weekday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                selected_day =  adapter.getItemAtPosition(i).toString();

                System.out.print(selected_day+":selected_day \n");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }


        });

        // Initialize the submit button
        submit  = (Button) findViewById(R.id.ch_submit);

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
                System.out.print("Button pressed!!!!!! \n");
                // update to the data base
            }
        });


        dpd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //start_date_up = year+"/"+monthOfYear+"/"+dayOfMonth;
                start_date_up = monthOfYear+"/"+dayOfMonth+"/"+year;
                System.out.print(start_date_up+":start_date \n");
                start_date.setText(start_date_up);


            }
        };

        tpd = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker arg0, int hourofday, int minute) {
                // TODO Auto-generated method stub
                cal.set(Calendar.HOUR_OF_DAY, hourofday);
                cal.set(Calendar.MINUTE, minute);
                open_time_up = hourofday+":"+minute;
                System.out.print(open_time_up+":open_time \n");
                open_time.setText(open_time_up);
            }
        };

        dpd2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                end_date_up = monthOfYear+"/"+dayOfMonth+"/"+year;
                System.out.print(end_date_up+":end_date \n");
                end_date.setText(end_date_up);

            }
        };

        tpd2 = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker arg0, int hourofday, int minute) {
                // TODO Auto-generated method stub
                cal.set(Calendar.HOUR_OF_DAY, hourofday);
                cal.set(Calendar.MINUTE, minute);
                close_time_up = hourofday+":"+minute;
                System.out.print(close_time_up+":close_time \n");
                close_time.setText(close_time_up);
            }
        };


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cafe_hours, menu);
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
}
package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


public class Cancel_Cafe_Operating_Hours extends ActionBarActivity {
    EditText cafe_name;
    String cn;
    Button confirm;
    Button can_date;
    Button open_time;
    Button close_time;

    TimePickerDialog.OnTimeSetListener tpd,tpd2;
    DatePickerDialog.OnDateSetListener dpd2;
    Calendar cal = Calendar.getInstance();

    String can_date_up;
    String open_time_up,close_time_up;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel__cafe__operating__hours);

        cafe_name = (EditText)findViewById(R.id.ch_enter_cafe_name);
        cn=getIntent().getExtras().getString("cafe_name");
        cafe_name.setText(cn);


        can_date   = (Button) findViewById(R.id.ch_cancel_date_button);

        can_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new DatePickerDialog(Cancel_Cafe_Operating_Hours.this,dpd2,
                        cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                //can_date_up = date_update;

            }
        });

        open_time = (Button) findViewById(R.id.ch_open_time_button);

        open_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Cancel_Cafe_Operating_Hours.this,tpd,
                        cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),
                        false).show();
                //open_time_up = time_update;

            }

        });


        close_time  = (Button) findViewById(R.id.ch_close_time_button);

        close_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Cancel_Cafe_Operating_Hours.this,tpd2,
                        cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),
                        false).show();
                //close_time_up = time_update;


            }
        });


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
                if (monthOfYear == 12)
                    monthOfYear = 0;
                else
                    monthOfYear = monthOfYear + 1;
                can_date_up = monthOfYear+"/"+dayOfMonth+"/"+year; // due to bug in code
                System.out.print(can_date_up+":can_date \n");
                can_date.setText(can_date_up);

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


        // Button and intent for enter cafe hours
        final Intent confirm_cancellation = new Intent(this, ch_confirm_cancellation.class);

        confirm  = (Button) findViewById(R.id.ch_submit);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                confirm_cancellation.putExtra("cafe_name", cn);
                confirm_cancellation.putExtra("cancel_date",can_date_up);
                confirm_cancellation.putExtra("cancel_open_time",open_time_up);
                confirm_cancellation.putExtra("cancel_close_time",close_time_up);
                startActivity(confirm_cancellation);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cancel__cafe__operating__hours, menu);
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

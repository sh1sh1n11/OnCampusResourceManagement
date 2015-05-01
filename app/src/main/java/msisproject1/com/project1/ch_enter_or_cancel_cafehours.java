package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


public class ch_enter_or_cancel_cafehours extends ActionBarActivity {
    EditText cafe_name;
    String cn;
    Button create_cafe_hours;
    Button cancel_cafe_hours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_enter_or_cancel_cafehours);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ch_enter_or_cancel_cafehours, menu);

        cafe_name = (EditText)findViewById(R.id.ch_enter_cafe_name);
        cn=getIntent().getExtras().getString("cafe_name");
        cafe_name.setText(cn);

        // Button and intent for enter cafe hours
        final Intent cafe_hours = new Intent(this, cafe_hours.class);

        create_cafe_hours  = (Button) findViewById(R.id.ch_enter_cafe_hours);
        create_cafe_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                cafe_hours.putExtra("cafe_name", cn);
                startActivity(cafe_hours);
                //start_date_up = date_update;
            }
        });

        // Button and intent for cancel cafe hours

        final Intent canc_cafe_hours = new Intent(this,  Cancel_Cafe_Operating_Hours.class);

        cancel_cafe_hours   = (Button) findViewById(R.id.ch_cancel_cafe_hours);

        cancel_cafe_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                canc_cafe_hours.putExtra("cafe_name", cn);
                startActivity(canc_cafe_hours);
                //end_date_up = date_update;

            }
        });


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

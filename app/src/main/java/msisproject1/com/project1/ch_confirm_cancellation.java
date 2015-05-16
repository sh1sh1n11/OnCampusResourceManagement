package msisproject1.com.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ch_confirm_cancellation extends ActionBarActivity {
    Button yes_button;
    Button no_button;
    String cn;
    String start_date;
    String end_date;
    String cancel_date;
    String start_time;
    String end_time;
    String day_sel;
    SimpleDateFormat formatter ;
    Date date_start,date_end,date_cancel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_confirm_cancellation);

        final Calendar cal=Calendar.getInstance();
        formatter = new SimpleDateFormat("MM/dd/yy");
        
        Parse.initialize(this, "14cEQnuwGGemEz0qh5QVqiOe23YAKPRkxioNRKmo", "n1KXKovLsVwufDgIRU77kjAkhrd13OUEeI56gzRD");
        final ParseObject cancel_cafe_hours = new ParseObject("cafe_hours_data_base");


        cn=getIntent().getExtras().getString("cafe_name");
        cancel_date = getIntent().getExtras().getString("cancel_date");
        start_time = getIntent().getExtras().getString("cancel_open_time");
        end_time = getIntent().getExtras().getString("cancel_close_time");

        yes_button = (Button) findViewById(R.id.ch_yes_button);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View arg0) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("cafe_hours_data_base");
        query.whereEqualTo("cafe_name", cn);
        query.findInBackground(new FindCallback <ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {

                   Log.d("Cafehours", "Retrieved " + objects.size() + "cafehours");
                            for (ParseObject dealsObject : objects) {

                                start_date = dealsObject.getString("start_date");
                                end_date =  dealsObject.getString("end_date");
                                day_sel  =  dealsObject.getString("Selected_day");

                                try {
                                    date_start = formatter.parse(start_date);
                                    date_end = formatter.parse(end_date);
                                    date_cancel= formatter.parse(cancel_date);
                                    if(date_cancel.compareTo(date_end)<= 0  && date_cancel.compareTo(date_start)>= 0){
                                        cal.setTime(date_cancel);
                                        int val = cal.get(Calendar.DAY_OF_WEEK);
                                        String day_name = new DateFormatSymbols().getWeekdays()[val];

                                        if (day_name.equals(day_sel)) {
                                            cancel_cafe_hours.put("cafe_name", cn);
                                            cancel_cafe_hours.put("start_date", cancel_date);
                                            cancel_cafe_hours.put("end_date", cancel_date);
                                            cancel_cafe_hours.put("open_time", start_time);
                                            cancel_cafe_hours.put("close_time", end_time);
                                            cancel_cafe_hours.put("Selected_day", day_name);
                                            cancel_cafe_hours.saveInBackground();

                                        }
                                    }

                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                }


                            }
                } else {
                   Log.d("Cafehours", "Error: " + e.getMessage());
                }

            }


        });

                Toast toast = Toast.makeText(getApplicationContext(),cn+"operating hours cancelled",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                toast.show();

                //start_date_up = date_update;
            }
        });

        // Button and intent for cancel cafe hours

        final Intent canc_cafe_hours = new Intent(this,  Cancel_Cafe_Operating_Hours.class);

        no_button   = (Button) findViewById(R.id.ch_no_button_button);

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                canc_cafe_hours.putExtra("cafe_name", cn);
                startActivity(canc_cafe_hours);
                //end_date_up = date_update;

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ch_confirm_cancellation, menu);
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

package msisproject1.com.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ch_confirm_cancellation extends ActionBarActivity {
    Button yes_button;
    Button no_button;
    String cn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_confirm_cancellation);
        cn=getIntent().getExtras().getString("cafe_name");
        yes_button = (Button) findViewById(R.id.ch_yes_button);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Retrieve from Parse database,modify and update
                // TO DO

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

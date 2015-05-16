package msisproject1.com.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity2 extends ActionBarActivity {
    Button Missionbakery;
    Button Cadence;
    Button Sunstream;
    Button cellarmarket,marketplace,broncos;

    String menu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);

        Missionbakery = (Button) findViewById(R.id.button);
        final Intent enter_cancel_cafehrs = new Intent(this, ch_enter_or_cancel_cafehours.class);
        Missionbakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  Missionbakery.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
                           }
        });

        Cadence = (Button) findViewById(R.id.button2);
        Cadence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  Cadence.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
            }
        });

        Sunstream = (Button) findViewById(R.id.button3);
        Sunstream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  Sunstream.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
            }
        });

        
        cellarmarket = (Button) findViewById(R.id.button4);
        cellarmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  cellarmarket.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
            }
        });

        marketplace = (Button) findViewById(R.id.button5);
        marketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  marketplace.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
            }
        });

        broncos = (Button) findViewById(R.id.button6);
        broncos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu1 =  broncos.getText().toString();
                enter_cancel_cafehrs.putExtra("cafe_name", menu1);
                startActivity(enter_cancel_cafehrs);
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

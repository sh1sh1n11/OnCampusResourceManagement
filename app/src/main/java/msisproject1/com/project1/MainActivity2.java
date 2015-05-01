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

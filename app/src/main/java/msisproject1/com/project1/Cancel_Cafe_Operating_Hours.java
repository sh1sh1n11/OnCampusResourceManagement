package msisproject1.com.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Cancel_Cafe_Operating_Hours extends ActionBarActivity {
    EditText cafe_name;
    String cn;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel__cafe__operating__hours);

        cafe_name = (EditText)findViewById(R.id.ch_enter_cafe_name);
        cn=getIntent().getExtras().getString("cafe_name");
        cafe_name.setText(cn);

        // Button and intent for enter cafe hours
        final Intent confirm_cancellation = new Intent(this, ch_confirm_cancellation.class);

        confirm  = (Button) findViewById(R.id.ch_submit);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                confirm_cancellation.putExtra("cafe_name", cn);
                startActivity(confirm_cancellation);
                //start_date_up = date_update;
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

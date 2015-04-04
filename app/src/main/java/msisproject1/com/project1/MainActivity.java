package msisproject1.com.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;


public class MainActivity extends ActionBarActivity {

    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // links java code


        et= (EditText)findViewById(R.id.user_name_edit_id);
        tv = (TextView)findViewById(R.id.user_name_id);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "y051ITxINowivGF6GmsZSj4NvAkEjxVvVsgNZYn7", "jnRaigGT5uTFgkZUvblwkwmjhTJm4yVhLQv3jzvm");


    }

    public void submitFunction(View view ){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

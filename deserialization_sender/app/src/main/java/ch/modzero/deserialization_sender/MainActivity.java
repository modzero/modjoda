package ch.modzero.deserialization_sender;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import ch.modzero.deserialization_sender.exploits.ExploitListSingleton;

public class MainActivity extends AppCompatActivity {

    static String app_name = "@string/app_name";
    ListView exploitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        exploitListView = (ListView) findViewById(R.id.exploitListView);
        exploitListView.setAdapter(new ExploitAdapter(this));
        exploitListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        EditText mEdit = (EditText)findViewById(R.id.intentName);
        mEdit.setText("ch.modzero.intent_receiver.deserialize.pwn");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending Exploits", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                EditText mEdit = (EditText)findViewById(R.id.intentName);
                Intent intent = new Intent(mEdit.getText().toString());
                Bundle bundle = new Bundle();
                ExploitListSingleton.getInstance().runAllExploits(getApplicationContext(), intent, bundle);
            }
        });
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

        // exploit_serialized_crypto();

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

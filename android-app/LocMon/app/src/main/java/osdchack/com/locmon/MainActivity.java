package osdchack.com.locmon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import osdchack.com.locmon.Preferences.GuardDetails;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GuardDetails guardDetails = new GuardDetails(getApplicationContext());
        if(guardDetails.getIsGuardSet()==true)
        {
            Toast.makeText(getApplicationContext(),"welcome!"+guardDetails.getGuard().getUser_name(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ActiveGuard.class));
        }
        else
        {
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
    }
}

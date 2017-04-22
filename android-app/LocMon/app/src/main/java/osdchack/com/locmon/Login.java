package osdchack.com.locmon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import osdchack.com.locmon.Models.Guard;
import osdchack.com.locmon.Preferences.GuardDetails;

public class Login extends AppCompatActivity {

    EditText etUserName, etPassword;
    Button btn_login;
    Context context;
    String URL = "http://harshgoyal.xyz/locmon/api/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        etUserName = (EditText) findViewById(R.id.user_name);
        etPassword = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().length() == 0)
                    etPassword.setError("cannot be left blank");
                if (etUserName.getText().toString().length() == 0)
                    etPassword.setError("cannot be left blank");
                else {
                    makeRequest();
                }
            }

        });
    }

    private void makeRequest() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "userLogin");
        params.put("user_name", etUserName.getText().toString());
        params.put("password", etPassword.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("res", response.toString());
                        try {
                            if (response.toString().equals("true")) {
                                startActivity(new Intent(Login.this, Admin.class));
                                finish();
                            } else {
                                try {
                                    GuardDetails guardDetails = new GuardDetails(context);
                                    String id = response.getString("id");
                                    //guardDetails.setGuard(new Guard(id, etUserName.getText().toString(), etPassword.getText().toString()));
                                    guardDetails.setIsGuardSet(true);
                                    startActivity(new Intent(Login.this, ActiveGuard.class));
                                    finish();
                                } catch (JSONException e) {
                                    Log.i("error", e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    Log.i("error listener called", error.getMessage());
                } catch (Exception e) {
                    Log.i("error", e.getMessage());
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
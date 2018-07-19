package vishaljai.xtreme;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class signup extends AppCompatActivity {

    EditText edit_username;
    EditText edit_email;
    EditText edit_pass;
    Button btn_sign;
    Button btn_logi;
  //  private static final String REGISTER_URL="http://192.168.0.102/register.php";
    private static final String REGISTER_URL="https://virenjai.000webhostapp.com/UserRegistration/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edit_username=(EditText)findViewById(R.id.id_username);
        edit_email=(EditText)findViewById(R.id.id_email);
        edit_pass=(EditText)findViewById(R.id.id_passw);
        btn_sign=(Button)findViewById(R.id.btn_signup);



        btn_logi=(Button)findViewById(R.id.btn_loginn);
        btn_logi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj1=new Intent(signup.this,login.class);
                obj1.setFlags(obj1.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(obj1);
            }
        });


        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = edit_username.getText().toString().trim().toLowerCase();
        String email = edit_email.getText().toString().trim().toLowerCase();
        String password = edit_pass.getText().toString().trim().toLowerCase();
        register(username, password, email);
    }



    private void register(final String username, final String password, final String email){
        String urlSuffix = "?username=" + username + "&password=" + password + "&email=" + email;



        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(signup.this, "Please Wait", null, true, true);
            }


            @Override
            protected void onPostExecute(String s) {
String str="username or email already exist";
if(username.isEmpty() || password.isEmpty() ||email.isEmpty()){
    loading.dismiss();
    Toast.makeText(getApplicationContext(),"Please fill all the field", Toast.LENGTH_SHORT).show();
}
else {



        super.onPostExecute(s);
        loading.dismiss();
        Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();


        Thread t1 = new Thread() {
            @Override
            public void run() {
                //super.run();

                try {
                    sleep(3000);
                    Intent ob2 = new Intent(signup.this, login.class);

                    startActivity(ob2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();



}
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader=null;

                try {
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;

                    result=bufferReader.readLine();

                    return  result;


                }catch (Exception e){
                    return null;
                }
            }

        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlSuffix);
    }


}

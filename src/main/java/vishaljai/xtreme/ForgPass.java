package vishaljai.xtreme;

import android.app.ProgressDialog;
import android.content.Context;
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

public class ForgPass extends AppCompatActivity {

    Button btnsend;
    EditText emails;
    //EditText usernames;
    Context context;
 private static final String REGISTER_URLS = "https://virenjai.000webhostapp.com/UserRegistration/email/mail.php";
    //private static final String REGISTER_URLS="http://192.168.0.102/mail/mail.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forg_pass);
        btnsend = (Button) findViewById(R.id.send_pass);
        emails = (EditText) findViewById(R.id.emailss);
       // usernames=(EditText)findViewById(R.id.user_name);

btnsend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        registerUser();
    }
});

    }
    private void registerUser() {
      //  String username = usernames.getText().toString().trim().toLowerCase();
        String email = emails.getText().toString().trim().toLowerCase();
       // String password = edit_pass.getText().toString().trim().toLowerCase();
        register(email);
    }



    private void register(final String email){
        String urlSuffix = "email=" + email;



        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgPass.this, "Please Wait", null, true, true);
            }


            @Override
            protected void onPostExecute(String s) {
              //  String str="username or email already exist";
                if(email.isEmpty()){
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(),"Please fill all the field", Toast.LENGTH_SHORT).show();
                }
                else {



                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "email success", Toast.LENGTH_SHORT).show();


                    Thread t1 = new Thread() {
                        @Override
                        public void run() {
                            //super.run();

                            try {
                                sleep(3000);
                                Intent ob2 = new Intent(ForgPass.this, login.class);

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

                    URL url=new URL(REGISTER_URLS+s);
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

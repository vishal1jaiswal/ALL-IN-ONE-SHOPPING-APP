package vishaljai.xtreme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Asus on 18/12/2017.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    Context getContext;
    Activity getActivity;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
       //String login_url = "http://192.168.43.97/login.php";
        //String login_url = "http://192.168.0.102/login.php";
       String login_url = "https://virenjai.000webhostapp.com/UserRegistration/login.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }




    @Override
    protected void onPostExecute(String result) {
        if (result.length()>25) {
            Toast.makeText(context, "Login SuccessFull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, jest.class);
            context.startActivity(intent);


        } else {

            Toast.makeText(context, "Login Username or Password invalid", Toast.LENGTH_SHORT).show();

        }
     // alertDialog.setMessage(result);
      //  alertDialog.show();
       // Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

//count(result);
  /*     Thread th=new Thread(){
           @Override
           public void run() {
               // super.run();
               try {
                   sleep(3000);
                   Intent intent = new Intent(context, jest.class);
                   context.startActivity(intent);
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
           }
       };
       th.start();

*/

       // signup obj=new signup();
      // obj.getIntent();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

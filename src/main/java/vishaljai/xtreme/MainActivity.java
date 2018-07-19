package vishaljai.xtreme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

Button btn_logii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
btn_logii=(Button)findViewById(R.id.btn_logi);
btn_logii.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent obji=new Intent(MainActivity.this,signup.class);
        startActivity(obji);

    }
});

    }

}

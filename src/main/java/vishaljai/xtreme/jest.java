package vishaljai.xtreme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.Toast;

public class jest extends AppCompatActivity implements Amazon.OnFragmentInteractionListener,Flipkart.OnFragmentInteractionListener,snapdeal.OnFragmentInteractionListener,shopclues.OnFragmentInteractionListener,Paytm.OnFragmentInteractionListener{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
     WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jest);

        toolbar=(Toolbar)findViewById(R.id.toolBar);
        WebView webView;
        setSupportActionBar(toolbar);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
     //   viewPagerAdapter.addFragments(new Amazon(),"Google");
        viewPagerAdapter.addFragments(new Amazon(),"Amazon");
        viewPagerAdapter.addFragments(new Flipkart(),"Flipkart");
       viewPagerAdapter.addFragments(new snapdeal(),"Snapdeal");
       viewPagerAdapter.addFragments(new shopclues(),"Shopclues");
       viewPagerAdapter.addFragments(new Paytm(),"Paytm");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       Amazon webViewFragment = new Amazon();
        fragmentTransaction.replace(R.id.viewPager,webViewFragment);
        fragmentTransaction.commit();
        //tabLayout.canScrollHorizontally(TabLayout.GRAVITY_FILL);


    }

//WebView webView=(WebView)findViewById(R.id.webview);







    @Override
    public void onBackPressed() {
      if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.super_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_back:
                onBackPressed();
                break;
            case R.id.menu_for:
                onForwardPressed();
                break;
            case R.id.menu_ref:
                webView.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void onForwardPressed()
    {
        if(webView.canGoForward()){
            webView.goForward();
        }
        else
        {
            Toast.makeText(this,"cant go forward further",Toast.LENGTH_SHORT).show();
        }
    }



   /* @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }*/



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

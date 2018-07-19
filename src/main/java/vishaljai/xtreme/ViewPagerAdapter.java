package vishaljai.xtreme;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
/**
 * Created by Asus on 10/06/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> tabTitles=new ArrayList<>();
    public void addFragments(Fragment fragments,String titles){
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       // tabTitles.get(TabLayout.MODE_SCROLLABLE);

        return tabTitles.get(position);
    }

}

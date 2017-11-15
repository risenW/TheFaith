package com.afridevelopers.thefaith;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu_bars);
        getSupportActionBar().setDisplayShowTitleEnabled (false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //Method to Initialize the Navigation View
        setNavigationDrawer();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setNavigationDrawer() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
                int itemClick = item.getItemId();
                switch (itemClick){
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_profile:
                        Toast.makeText(MainActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_Messages:
                        viewPager.setCurrentItem(2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "Friends clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notifs:
                        viewPager.setCurrentItem(1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_Privacy_policies:
                        Toast.makeText(MainActivity.this, "Privacy clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this, "LogOut clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Closed ", Toast.LENGTH_SHORT).show();
                }

//                if (fragment != null){
//
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.nav_frame,fragment);
//                    transaction.commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }

                return false;
            }
        });
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Activity_fragment(), "Activity");
        adapter.addFrag(new Notifications_fragment(), "Notifications");
        adapter.addFrag(new messages_fragment(), "Messages");
        viewPager.setAdapter(adapter);
    }

    //Custom Fragment Adapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

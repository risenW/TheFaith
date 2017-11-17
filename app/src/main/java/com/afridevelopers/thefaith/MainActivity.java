package com.afridevelopers.thefaith;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView profilePic,coverpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Method to Initialize toolbar
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
        //Gets the header view and make it clickable
        View headerView = navigationView.getHeaderView(0);
        RelativeLayout header = (RelativeLayout)headerView.findViewById(R.id.view_container);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserProfileActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemClick = item.getItemId();
                switch (itemClick){
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_profile:
                        Intent intent1 = new Intent(MainActivity.this,UserProfileActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.nav_Messages:
                        viewPager.setCurrentItem(2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_friends:
                        Intent intent2 = new Intent(MainActivity.this,FriendListActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.nav_notifs:
                        viewPager.setCurrentItem(1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        Intent intent3 = new Intent(MainActivity.this,SettingsActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_Privacy_policies:
                        Intent intent4 = new Intent(MainActivity.this,PrivacyPolicies.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this, "LogOut clicked", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Closed ", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Activity_fragment(), "Activity");
        adapter.addFrag(new Notifications_fragment(), "Notifications");
        adapter.addFrag(new messages_fragment(), "Inbox");
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

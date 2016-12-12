package test.jinesh.customnavigation;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity implements CustomNavigationView.NavigationItemSelectedListner {
    ArrayList<String> menuList;
    ArrayAdapter<String> menuAdapter;
    CustomNavigationView navView;
    DrawerLayout drawerLayout;
    android.support.v4.app.FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = (CustomNavigationView) findViewById(R.id.navView);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        FirstFragment firstFragment = new FirstFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainContent, firstFragment).commit();
        prepareListItems();
        menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList);
        navView.setAdapter(menuAdapter);
        navView.setHeaderView(getHeader());
        navView.setOnNavigationItemSelectedListner(this);
        navView.setSelectionBackGround(getResources().getColor(R.color.colorAccent));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawerLayout.getChildAt(0).setTranslationX(slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void prepareListItems() {
        menuList = new ArrayList<>();
        menuList.add("One");
        menuList.add("Two");
        menuList.add("Three");
        menuList.add("Four");
    }

    private View getHeader() {
        View view = getLayoutInflater().inflate(R.layout.header, null);
        TextView button = (TextView) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("header", "buttonClicked");
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(View view, int position) {
        switch (position) {
            case 0:
                FirstFragment firstFragment=new FirstFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, firstFragment).commit();
                break;
            case 1:
                SecondFragment secondFragment=new SecondFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, secondFragment).commit();
                break;
            case 2:
                ThirdFragment thirdFragment=new ThirdFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, thirdFragment).commit();
                break;
            case 3:
                FourthFragment fourthFragment=new FourthFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, fourthFragment).commit();
                break;
        }
        drawerLayout.closeDrawers();
    }
}

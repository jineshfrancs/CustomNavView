# CustomNavView
Custom implementation of NavigationView for using custom layout for navigation items and header. 

![Output sample](https://github.com/jineshfrancs/CustomNavView/blob/master/screens/nav_screen.gif)

Add CustomNavigationView in DrawerLayout
```
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="test.jinesh.customnavigation.NavigationActivity">
    <FrameLayout
        android:layout_width="match_parent"
        android:orientation="vertical"
        android:id="@+id/mainContent"
        android:layout_height="match_parent">

    </FrameLayout>

    <test.jinesh.customnavigation.CustomNavigationView
        android:id="@+id/navView"
        android:layout_width="280dp"
        android:layout_height="match_parent"
        android:layout_gravity="start" />
</android.support.v4.widget.DrawerLayout>
```
Set header view and items adapter
```
 menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList);
 navView.setAdapter(menuAdapter);
 //header view and bottom margin for the header view
 navView.setHeaderView(getHeader(),20);
```
Set NavigationItemSelectedListner to notified about click events on nav items
```
navView.setOnNavigationItemSelectedListner(this);

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
 ```

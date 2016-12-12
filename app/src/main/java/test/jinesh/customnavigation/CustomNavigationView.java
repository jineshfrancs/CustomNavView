package test.jinesh.customnavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by Jinesh on 12-12-2016.
 */

public class CustomNavigationView extends ScrimInsetsFrameLayout {
    private ScrollView listView;
    private ListAdapter adapter;
    private Drawable background;
    private LinearLayout linearLayout;
    private int backGroundColor=0xffe2e2e2;
    private  int selectedIndex=-1;
    private static NavigationItemSelectedListner navigationItemSelectedListner;
    public CustomNavigationView(Context context) {
        super(context);
        init(context);
    }


    public CustomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        FrameLayout.LayoutParams listParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listView = new ScrollView(context);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(listParams);
        listParams.gravity = Gravity.START;
        listView.setLayoutParams(listParams);
        listView.setVerticalScrollBarEnabled(false);
        listView.setHorizontalScrollBarEnabled(false);
        if (background != null)
            setBackground(background);
        else
            setBackgroundColor(0xffffffff);
        listView.addView(linearLayout);
        addView(listView);
        setFitsSystemWindows(true);

    }

    View[] childView;

    public void setAdapter(final ListAdapter adapter) {
        this.adapter = adapter;
        final int defColor = 0xffffffff;
        childView=new View[adapter.getCount()];

        for (int index = 0; index < adapter.getCount(); index++) {
            childView[index] = adapter.getView(index, null, this);
            childView[index].setTag(index);
            final int finalIndex = index;
            childView[index].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int innerIndex = 0; innerIndex < adapter.getCount(); innerIndex++) {
                        if ((int) childView[innerIndex].getTag() != finalIndex)
                            childView[innerIndex].setBackgroundColor(defColor);
                        else
                            childView[innerIndex].setBackgroundColor(backGroundColor);
                    }
                    if(navigationItemSelectedListner!=null)
                        navigationItemSelectedListner.onItemSelected(childView[finalIndex],finalIndex);
                     selectedIndex=finalIndex;
                }
            });
            linearLayout.addView(childView[index]);
        }

    }
    public void setHeaderView(View view){
        linearLayout.addView(view,0);
    }
    public void setBackGround(Drawable backGround) {
        background = backGround;
        setBackground(backGround);
    }
    public void setSelectionBackGround(int color) {
       backGroundColor=color;
    }
    public ListAdapter getAdapter() {
        return adapter;
    }

   public interface NavigationItemSelectedListner{
       void onItemSelected(View view,int position);
   }

    public  void setOnNavigationItemSelectedListner(NavigationItemSelectedListner navigationItemSelectedListner) {
        CustomNavigationView.navigationItemSelectedListner = navigationItemSelectedListner;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}

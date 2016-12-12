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
    private ScrollView scrollView;
    private ListAdapter adapter;
    private Drawable background;
    private LinearLayout linearLayout;
    private int backGroundColor = 0xffe2e2e2;
    View[] childView;
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
        scrollView = new ScrollView(context);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(listParams);
        listParams.gravity = Gravity.START;
        scrollView.setLayoutParams(listParams);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        if (background != null)
            setBackground(background);
        else
            setBackgroundColor(0xffffffff);
        scrollView.addView(linearLayout);
        addView(scrollView);
        setFitsSystemWindows(true);

    }

    /**
     * Sets a List adapter to display navigation items
     *
     * @param adapter adapter to set the nav action items
     */
    public void setAdapter(final ListAdapter adapter) {
        this.adapter = adapter;
        final int defColor = 0xffffffff;
        childView = new View[adapter.getCount()];

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
                    if (navigationItemSelectedListner != null)
                        navigationItemSelectedListner.onItemSelected(childView[finalIndex], finalIndex);

                }
            });
            linearLayout.addView(childView[index]);
        }

    }

    /**
     * Sets the header view for the navigation drawer along with the bottom margin
     *
     * @param view         header view for the navigation drawer
     * @param marginBottom bottom margin of header view
     */
    public void setHeaderView(View view, int marginBottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, marginBottom);
        view.setLayoutParams(layoutParams);
        linearLayout.addView(view, 0);
    }

    /**
     * Sets the specified drawable to the background of navigation drawer
     *
     * @param backGround drawable for the background
     */
    public void setBackGround(Drawable backGround) {
        background = backGround;
        setBackground(backGround);
    }

    /**
     * Sets the specified background color to the navigation drawer
     *
     * @param backGround background color for the navigation drawer
     */
    public void setBackGround(int backGround) {
        setBackgroundColor(backGround);
    }

    /**
     * Sets the background color for the item selection indicator
     *
     * @param color background color to set
     */
    public void setSelectionBackGround(int color) {
        backGroundColor = color;
    }

    /**
     * Returns the nav item adapter
     *
     * @return ListAdapter
     */
    public ListAdapter getAdapter() {
        return adapter;
    }

    /**
     * interface to notify click actions on navigation items
     */
    public interface NavigationItemSelectedListner {
        void onItemSelected(View view, int position);
    }

    /**
     * Sets a item click listner,which notifies the click actions on the action items
     *
     * @param navigationItemSelectedListner listner notifies item click with particular position
     */
    public void setOnNavigationItemSelectedListner(NavigationItemSelectedListner navigationItemSelectedListner) {
        CustomNavigationView.navigationItemSelectedListner = navigationItemSelectedListner;
    }

}

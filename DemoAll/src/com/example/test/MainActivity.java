package com.example.test;

import net.simonvt.menudrawer.MenuDrawer;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {
	private MenuDrawer mDrawer;
	private View view;
	Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        getSupportActionBar();
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setMenuView(R.layout.activity_main);
        mDrawer.setDrawerIndicatorEnabled(true);
        // The drawable that replaces the up indicator in the action bar
        mDrawer.setSlideDrawable(R.drawable.ic_drawer);
        // Whether the previous drawable should be shown
        mDrawer.setDrawerIndicatorEnabled(true);
        
        view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        mDrawer.setContentView(view);
        btn = (Button) view.findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println("asdf");
				Intent intent = new Intent(getApplicationContext(), RefreshAndBackActivity.class);
				startActivity(intent);
			}
		});
       
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.toggleMenu();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    
}

package com.example.scheduleme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	ActionBar.Tab tabProfile, tabGroup, tabPeople;
	Fragment fragmentTabProfile = new ProfileFragment();
	Fragment fragmentTabGroup = new GroupFragment();
	Fragment fragmentTabPeople = new PeopleFragment();
	
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        session = new SessionManager(getApplicationContext());      
        
        session.checkLogin();
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        tabProfile = actionBar.newTab().setText("Profile");
        tabGroup = actionBar.newTab().setText("Groups");
        tabPeople = actionBar.newTab().setText("People");
        
        tabProfile.setTabListener(new MyTabListener(fragmentTabProfile));
        tabGroup.setTabListener(new MyTabListener(fragmentTabGroup));
        tabPeople.setTabListener(new MyTabListener(fragmentTabPeople));
        
        actionBar.addTab(tabProfile);
        actionBar.addTab(tabGroup);
        actionBar.addTab(tabPeople);
        
        
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public class MyTabListener implements ActionBar.TabListener {
		Fragment fragment;
		
		public MyTabListener(Fragment fragment) {
			this.fragment = fragment;
		}
		
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
		}
		
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}
		
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// nothing done here
		}
	}
	
}


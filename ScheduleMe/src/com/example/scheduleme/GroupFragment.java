package com.example.scheduleme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class GroupFragment extends ListFragment {
	private GroupAdapter groupAdapter;


	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ListView placesListView = getListView();

		final View footerView = getActivity().getLayoutInflater().inflate(
				R.layout.footer_view, null);
		placesListView.addFooterView(footerView);

		footerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				groupAdapter.add(new Group("groupname", null, "hello", null,
						"desc"));
			}

		});

		groupAdapter = new GroupAdapter(getActivity());
		setListAdapter(groupAdapter);
	}
	
	
}
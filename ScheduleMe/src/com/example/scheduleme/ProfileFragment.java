package com.example.scheduleme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	ImageView image;
	static final int REQUEST_CAMERA = 1;
	static final int SELECT_FILE = 2;
	View view;
	ImageView editFirstName;
	ImageView editEmail;
	
	WebView webView;
	
	
	SessionManager session;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		session = new SessionManager(getActivity().getApplicationContext());
		
		
		
		view = inflater.inflate(R.layout.personal_profile, container,
				false);
		
		
		
		image = (ImageView) view.findViewById(R.id.pic);
		final ProfileFragment frag = this;
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				frag.selectPicture();
			}
				
		});
		
		editFirstName = (ImageView) view.findViewById(R.id.edit_name);
		editFirstName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				frag.changeFirstName();
			}
		});
		
		editEmail = (ImageView) view.findViewById(R.id.edit_email);
		editEmail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				frag.changeEmail();
			}
		});
		
		
		String name = session.getUserDetails().get(SessionManager.KEY_NAME);
		
		webView = (WebView) view.findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://scheduleme.herokuapp.com/get_calendar?did=" + name);
		
		return view;
	}
	
	public void onResume() {
		super.onResume();
		HashMap<String, String> map = session.getUserDetails();
		
		String name = map.get(SessionManager.KEY_NAME);
		
		
		
		if (name != null) {
			Log.v("hey", name);
			TextView nameView = (TextView)view.findViewById(R.id.personal_profile_name);
			
			nameView.setText(name);
		}
	}

	protected void selectPicture() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File f = new File(android.os.Environment
							.getExternalStorageDirectory(), "temp.jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
					startActivityForResult(intent, REQUEST_CAMERA);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							SELECT_FILE);
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_CAMERA) {
				File f = new File(Environment.getExternalStorageDirectory()
						.toString());
				for (File temp : f.listFiles()) {
					if (temp.getName().equals("temp.jpg")) {
						f = temp;
						break;
					}
				}
				try {
					Bitmap bm;
					BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

					bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
							btmapOptions);

					// bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
					image.setImageBitmap(bm);

					String path = android.os.Environment
							.getExternalStorageDirectory()
							+ File.separator
							+ "Phoenix" + File.separator + "default";
					f.delete();
					OutputStream fOut = null;
					File file = new File(path, String.valueOf(System
							.currentTimeMillis()) + ".jpg");
					try {
						fOut = new FileOutputStream(file);
						bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
						fOut.flush();
						fOut.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (requestCode == SELECT_FILE) {
				Uri selectedImageUri = data.getData();

				
				
				String tempPath = getPath(data, getActivity());
				Log.v("hey", tempPath);
				Bitmap bm;
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				btmapOptions.inSampleSize = 2;
				bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
				
				image.setImageBitmap(bm);
			}
		}
	}

	public String getPath(Intent data, Context context) {
	    Uri selectedImage = data.getData();
	    String[] filePathColumn = { MediaStore.Images.Media.DATA };
	    Cursor cursor = context.getContentResolver().query(selectedImage,
	            filePathColumn, null, null, null);
	    cursor.moveToFirst();
	    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	    String picturePath = cursor.getString(columnIndex);
	    cursor.close();
	    return picturePath;
	}
	
	private void changeFirstName() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.change_dialog);
		dialog.setTitle("Change First Name");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText("What would you like to change your name to?");
		
		
		

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edit = (EditText)dialog.findViewById(R.id.edit);
				
				TextView nameView = (TextView) view.findViewById(R.id.personal_profile_name);
				nameView.setText(edit.getText().toString());
				dialog.dismiss();
			}
		});

		dialog.show();
	}
	
	private void changeLastName(String name) {
		
	}
	
	private void changeEmail() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.change_dialog);
		dialog.setTitle("Change Email");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText("What would you like to change your email to?");
		
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edit = (EditText)dialog.findViewById(R.id.edit);
				
				TextView nameView = (TextView) view.findViewById(R.id.personal_profile_email);
				nameView.setText(edit.getText().toString());
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
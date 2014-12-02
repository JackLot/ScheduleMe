package com.example.scheduleme;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupAdapter extends BaseAdapter {

	private ArrayList<Group> list = new ArrayList<Group>();
	private static LayoutInflater inflater = null;
	private Context mContext;
	
	public GroupAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View newView = convertView;
		ViewHolder holder;

		Group curr = list.get(position);

		if (null == convertView) {
			holder = new ViewHolder();
			newView = inflater.inflate(R.layout.group_list_item, parent, false);
			holder.groupPic = (ImageView) newView.findViewById(R.id.group_pic);
			holder.name = (TextView) newView.findViewById(R.id.group_name);
			holder.description = (TextView) newView.findViewById(R.id.group_description);
			newView.setTag(holder);
			
		} else {
			holder = (ViewHolder) newView.getTag();
		}

		holder.groupPic.setImageBitmap(curr.getPic());
		holder.name.setText(curr.getName());
		holder.description.setText(curr.getDescription());

		return newView;
	}
	
	static class ViewHolder {
		
		ImageView groupPic;
		TextView name;
		TextView description;
		
	}

	public void add(Group groupItem) {
		list.add(groupItem);
		notifyDataSetChanged();
	}
	

}

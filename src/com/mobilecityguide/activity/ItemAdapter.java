package com.mobilecityguide.activity;
import java.util.ArrayList;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.AddItem.UserRecord;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;




public class ItemAdapter extends Activity {

	private ArrayList<UserRecord> users;

	public ItemAdapter(Context context, int textViewResourceId, ArrayList<UserRecord> users) {
		super();
		this.users = users;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.listitem, null);
		}

		UserRecord user = users.get(position);
		if (user != null) {
			TextView username = (TextView) v.findViewById(R.id.item_name_field);
			TextView email = (TextView) v.findViewById(R.id.description_field);

			if (username != null) {
				username.setText(user.username);
			}

			if(email != null) {
				email.setText("Email: " + user.email );
			}
		}
		return v;
	}
}
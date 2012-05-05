package com.mobilecityguide.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.AddItem.Item_Adapter;
import com.mobilecityguide.activity.AddItem.UserRecord;

public class MyPlace extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_items);
		
		
		ArrayList<UserRecord> users = new ArrayList<UserRecord>();
        UserRecord user1 = new UserRecord("Justin", "someemail@gmail.com");
        users.add(user1);
        UserRecord user2 = new UserRecord("Jeremy", "someemail@yahoo.com");
        users.add(user2);
        UserRecord user3 = new UserRecord("Frank", "someemail@hotmail.com");
        users.add(user3);
        
        ListView listView = (ListView) this.findViewById(R.id.list);
        listView.setAdapter(new Item_Adapter(this, android.R.layout.simple_list_item_1, users));
       // setListeners();
	}

	private void setListeners() {
		View addButton = findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
		View dellButton = findViewById(R.id.dell_button);
		dellButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.add_button:
			intent = new Intent(this, AddItem.class);
			startActivity(intent);
			break;
		case R.id.dell_button:
			intent = new Intent(this, DellItem.class);
			startActivity(intent);
			break;
		}
	}
	
	public class Item_Adapter extends ArrayAdapter<UserRecord> {
		private ArrayList<UserRecord> users;

		public Item_Adapter(Context context, int textViewResourceId, ArrayList<UserRecord> users) {
			super(context, textViewResourceId, users);
			this.users = users;
		}

		@Override
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
	
	public class UserRecord {
		public String username;
		public String email;
		
		public UserRecord(String username, String email) {
			this.username = username;
			this.email = email;
		}
	}
}

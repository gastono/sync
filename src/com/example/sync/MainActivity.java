package com.example.sync;


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private CustomLayout custom;
	 public static ArrayList<Contact> contact_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		custom = (CustomLayout)findViewById(R.id.custom);
		
		final ItemList contactsListView = new ItemList((ListView)findViewById(R.id.contactsList));
		initContactListView(this, contactsListView.getList());
		
		EditText inputSearch = (EditText)findViewById(R.id.editText1);
		
		inputSearch.addTextChangedListener(new TextWatcher() {
        	 
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // TODO: buscar en dummies e ir agregando por coincidencia
              
            	CustomAdapter adapter = (CustomAdapter)contactsListView.getList().getAdapter();
            	
            	adapter.getFilter().performFiltering(cs,(CustomAdapter)contactsListView.getList().getAdapter());
            }
         
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
         
            }

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	
	  public static void initContactListView(Context context,ListView listView)
	    {
	    	final ListView lv1 = listView;
	    	if (contact_list==null) {
				contact_list = new ArrayList<Contact>();
			}
	    	lv1.setAdapter(new CustomAdapter(context,contact_list));
	    	
	    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.contacts:
			custom.toggleMenu();
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

}

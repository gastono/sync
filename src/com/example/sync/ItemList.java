package com.example.sync;

import android.widget.ListView;

public class ItemList {
	
	private ListView list;
	
	public ItemList(ListView list)
	{
		setList(list);
	}

	public ListView getList() {
		return list;
	}

	public void setList(ListView list) {
		this.list = list;
	}

}

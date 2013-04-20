package com.example.sync;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class CustomLayout extends LinearLayout {

	private LinearLayout contactSearcher;
	public CustomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		try
		{
			LayoutInflater.from(getContext()).inflate(R.layout.contact_searcher, this, true);
		} catch(Exception e) {}	
		
		contactSearcher = (LinearLayout)findViewById(R.id.contactSearcher);
	}
	public void showMenu()
	{
		contactSearcher.setVisibility(View.VISIBLE);	
		contactSearcher.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_in_from_left));
	}

	public void hideMenu()
	{
		contactSearcher.setVisibility(View.GONE);
		contactSearcher.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_out_to_left));
	}

	public void toggleMenu()
	{
		if (contactSearcher.getVisibility() == View.GONE)
		{
			showMenu();
		} 
		else 
		{
			hideMenu();
		}
	}

}

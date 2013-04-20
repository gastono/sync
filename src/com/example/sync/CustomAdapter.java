package com.example.sync;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {

	public  ArrayList<Contact> itemDetailsrrayList;
	private LayoutInflater l_Inflater;
	private ConctactFilter filter;
	private Integer[] imgid = {
			R.drawable.bb1,
			R.drawable.bb2,
			R.drawable.bb4,
			R.drawable.bb5,
			R.drawable.bb6,
			R.drawable.d1
			};
	
	public ConctactFilter getFilter()
    {
        if(filter == null)
            filter = new ConctactFilter();
        return filter;
    }
	public CustomAdapter(Context context,ArrayList<Contact> results)
	{
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}
	
	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;		
		ImageView itemImage;
	}
	
	@Override
	public int getCount() {
		 if (itemDetailsrrayList==null) {
			 return 0;
			}else {
				return itemDetailsrrayList.size();
			}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemDetailsrrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void clear()
	{
		this.itemDetailsrrayList.clear();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.contact_detail, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);			
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());		
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}
	
	public class ConctactFilter extends Filter {
		
 		
		
		protected FilterResults performFiltering(CharSequence arg0,CustomAdapter adapter) {
			adapter.clear();
			notifyDataSetChanged();
			arg0 =  arg0.toString().toLowerCase();
			
			FilterResults result = new FilterResults();
			
			if (arg0!= null && arg0.toString().length()>0) {
				ArrayList<Contact> items = new ArrayList<Contact>();
				
				ArrayList<Contact> filtered = new ArrayList<Contact>();
				
				synchronized(this)
				{
					items.addAll(GetSearchResults());//TODO:Llamar a servicio para consulta de contactos.
				}
				for (int i = 0; i < arg0.toString().length(); i++) {
					Contact itm = items.get(i);
					
					if (itm.getName().toLowerCase().contains(arg0.toString())) {
						filtered.add(itm);
					}
				}
				
				adapter.itemDetailsrrayList.addAll(filtered);
				notifyDataSetChanged();
				result.count = filtered.size();
				result.values = filtered;
				
				
			}else {
				synchronized(this)
				{
				result.count = itemDetailsrrayList.size();
				result.values = itemDetailsrrayList;
				}
			}	
			return null;
		}
		
		 private  ArrayList<Contact> GetSearchResults(){
		    	ArrayList<Contact> results = new ArrayList<Contact>();
		    	
		    	Contact item_details = new Contact();
		    	item_details.setName("Sergio Flores");
		    	item_details.setItemDescription("1558482847");		    	
		    	item_details.setImageNumber(1);
		    	results.add(item_details);
		    	
		    	item_details = new Contact();
		    	item_details.setName("May Marquez");
		    	item_details.setItemDescription("1553183233");		    	
		    	item_details.setImageNumber(2);
		    	results.add(item_details);
		    	
		    	item_details = new Contact();
		    	item_details.setName("Franco Santollin");
		    	item_details.setItemDescription("1551233265");		    	
		    	item_details.setImageNumber(3);
		    	results.add(item_details);
		    	
		    	item_details = new Contact();
		    	item_details.setName("Nahuel Taibo");
		    	item_details.setItemDescription("1569534136");		    	
		    	item_details.setImageNumber(4);
		    	results.add(item_details);
		    	
		    	item_details = new Contact();
		    	item_details.setName("Burger");
		    	item_details.setItemDescription("Fish Burger");		    	
		    	item_details.setImageNumber(5);
		    	results.add(item_details);
		    	
		    	item_details = new Contact();
		    	item_details.setName("Mango");
		    	item_details.setItemDescription("Mango Juice");		    	
		    	item_details.setImageNumber(6);
		    	results.add(item_details);
		    	
		    	
		    	return results;
		    }
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence arg0, FilterResults arg1) {
			// TODO Auto-generated method stub
			
			itemDetailsrrayList = (ArrayList<Contact>) arg1.values;
			notifyDataSetChanged();
		
		}

		@Override
		protected FilterResults performFiltering(CharSequence arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}

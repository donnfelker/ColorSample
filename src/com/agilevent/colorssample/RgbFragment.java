package com.agilevent.colorssample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RgbFragment extends ColorListFragment {
	
	protected List<SimpleColor> colors;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		colors = ColorFactory.getColors(); 
		
		setListAdapter(new ColorsAdapter());
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		// Notify listener that a color has been changed. 
		if(listener != null)
			listener.onColorChanged(colors.get(position)); 
		
	}
	

	class ColorsAdapter extends ArrayAdapter<SimpleColor> {

		public ColorsAdapter() {
			super(getActivity(), android.R.layout.simple_list_item_activated_1, colors);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null) {
				convertView= LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_activated_1, null);
			}
			
			SimpleColor color = colors.get(position);
			convertView.setTag(color); 
			TextView colorName = (TextView)convertView.findViewById(android.R.id.text1);
			colorName.setText(color.getName());
			
			return convertView; 
		}
		
	}
	
	public void reset() {
		// Uses the colors object, which only has our defaults in it. 
		colors = ColorFactory.getColors(); 
		setListAdapter(new ColorsAdapter());
		
		// Notify listener that it should reset it self to transparent. 
		listener.onColorChanged(new SimpleColor("transparent", Color.parseColor("#00000000"))); 
	}
}

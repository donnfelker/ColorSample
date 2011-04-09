package com.agilevent.colorssample;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

public class ColorFactory  {
	public static List<SimpleColor> getColors( ){
		List<SimpleColor> colors = new ArrayList<SimpleColor>();
		
		colors.add(new SimpleColor("Red", Color.parseColor("#ff0000")));
		colors.add(new SimpleColor("Green", Color.parseColor("#00e130")));
		colors.add(new SimpleColor("Blue", Color.parseColor("#007eff")));

		
		return colors; 
	}
	

}

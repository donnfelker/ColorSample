package com.agilevent.colorssample;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorFactory  {
	public static List<SimpleColor> getColors( ){
		List<SimpleColor> colors = new ArrayList<SimpleColor>();
		
		colors.add(new SimpleColor("Red", Color.parseColor("#ff0000")));
		colors.add(new SimpleColor("Green", Color.parseColor("#00e130")));
		colors.add(new SimpleColor("Blue", Color.parseColor("#007eff")));

		
		return colors; 
	}


    public static List<SimpleColor> getPastelColors() {
        List<SimpleColor> colors = new ArrayList<SimpleColor>();

		colors.add(new SimpleColor("Pink", Color.parseColor("#ff8b8d")));
		colors.add(new SimpleColor("Aqua", Color.parseColor("#8bf3ff")));
		colors.add(new SimpleColor("Orange", Color.parseColor("#ffca8b")));


		return colors;
    }

    public static List<SimpleColor> getNeonColors() {
           List<SimpleColor> colors = new ArrayList<SimpleColor>();

           colors.add(new SimpleColor("Hot Pink", Color.parseColor("#ff00d2")));
           colors.add(new SimpleColor("Lime", Color.parseColor("#48ff00")));
           colors.add(new SimpleColor("Future Blue", Color.parseColor("#00c6ff")));


           return colors;
       }



}

package com.agilevent.colorssample;

import android.app.ListFragment;

public abstract class ColorListFragment extends ListFragment {

    protected ColorChangedListener listener;

    public void setColorChangedListener(ColorChangedListener listener) {
		this.listener = listener;
	}

    public abstract void reset();


}

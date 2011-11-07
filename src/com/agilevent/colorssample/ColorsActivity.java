package com.agilevent.colorssample;


import android.app.*;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ColorsActivity extends Activity {
   
	//protected ColorsFragment cf;
	protected ColorDetailFragment cdf;
    protected ColorListFragment currentListFragment;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        // 1. Get ColorsFragment
        // 2. Get ColorDetailFragment
        // 3. Set the listener of the ColorFragment to that of the ColorFragment
        //cf = (ColorsFragment)getFragmentManager().findFragmentById(R.id.colors);
        cdf = (ColorDetailFragment)getFragmentManager().findFragmentById(R.id.details); 
        
        // Let the ColorsFragment know who to notify when the color changes. 
        //cf.setColorChangedListener(cdf);


        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        bar.addTab(bar.newTab()
                .setText("RGB")
                .setTabListener(new TabListener<ColorsFragment>(
                        this, "rgb", ColorsFragment.class)));

        bar.addTab(bar.newTab()
                .setText("Pastel")
                .setTabListener(new TabListener<PastelsFragment>(
                        this, "pastel", PastelsFragment.class)));

       bar.addTab(bar.newTab()
                .setText("Neon")
                .setTabListener(new TabListener<NeonFragment>(
                        this, "neon", NeonFragment.class)));



        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }

    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.color_menu, menu);
		
		EditText add=(EditText)menu.findItem(R.id.add)
			.getActionView().findViewById(R.id.color);
		
		add.setOnEditorActionListener(onAdd);

		return(super.onCreateOptionsMenu(menu));
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		
			case R.id.reset:
				currentListFragment.reset();
				return(true);

			case R.id.about:				
			case android.R.id.home:
				Toast.makeText(this,"Color Sample App. Uses Fragments and the Action Bar",Toast.LENGTH_LONG).show();
				return(true);
		}
		
		return(super.onOptionsItemSelected(item));
	}


	@SuppressWarnings("unchecked")
	private void addColor(String colorWithCode) {

        if(colorWithCode.indexOf( ':' ) > 0) {

            ArrayAdapter<SimpleColor> adapter = (ArrayAdapter<SimpleColor>) currentListFragment.getListAdapter();

            String colorName = colorWithCode.substring(0, colorWithCode.indexOf(':'));
            String colorHex = colorWithCode.substring(colorWithCode.indexOf(':') + 1, colorWithCode.length());

            adapter.add(new SimpleColor(colorName, Color.parseColor(colorHex)));

        } else {

            new AlertDialog.Builder(this)
                    .setIcon( android.R.drawable.ic_dialog_alert )
                    .setMessage( "Please add a color in this format: ColorName:#HEXVAL" )
                    .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        }


	}
    
    
    private TextView.OnEditorActionListener onAdd=
		new TextView.OnEditorActionListener() {
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (event==null || event.getAction()==KeyEvent.ACTION_UP) {
				
				addColor(v.getText().toString());
				
				// Clear it out now
				v.setText("");
				
				InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
			
			return(true);
		}
	};

    public class TabListener<T extends ColorListFragment> implements ActionBar.TabListener {
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        protected ColorListFragment mFragment;


        public TabListener(Activity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }

        public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            mFragment = (ColorListFragment) mActivity.getFragmentManager().findFragmentByTag(mTag);
            if (mFragment != null && !mFragment.isDetached()) {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                ft.detach(mFragment);
                ft.commit();
            }
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment == null) {
                mFragment = (ColorListFragment) Fragment.instantiate( mActivity, mClass.getName(), mArgs );
                mFragment.setColorChangedListener( cdf );
                ft.add(R.id.color_content, mFragment, mTag);

                currentListFragment = mFragment;

            } else {
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
        }
    }
	
    
    
   
    
   
}
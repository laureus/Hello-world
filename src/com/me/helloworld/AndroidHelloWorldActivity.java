package com.me.helloworld;


import android.app.Activity;
import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import winterwell.jtwitter.*;


public class AndroidHelloWorldActivity extends Activity implements OnClickListener,
	TextWatcher {
    
	private static final String TAG = "StatusActivity";
	EditText editText;
	Button updateButton;
	Twitter twitter;
	TextView textCount;
	//SharedPreferences prefs;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    //Find views
    editText = (EditText)findViewById(R.id.editText);
    updateButton = (Button)findViewById(R.id.buttonUpdate);
    
    updateButton.setOnClickListener(this);
    
    textCount = (TextView) findViewById(R.id.textCount);
    textCount.setText(Integer.toString(140));
    textCount.setTextColor(Color.GREEN);
    editText.addTextChangedListener(this);
    
    //twitter = new Twitter("student","password");
    //twitter.setAPIRootUrl("http://yamba.marakana.com/api");
    
    // Setup preferences
    //prefs = PreferenceManager.getDefaultSharedPreferences(this);
    //prefs.registerOnSharedPreferenceChangeListener(this);
    }
    
    public void onClick(View v){
    	
    	// Update twitter status
    	/*try {
    		getTwitter().setStatus(editText.getText().toString());
    	} catch (TwitterException e){
    		Log.d(TAG, "Twitter setStatus failed: "+ e);
    	}*/
    	
    	//twitter.setStatus(editText.getText().toString());
    	//Log.d(TAG, "onClicked");
    }
    
   /* private Twitter getTwitter(){
    	if(twitter == null){
    		String username, password, apiRoot;
    		username = prefs.getString("username", "");
    		password = prefs.getString("password", "");
    		apiRoot = prefs.getString("apiRoot", "http://yamba.marakana.com/api");
    		
    		// Connect to twitter.com
    		twitter = new Twitter(username,password);
    		twitter.setAPIRootUrl(apiRoot);    		
    	}
    	return twitter;
    } */
    
    // Asynchronously posts to twitter
    class PostToTwitter extends AsyncTask<String, Integer, String>{
    	
    	@Override
    	protected String doInBackground(String... statuses) { //
    	try {
    	Twitter.Status status = twitter.updateStatus(statuses[0]);
    	return status.text;
    	} catch (TwitterException e) {
    	Log.e(TAG, e.toString());
    	e.printStackTrace();
    	return "Failed to post";
    	}
    	}
    	// Called when there's a status to be updated
    	@Override
    	protected void onProgressUpdate(Integer... values) { //
    	super.onProgressUpdate(values);
    	// Not used in this case
    	}
    	// Called once the background activity has completed
    	@Override
    	protected void onPostExecute(String result) { //
    	Toast.makeText(AndroidHelloWorldActivity.this, result, Toast.LENGTH_LONG).show();
    	}
    
    }
    
    public void afterTextChanged(Editable statusText){
    	int count = 140 - statusText.length();
    	textCount.setText(Integer.toString(count));
    	textCount.setTextColor(Color.GREEN);
    	if(count < 30)
    		textCount.setTextColor(Color.YELLOW);
    	if(count <0)
    		textCount.setTextColor(Color.RED);
    }
    
    public void beforeTextChanged(CharSequence s, int start, int count, int after){
    	
    }
    
    public void onTextChanged(CharSequence s, int start, int before, int count){
    	
    }
    
 // Called first time user clicks on the menu button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater(); //
    inflater.inflate(R.menu.menu, menu); //
    return true; //
    }
    
 // Called when an options item is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) { //
    case R.id.itemPrefs:
    startActivity(new Intent(this, PrefsActivity.class)); //
    break;
    }
    return true; //
    }



}
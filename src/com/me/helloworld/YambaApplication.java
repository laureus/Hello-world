package com.me.helloworld;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

public class YambaApplication extends Application implements
		OnSharedPreferenceChangeListener {

	private static final String TAG = YambaApplication.class.getSimpleName();
	public Twitter twitter;
	private SharedPreferences prefs;
	
	@Override
	public void onCreate(){
		super.onCreate();
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
		this.prefs.registerOnSharedPreferenceChangeListener(this);
		Log.i(TAG, "onCreate");
	}
	
	@Override
	public void onTerminate(){
		super.onTerminate();
		Log.i(TAG, "onTerminated");
	}
	
	public synchronized Twitter getTwitter(){
		if(this.twitter == null){
			String username = this.prefs.getString("username", "");
			String password = this.prefs.getString("password", "");
			String apiRoot = prefs.getString("apiRoot", "http://yamba.marakana.com/api");
			
			if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
					&& !TextUtils.isEmpty(apiRoot)){
				this.twitter = new Twitter(username, password);
				this.twitter.setAPIRootUrl(apiRoot);
			}
		}
		return this.twitter;
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPrefences, String key) {

		this.twitter = null;

	}

}

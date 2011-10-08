/**
 * 
 */
package com.me.helloworld;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author Laureus
 *
 */
public class PrefsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { //
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.prefs); //
	}
}

package com.odistagon.myn;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AyMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ay_main);

		TelephonyManager	telman = null;
		String				sNum1 = null;
		try {
			telman = (TelephonyManager)
				getSystemService(TELEPHONY_SERVICE);

			sNum1 = telman.getLine1Number();
		} catch(Exception exc) {
			sNum1 = "Device does not have telephony functionality."
					+ "\r\n" + exc.getMessage();
		}

		FontFitTextView	tv0 = (FontFitTextView)findViewById(R.id.tv_main_test1);
		String			sText = (sNum1 != null ?
				PhoneNumberUtils.formatNumber(sNum1) : "number could not retrieved");
		// max text size depends on layout height of the TextView. On Android 1.6, 
		// the height seems not to be changed after the text displayed.
		tv0.setMaxTextSize(36);
		tv0.setText(sText);
//		tv0.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
				// Call requires API level 11
//				ClipboardManager	cbm0 = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
////				cbm0.setPrimaryClip(ClipData.newPlainText(
////						"Phone Number", ((TextView)v).getText().toString()));
////				cbm0.setText(((TextView)v).getText().toString());
//			}
//		});
		Button			btn0 = (Button)findViewById(R.id.btn_main_sett);
		btn0.setOnClickListener(new OnPhoneStateBtnListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ay_main, menu);
		return true;
	}

	private class OnPhoneStateBtnListener implements OnClickListener {
		@Override
		public void onClick(View v) {
//			Intent i0 = new Intent(android.provider.Settings.ACTION_SETTINGS);
			Intent i0 = new Intent();
			i0.setComponent(new ComponentName(
					"com.android.settings", "com.android.settings.deviceinfo.Status"));
			if(i0 != null) {
				i0.setAction(Intent.ACTION_MAIN);
				startActivity(i0);
			}
		}
	}
}

package com.gallantrealm.android;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * This activity can be subclassed for menus within a game. It provides controller support within the menus.
 */
public class GallantActivity extends Activity {

	public int songId = 0;

	View currentFocusView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fullscreen();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);
	    if (hasFocus) {
	    	fullscreen();
	    }
	}
	
	private void fullscreen() {
	    // Enables sticky immersive mode
	    View decorView = getWindow().getDecorView();
	    decorView.setSystemUiVisibility(
	            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
	            // Set the content to appear under the system bars so that the
	            // content doesn't resize when the system bars hide and show.
	            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	            // Hide the nav bar and status bar
	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_FULLSCREEN);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	// ------------------------
	// Mapping controllers to keyboard
	// ------------------------

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		System.out.println("Keycode: " + keyCode);
		if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_BUTTON_A || keyCode == KeyEvent.KEYCODE_BUTTON_SELECT) {
			View v = getWindow().getCurrentFocus();
			if (v instanceof Button || v instanceof CheckBox || v instanceof RadioButton) {
				v.performClick();
			} else {
				onOkay();
			}
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE || keyCode == KeyEvent.KEYCODE_BUTTON_B) {
			onCancel();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BUTTON_L1 || keyCode == KeyEvent.KEYCODE_BUTTON_L2) {
			onPrevious();
		} else if (keyCode == KeyEvent.KEYCODE_BUTTON_R1 || keyCode == KeyEvent.KEYCODE_BUTTON_R2) {
			onNext();
		}
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		System.out.println("Keycode: " + keyCode);
		return false;
	}

	private void sendKey(final int keyCode) {
		new Thread() {
			@Override
			public void run() {
				try {
					Instrumentation inst = new Instrumentation();
					inst.sendKeyDownUpSync(keyCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Override to provide behavior to show next item to select from in a list
	 */
	public void onNext() {

	}

	/**
	 * Override to provide behavior to show previous item to select from in a list
	 */
	public void onPrevious() {

	}

	/**
	 * Override to give a behavior on okay pressed. Default is to finish.
	 */
	public void onOkay() {
		finish();
	}

	/**
	 * Override to give a behavior on cancel pressed. Default is to finish.
	 */
	public void onCancel() {
		finish();
	}

}

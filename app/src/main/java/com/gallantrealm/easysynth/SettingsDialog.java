package com.gallantrealm.easysynth;

import com.gallantrealm.android.Translator;
import com.gallantrealm.easysynth.theme.AuraTheme;
import com.gallantrealm.easysynth.theme.CustomTheme;
import com.gallantrealm.easysynth.theme.IceTheme;
import com.gallantrealm.easysynth.theme.MetalTheme;
import com.gallantrealm.easysynth.theme.OnyxTheme;
import com.gallantrealm.easysynth.theme.SpaceTheme;
import com.gallantrealm.easysynth.theme.SunsetTheme;
import com.gallantrealm.easysynth.theme.TeaTheme;
import com.gallantrealm.easysynth.theme.TropicalTheme;
import com.gallantrealm.easysynth.theme.WoodTheme;
import com.gallantrealm.easysynth.ClientModel;
import com.gallantrealm.android.SelectItemDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import de.viktorreiser.toolbox.widget.NumberPicker;

public class SettingsDialog extends Dialog {
	ClientModel clientModel = ClientModel.getClientModel();

	Spinner keysSpinner;
	Spinner midiChannelSpinner;
	Spinner tuningSpinner;
	NumberPicker tuningCents;
	Button okButton;
	Button chooseBackgroundButton;
	int buttonPressed = -1;
	String title;
	String message;
	String initialValue;
	String[] options;
	MainActivity activity;

	public SettingsDialog(MainActivity context) {
		super(context, R.style.Theme_Dialog);
		activity = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.title = "Settings";
		this.message = "test";
		this.initialValue = "";
		this.options = null;
		setContentView(R.layout.settings_dialog);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		keysSpinner = (Spinner) findViewById(R.id.keysSpinner);
		midiChannelSpinner = (Spinner) findViewById(R.id.midiChannelSpinner);
		tuningSpinner = (Spinner) findViewById(R.id.tuningSpinner);
		tuningCents = (NumberPicker)findViewById(R.id.tuningCents);
		okButton = (Button) findViewById(R.id.okButton);
		chooseBackgroundButton = (Button) findViewById(R.id.chooseBackgroundButton);

		Typeface typeface = clientModel.getTypeface(getContext());
		if (typeface != null) {
			okButton.setTypeface(typeface);
			chooseBackgroundButton.setTypeface(typeface);
		}

		ArrayAdapter<CharSequence> keysAdapter = Translator.getArrayAdapter(activity, R.layout.spinner_item, new String[] { "13", "20", "25", "32" });
		keysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		keysSpinner.setAdapter(keysAdapter);
		keysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int keysSelection = keysSpinner.getSelectedItemPosition();
				activity.setKeyboardSize(keysSelection);
				clientModel.setKeyboardSize(keysSelection);
				clientModel.savePreferences(activity);
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});
		int keySelection = clientModel.getKeyboardSize();
		keysSpinner.setSelection(keySelection);
		
		ArrayAdapter<CharSequence> midiChannelAdapter = Translator.getArrayAdapter(activity, R.layout.spinner_item, new String[] { "Any", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" });
		midiChannelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		midiChannelSpinner.setAdapter(midiChannelAdapter);
		midiChannelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int midiChannel = midiChannelSpinner.getSelectedItemPosition();
				activity.setMidiChannel(midiChannel);
				clientModel.setMidiChannel(midiChannel);
				clientModel.savePreferences(activity);
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});
		midiChannelSpinner.setSelection(clientModel.getMidiChannel());

		ArrayAdapter<CharSequence> tuningAdapter = Translator.getArrayAdapter(activity, R.layout.spinner_item, new String[] { "Equal Temperament", "Just Intonation", "Out of Tune" });
		tuningAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tuningSpinner.setAdapter(tuningAdapter);
		tuningSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int tuningSelection = tuningSpinner.getSelectedItemPosition();
				activity.setTuning(tuningSelection);
				clientModel.setSampleRateReducer(tuningSelection);
				clientModel.savePreferences(activity);
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});
		int tuningSelection = clientModel.getSampleRateReducer();
		tuningSpinner.setSelection(tuningSelection);

		tuningCents.setCurrent(clientModel.getTuningCents());
		tuningCents.setOnChangeListener(new NumberPicker.OnChangedListener() {
			public void onChanged(NumberPicker picker, int oldVal, int newVal) {
				activity.setTuningCents(tuningCents.getCurrent());
				clientModel.setTuningCents(tuningCents.getCurrent());
				clientModel.savePreferences(activity);
			}
		});

		okButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				buttonPressed = 0;
				SettingsDialog.this.dismiss();
				SettingsDialog.this.cancel();
				return true;
			}

		});
		chooseBackgroundButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				chooseBackground();
				return true;
			}

		});

		Translator.getTranslator().translate(this.getWindow().getDecorView());
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	public void chooseBackground() {
		Class[] themes;
		if (Build.VERSION.SDK_INT >= 16 && (ClientModel.getClientModel().isFullVersion() || ClientModel.getClientModel().isGoggleDogPass())) {  // needed for custom background
			themes =  new Class[] { //
					OnyxTheme.class, MetalTheme.class, WoodTheme.class, //
					AuraTheme.class, IceTheme.class, TeaTheme.class, //
					SpaceTheme.class, SunsetTheme.class, TropicalTheme.class, //
					CustomTheme.class //
			};
		} else {
			themes =  new Class[] { //
					OnyxTheme.class, MetalTheme.class, WoodTheme.class, //
					AuraTheme.class, IceTheme.class, TeaTheme.class, //
					SpaceTheme.class, SunsetTheme.class, TropicalTheme.class //
			};
		}
		final SelectItemDialog selectItemDialog = new SelectItemDialog(activity, "Choose a background", themes, null);
		selectItemDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				if (selectItemDialog.getItemSelected() != null) {
					String option = selectItemDialog.getItemSelected().getName();
					clientModel.setBackgroundName(option);
					clientModel.savePreferences(getContext());
					activity.setTheme(option, null);
				}
			}
		});
		selectItemDialog.show();
	}

}

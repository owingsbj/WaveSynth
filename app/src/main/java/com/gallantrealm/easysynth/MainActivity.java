package com.gallantrealm.easysynth;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import com.gallantrealm.android.ContentUriUtil;
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
import com.gallantrealm.android.InputDialog;
import com.gallantrealm.android.MessageDialog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.newrelic.agent.android.NewRelic;

public class MainActivity extends Activity implements OnTouchListener, OnSeekBarChangeListener, OnCheckedChangeListener, View.OnClickListener {

	public ClientModel clientModel = ClientModel.getClientModel();

	View mainLayout;

	Sound sound; // last sound loaded
	long soundLoadTime; // time sound was loaded

	View buttonPane;
	Button buyButton;
	Button soundButton;
	Button harmonicsButton;
	Button filterButton;
	Button ampButton;
	Button vibratoButton;
	Button vibrato2Button;
	Button echoButton;
	Button sequencerButton;
	Button recordButton;
	Button settingsButton;
	View soundPane;
	View harmonicsPane;
	View filterPane;
	View ampPane;
	View vibratoPane;
	View vibrato2Pane;
	View echoPane;
	View sequencerPane;
	View recordPane;
	View keyboardPane;

	Spinner soundSpinner;
	Button saveButton;
	Button sendButton;
	Button deleteButton;

	RadioButton monoRadio;
	RadioButton polyRadio;
	RadioButton chorusRadio;
	SeekBar chorusWidth;
	SeekBar portamentoAmount;
	Spinner velocitySpinner;
	Spinner expressionSpinner;
	RadioButton octave2Radio;
	RadioButton octave3Radio;
	RadioButton octave4Radio;
	RadioButton octave5Radio;
	RadioButton octave6Radio;
	CheckBox holdButton;
	Spinner keySpinner;

	SeekBar harmonic1;
	SeekBar harmonic2;
	SeekBar harmonic3;
	SeekBar harmonic4;
	SeekBar harmonic5;
	SeekBar harmonic6;
	SeekBar harmonic7;
	SeekBar harmonic8;
	SeekBar harmonic9;
	SeekBar harmonic10;
	SeekBar harmonic11;
	SeekBar harmonic12;
	SeekBar harmonic13;
	SeekBar harmonic14;
	SeekBar harmonic15;
	SeekBar harmonic16;
	SeekBar harmonic17;
	SeekBar harmonic18;
	SeekBar harmonic19;
	SeekBar harmonic20;
	SeekBar harmonic21;
	SeekBar harmonic22;
	SeekBar harmonic23;
	SeekBar harmonic24;
	SeekBar harmonic25;
	SeekBar harmonic26;
	SeekBar harmonic27;
	SeekBar harmonic28;
	SeekBar harmonic29;
	SeekBar harmonic30;
	SeekBar harmonic31;
	SeekBar harmonic32;

	Spinner filterTypeSpinner;
	SeekBar filterResonance;
	SeekBar filterLow;
	SeekBar filterHigh;
	SeekBar filterEnvAttack;
	SeekBar filterEnvDecay;
	SeekBar filterEnvSustain;
	SeekBar filterEnvRelease;

	SeekBar ampVolume;
	SeekBar ampAttack;
	SeekBar ampDecay;
	SeekBar ampSustain;
	SeekBar ampRelease;
	SeekBar ampOverdrive;
	CheckBox ampOverdrivePerVoiceCheckBox;

	Spinner vibratoDestinationSpinner;
	Spinner vibratoTypeSpinner;
	SeekBar vibratoAmount;
	SeekBar vibratoRate;
	CheckBox vibratoSyncCheckBox;
	SeekBar vibratoAttack;
	SeekBar vibratoDecay;

	Spinner vibrato2DestinationSpinner;
	Spinner vibrato2TypeSpinner;
	SeekBar vibrato2Amount;
	SeekBar vibrato2Rate;
	CheckBox vibrato2SyncCheckBox;
	SeekBar vibrato2Attack;
	SeekBar vibrato2Decay;

	SeekBar echoAmount;
	SeekBar echoDelay;
	SeekBar echoFeedback;
	SeekBar flangeAmount;
	SeekBar flangeRate;

	SeekBar sequence1;
	SeekBar sequence2;
	SeekBar sequence3;
	SeekBar sequence4;
	SeekBar sequence5;
	SeekBar sequence6;
	SeekBar sequence7;
	SeekBar sequence8;
	SeekBar sequence9;
	SeekBar sequence10;
	SeekBar sequence11;
	SeekBar sequence12;
	SeekBar sequence13;
	SeekBar sequence14;
	SeekBar sequence15;
	SeekBar sequence16;
	// SeekBar sequenceRate;
	Button sequenceRateUpButton;
	Button sequenceRateDownButton;
	TextView sequenceRateTextView;
	CheckBox sequenceLoopCheckBox;

	TextView recordTime;
	Button recordStartButton;
	Button recordStopButton;
	Button recordPlayButton;
	Button recordSaveButton;

	View keyboard;
	int keyboardLocation[];
	Button key1;
	Button key2;
	Button key3;
	Button key4;
	Button key5;
	Button key6;
	Button key7;
	Button key8;
	Button key9;
	Button key10;
	Button key11;
	Button key12;
	Button key13;
	Button key14;
	Button key15;
	Button key16;
	Button key17;
	Button key18;
	Button key19;
	Button key20;
	Button key21;
	Button key22;
	Button key23;
	Button key24;
	Button key25;
	Button key26;
	Button key27;
	Button key28;
	Button key29;
	Button key30;
	Button key31;
	Button key32;

	int[] keyvoice = new int[25];

	WaveSynth synth;

	private boolean dirty;

	int myMidiChannel;

	PowerManager.WakeLock wakelock;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// start new relic monitoring
		// NewRelic.withApplicationToken("AA3df2239d290a22408ba275086e61834efc7eae94").start(this.getApplication());

		setContentView(R.layout.synth_screen);

		clientModel.loadPreferences(this);

		synth = new WaveSynth(this);

		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		mainLayout = findViewById(R.id.mainLayout);
		Translator.setTranslator(new EasySynthTranslator());

		Translator.getTranslator().translate(mainLayout);

		buyButton = (Button) findViewById(R.id.buyButton);
		soundButton = (Button) findViewById(R.id.soundButton);
		harmonicsButton = (Button) findViewById(R.id.harmonicsButton);
		filterButton = (Button) findViewById(R.id.filterButton);
		ampButton = (Button) findViewById(R.id.ampButton);
		vibratoButton = (Button) findViewById(R.id.vibratoButton);
		vibrato2Button = (Button) findViewById(R.id.vibrato2Button);
		echoButton = (Button) findViewById(R.id.effectsButton);
		sequencerButton = (Button) findViewById(R.id.sequencerButton);
		recordButton = (Button) findViewById(R.id.recordButton);
		settingsButton = (Button) findViewById(R.id.settingsButton);

		soundPane = findViewById(R.id.soundPane);
		harmonicsPane = findViewById(R.id.harmonicsPane);
		filterPane = findViewById(R.id.filterPane);
		ampPane = findViewById(R.id.ampPane);
		vibratoPane = findViewById(R.id.vibratoPane);
		vibrato2Pane = findViewById(R.id.vibrato2Pane);
		echoPane = findViewById(R.id.effectsPane);
		sequencerPane = findViewById(R.id.sequencerPane);
		recordPane = findViewById(R.id.recordPane);
		keyboardPane = findViewById(R.id.keyboardPane);

		soundSpinner = (Spinner) soundPane.findViewById(R.id.soundSpinner);
		saveButton = (Button) soundPane.findViewById(R.id.saveButton);
		sendButton = (Button) soundPane.findViewById(R.id.sendButton);
		deleteButton = (Button) soundPane.findViewById(R.id.deleteButton);

		monoRadio = (RadioButton) soundPane.findViewById(R.id.monoRadio);
		polyRadio = (RadioButton) soundPane.findViewById(R.id.polyRadio);
		chorusRadio = (RadioButton) soundPane.findViewById(R.id.chorusRadio);
		chorusWidth = (SeekBar) soundPane.findViewById(R.id.chorusWidth);
		portamentoAmount = (SeekBar) soundPane.findViewById(R.id.portamentoAmount);
		velocitySpinner = (Spinner) soundPane.findViewById(R.id.velocitySpinner);
		expressionSpinner = (Spinner) soundPane.findViewById(R.id.expressionSpinner);
		octave2Radio = (RadioButton) soundPane.findViewById(R.id.octave2Radio);
		octave3Radio = (RadioButton) soundPane.findViewById(R.id.octave3Radio);
		octave4Radio = (RadioButton) soundPane.findViewById(R.id.octave4Radio);
		octave5Radio = (RadioButton) soundPane.findViewById(R.id.octave5Radio);
		octave6Radio = (RadioButton) soundPane.findViewById(R.id.octave6Radio);
		holdButton = (CheckBox) soundPane.findViewById(R.id.holdButton);
		keySpinner = (Spinner) soundPane.findViewById(R.id.keySpinner);

		harmonic1 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic1);
		harmonic2 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic2);
		harmonic3 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic3);
		harmonic4 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic4);
		harmonic5 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic5);
		harmonic6 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic6);
		harmonic7 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic7);
		harmonic8 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic8);
		harmonic9 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic9);
		harmonic10 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic10);
		harmonic11 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic11);
		harmonic12 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic12);
		harmonic13 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic13);
		harmonic14 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic14);
		harmonic15 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic15);
		harmonic16 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic16);
		harmonic17 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic17);
		harmonic18 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic18);
		harmonic19 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic19);
		harmonic20 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic20);
		harmonic21 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic21);
		harmonic22 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic22);
		harmonic23 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic23);
		harmonic24 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic24);
		harmonic25 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic25);
		harmonic26 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic26);
		harmonic27 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic27);
		harmonic28 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic28);
		harmonic29 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic29);
		harmonic30 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic30);
		harmonic31 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic31);
		harmonic32 = (SeekBar) harmonicsPane.findViewById(R.id.harmonic32);

		filterTypeSpinner = (Spinner) filterPane.findViewById(R.id.filterTypeSpinner);
		filterResonance = (SeekBar) filterPane.findViewById(R.id.filterResonance);
		filterLow = (SeekBar) filterPane.findViewById(R.id.filterLow);
		filterHigh = (SeekBar) filterPane.findViewById(R.id.filterHigh);
		filterEnvAttack = (SeekBar) filterPane.findViewById(R.id.filterEnvAttack);
		filterEnvDecay = (SeekBar) filterPane.findViewById(R.id.filterEnvDecay);
		filterEnvSustain = (SeekBar) filterPane.findViewById(R.id.filterEnvSustain);
		filterEnvRelease = (SeekBar) filterPane.findViewById(R.id.filterEnvRelease);

		ampVolume = (SeekBar) ampPane.findViewById(R.id.ampVolume);
		ampAttack = (SeekBar) ampPane.findViewById(R.id.ampAttack);
		ampDecay = (SeekBar) ampPane.findViewById(R.id.ampDecay);
		ampSustain = (SeekBar) ampPane.findViewById(R.id.ampSustain);
		ampRelease = (SeekBar) ampPane.findViewById(R.id.ampRelease);
		ampOverdrive = (SeekBar) ampPane.findViewById(R.id.ampOverdrive);
		ampOverdrivePerVoiceCheckBox = (CheckBox) ampPane.findViewById(R.id.ampOverdriverPerVoiceCheckBox);

		vibratoDestinationSpinner = (Spinner) vibratoPane.findViewById(R.id.vibratoDestinationSpinner);
		vibratoTypeSpinner = (Spinner) vibratoPane.findViewById(R.id.vibratoTypeSpinner);
		vibratoAmount = (SeekBar) vibratoPane.findViewById(R.id.vibratoAmount);
		vibratoRate = (SeekBar) vibratoPane.findViewById(R.id.vibratoRate);
		vibratoSyncCheckBox = (CheckBox) vibratoPane.findViewById(R.id.vibratoSyncCheckBox);
		vibratoAttack = (SeekBar) vibratoPane.findViewById(R.id.vibratoAttack);
		vibratoDecay = (SeekBar) vibratoPane.findViewById(R.id.vibratoDecay);

		vibrato2DestinationSpinner = (Spinner) vibrato2Pane.findViewById(R.id.vibratoDestinationSpinner);
		vibrato2TypeSpinner = (Spinner) vibrato2Pane.findViewById(R.id.vibratoTypeSpinner);
		vibrato2Amount = (SeekBar) vibrato2Pane.findViewById(R.id.vibratoAmount);
		vibrato2Rate = (SeekBar) vibrato2Pane.findViewById(R.id.vibratoRate);
		vibrato2SyncCheckBox = (CheckBox) vibrato2Pane.findViewById(R.id.vibratoSyncCheckBox);
		vibrato2Attack = (SeekBar) vibrato2Pane.findViewById(R.id.vibratoAttack);
		vibrato2Decay = (SeekBar) vibrato2Pane.findViewById(R.id.vibratoDecay);

		echoAmount = (SeekBar) echoPane.findViewById(R.id.echoAmount);
		echoDelay = (SeekBar) echoPane.findViewById(R.id.echoDelay);
		echoFeedback = (SeekBar) echoPane.findViewById(R.id.echoFeedback);
		flangeAmount = (SeekBar) echoPane.findViewById(R.id.flangeAmount);
		flangeRate = (SeekBar) echoPane.findViewById(R.id.flangeRate);

		sequence1 = (SeekBar) sequencerPane.findViewById(R.id.sequence1);
		sequence2 = (SeekBar) sequencerPane.findViewById(R.id.sequence2);
		sequence3 = (SeekBar) sequencerPane.findViewById(R.id.sequence3);
		sequence4 = (SeekBar) sequencerPane.findViewById(R.id.sequence4);
		sequence5 = (SeekBar) sequencerPane.findViewById(R.id.sequence5);
		sequence6 = (SeekBar) sequencerPane.findViewById(R.id.sequence6);
		sequence7 = (SeekBar) sequencerPane.findViewById(R.id.sequence7);
		sequence8 = (SeekBar) sequencerPane.findViewById(R.id.sequence8);
		sequence9 = (SeekBar) sequencerPane.findViewById(R.id.sequence9);
		sequence10 = (SeekBar) sequencerPane.findViewById(R.id.sequence10);
		sequence11 = (SeekBar) sequencerPane.findViewById(R.id.sequence11);
		sequence12 = (SeekBar) sequencerPane.findViewById(R.id.sequence12);
		sequence13 = (SeekBar) sequencerPane.findViewById(R.id.sequence13);
		sequence14 = (SeekBar) sequencerPane.findViewById(R.id.sequence14);
		sequence15 = (SeekBar) sequencerPane.findViewById(R.id.sequence15);
		sequence16 = (SeekBar) sequencerPane.findViewById(R.id.sequence16);
		// sequenceRate = (SeekBar)
		// sequencerPane.findViewById(R.id.sequenceRate);
		sequenceRateTextView = (TextView) sequencerPane.findViewById(R.id.sequenceRateTextView);
		sequenceRateUpButton = (Button) sequencerPane.findViewById(R.id.sequenceRateUpButton);
		sequenceRateDownButton = (Button) sequencerPane.findViewById(R.id.sequenceRateDownButton);
		sequenceLoopCheckBox = (CheckBox) sequencerPane.findViewById(R.id.sequenceLoopCheckBox);

		recordTime = (TextView) recordPane.findViewById(R.id.recordTime);
		recordStartButton = (Button) recordPane.findViewById(R.id.recordStartButton);
		recordStopButton = (Button) recordPane.findViewById(R.id.recordStopButton);
		recordPlayButton = (Button) recordPane.findViewById(R.id.recordPlayButton);
		recordSaveButton = (Button) recordPane.findViewById(R.id.recordSaveButton);

		keyboard = keyboardPane.findViewById(R.id.keyboard);
		key1 = (Button) keyboardPane.findViewById(R.id.key1);
		key2 = (Button) keyboardPane.findViewById(R.id.key2);
		key3 = (Button) keyboardPane.findViewById(R.id.key3);
		key4 = (Button) keyboardPane.findViewById(R.id.key4);
		key5 = (Button) keyboardPane.findViewById(R.id.key5);
		key6 = (Button) keyboardPane.findViewById(R.id.key6);
		key7 = (Button) keyboardPane.findViewById(R.id.key7);
		key8 = (Button) keyboardPane.findViewById(R.id.key8);
		key9 = (Button) keyboardPane.findViewById(R.id.key9);
		key10 = (Button) keyboardPane.findViewById(R.id.key10);
		key11 = (Button) keyboardPane.findViewById(R.id.key11);
		key12 = (Button) keyboardPane.findViewById(R.id.key12);
		key13 = (Button) keyboardPane.findViewById(R.id.key13);
		key14 = (Button) keyboardPane.findViewById(R.id.key14);
		key15 = (Button) keyboardPane.findViewById(R.id.key15);
		key16 = (Button) keyboardPane.findViewById(R.id.key16);
		key17 = (Button) keyboardPane.findViewById(R.id.key17);
		key18 = (Button) keyboardPane.findViewById(R.id.key18);
		key19 = (Button) keyboardPane.findViewById(R.id.key19);
		key20 = (Button) keyboardPane.findViewById(R.id.key20);
		key21 = (Button) keyboardPane.findViewById(R.id.key21);
		key22 = (Button) keyboardPane.findViewById(R.id.key22);
		key23 = (Button) keyboardPane.findViewById(R.id.key23);
		key24 = (Button) keyboardPane.findViewById(R.id.key24);
		key25 = (Button) keyboardPane.findViewById(R.id.key25);
		key26 = (Button) keyboardPane.findViewById(R.id.key26);
		key27 = (Button) keyboardPane.findViewById(R.id.key27);
		key28 = (Button) keyboardPane.findViewById(R.id.key28);
		key29 = (Button) keyboardPane.findViewById(R.id.key29);
		key30 = (Button) keyboardPane.findViewById(R.id.key30);
		key31 = (Button) keyboardPane.findViewById(R.id.key31);
		key32 = (Button) keyboardPane.findViewById(R.id.key32);

		// in order to avoid accidentally "pressing" the keyboard, add a click to panes
		mainLayout.setClickable(true);
		soundPane.setClickable(true);
		harmonicsPane.setClickable(true);
		filterPane.setClickable(true);
		ampPane.setClickable(true);
		vibratoPane.setClickable(true);
		vibrato2Pane.setClickable(true);
		echoPane.setClickable(true);
		sequencerPane.setClickable(true);
		recordPane.setClickable(true);
		keyboardPane.setClickable(true);
		OnTouchListener touchListener = new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		};
		mainLayout.setOnTouchListener(touchListener);
		soundPane.setOnTouchListener(touchListener);
		harmonicsPane.setOnTouchListener(touchListener);
		filterPane.setOnTouchListener(touchListener);
		ampPane.setOnTouchListener(touchListener);
		vibratoPane.setOnTouchListener(touchListener);
		vibrato2Pane.setOnTouchListener(touchListener);
		echoPane.setOnTouchListener(touchListener);
		sequencerPane.setOnTouchListener(touchListener);
		recordPane.setOnTouchListener(touchListener);
		keyboardPane.setOnTouchListener(touchListener);

		monoRadio.setOnCheckedChangeListener(this);
		polyRadio.setOnCheckedChangeListener(this);
		chorusRadio.setOnCheckedChangeListener(this);

		ArrayAdapter<CharSequence> velocityAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "None", "Volume", "Filter", "Both" });
		velocityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		velocitySpinner.setAdapter(velocityAdapter);
		velocitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int velocitySelection = velocitySpinner.getSelectedItemPosition();
				if (velocitySelection == 0) { // none
					synth.velocityType = WaveSynth.VELOCITY_NONE;
				} else if (velocitySelection == 1) { // volume
					synth.velocityType = WaveSynth.VELOCITY_VOLUME;
				} else if (velocitySelection == 2) { // filter
					synth.velocityType = WaveSynth.VELOCITY_FILTER;
				} else if (velocitySelection == 3) { // both
					synth.velocityType = WaveSynth.VELOCITY_BOTH;
				}
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});

		ArrayAdapter<CharSequence> expressionAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "None", "Volume", "Filter", "Vibrato", "Pitch", "Volume+Filter" });
		expressionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		expressionSpinner.setAdapter(expressionAdapter);
		expressionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int expressionSelection = expressionSpinner.getSelectedItemPosition();
				if (expressionSelection == 0) { // none
					synth.expressionType = WaveSynth.EXPRESSION_NONE;
				} else if (expressionSelection == 1) { // volume
					synth.expressionType = WaveSynth.EXPRESSION_VOLUME;
				} else if (expressionSelection == 2) { // filter
					synth.expressionType = WaveSynth.EXPRESSION_FILTER;
				} else if (expressionSelection == 3) { // vibrato
					synth.expressionType = WaveSynth.EXPRESSION_VIBRATO;
				} else if (expressionSelection == 4) { // pitch
					synth.expressionType = WaveSynth.EXPRESSION_PITCH;
				} else if (expressionSelection == 5) { // both
					synth.expressionType = WaveSynth.EXPRESSION_VOLUME_AND_FILTER;
				}
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});

		holdButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				synth.hold = isChecked;
				synth.keyRelease(1);
				synth.keyRelease(2);
				synth.keyRelease(3);
				synth.keyRelease(4);
				clearHolds();
			}
		});

		ArrayAdapter<CharSequence> keyAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B" });
		keyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		keySpinner.setAdapter(keyAdapter);
		keySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				int keySelection = keySpinner.getSelectedItemPosition();
				synth.key = keySelection;
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}
		});

		setTheme(clientModel.getBackgroundName(), clientModel.getCustomBackgroundPath());

		ArrayAdapter<CharSequence> filterTypeAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item,
				new String[] { "Low Pass", "Band Pass", "High Pass", "Fade", "Comb 1", "Comb 2", "Comb 3", "Comb 4", "Formant 1", "Formant 2", "Formant 3", "Formant 4" });
		filterTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filterTypeSpinner.setAdapter(filterTypeAdapter);

		ArrayAdapter<CharSequence> vibratoDestinationAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "Pitch", "Filter", "Amp" });
		vibratoDestinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vibratoDestinationSpinner.setAdapter(vibratoDestinationAdapter);
		vibratoDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				synth.vibratoDestination = vibratoDestinationSpinner.getSelectedItemPosition();
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}

		});

		ArrayAdapter<CharSequence> vibratoTypeAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "Wave", "Ramp Up", "Ramp Down", "Two Tone", "Noise", "Pulse 1/4", "Pulse 1/8", "Pulse 1/16" });
		vibratoTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vibratoTypeSpinner.setAdapter(vibratoTypeAdapter);
		vibratoTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				synth.vibratoType = vibratoTypeSpinner.getSelectedItemPosition();
				synth.genVibratoWaves();
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}

		});

		ArrayAdapter<CharSequence> vibrato2DestinationAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "Pitch", "Filter", "Amp", "Mod Pitch", "Mod Amp" });
		vibrato2DestinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vibrato2DestinationSpinner.setAdapter(vibrato2DestinationAdapter);
		vibrato2DestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				synth.vibrato2Destination = vibrato2DestinationSpinner.getSelectedItemPosition();
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}

		});

		ArrayAdapter<CharSequence> vibrato2TypeAdapter = Translator.getArrayAdapter(this, R.layout.spinner_item, new String[] { "Wave", "Ramp Up", "Ramp Down", "Two Tone", "Noise", "Pulse 1/4", "Pulse 1/8", "Pulse 1/16" });
		vibrato2TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vibrato2TypeSpinner.setAdapter(vibrato2TypeAdapter);
		vibrato2TypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				synth.vibrato2Type = vibrato2TypeSpinner.getSelectedItemPosition();
				synth.genVibratoWaves();
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}

		});

		soundButton.setSelected(true);
		harmonicsButton.setSelected(false);
		filterButton.setSelected(false);
		ampButton.setSelected(false);
		vibratoButton.setSelected(false);
		vibrato2Button.setSelected(false);
		echoButton.setSelected(false);
		sequencerButton.setSelected(false);
		recordButton.setSelected(false);
		soundPane.setVisibility(View.VISIBLE);
		harmonicsPane.setVisibility(View.GONE);
		filterPane.setVisibility(View.GONE);
		ampPane.setVisibility(View.GONE);
		vibratoPane.setVisibility(View.GONE);
		vibrato2Pane.setVisibility(View.GONE);
		echoPane.setVisibility(View.GONE);
		sequencerPane.setVisibility(View.GONE);
		recordPane.setVisibility(View.GONE);
		soundPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		harmonicsPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		filterPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		ampPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		vibratoPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		vibrato2Pane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		echoPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		sequencerPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		recordPane.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

		if (!clientModel.isFullVersion() && !clientModel.isGoggleDogPass()) {
			buyButton.setVisibility(View.VISIBLE);
		} else {
			buyButton.setVisibility(View.GONE);
			
		}
		buyButton.setOnClickListener(this);
		soundButton.setOnClickListener(this);
		harmonicsButton.setOnClickListener(this);
		filterButton.setOnClickListener(this);
		ampButton.setOnClickListener(this);
		vibratoButton.setOnClickListener(this);
		vibrato2Button.setOnClickListener(this);
		echoButton.setOnClickListener(this);
		sequencerButton.setOnClickListener(this);
		recordButton.setOnClickListener(this);
		recordStartButton.setOnClickListener(this);
		recordStopButton.setOnClickListener(this);
		recordPlayButton.setOnClickListener(this);
		recordSaveButton.setOnClickListener(this);
		settingsButton.setOnClickListener(this);

		// initialize the sound spinner
		updateSoundSpinner();
		// setup the selection listener
		soundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				loadSound((String) soundSpinner.getSelectedItem());
			}

			@Override
			public void onNothingSelected(AdapterView av) {
				System.out.println("Nothing selected");
			}

		});
		// select the sound from last session, or the first sound
		String soundName = clientModel.getInstrumentName();
		int soundPosition = 0;
		for (int i = 0; i < soundSpinner.getCount(); i++) {
			if (soundName.equals(soundSpinner.getItemAtPosition(i))) {
				soundPosition = i;
			}
		}
		soundSpinner.setSelection(soundPosition);

		if (clientModel.getPlayCount() <= 1) {
			// Choose a good default keyboard size
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			double screenSize = Math.sqrt(Math.pow(metrics.heightPixels / metrics.ydpi, 2) + Math.pow(metrics.widthPixels / metrics.xdpi, 2));
			System.out.println("ScreenSize is " + screenSize);
			if (screenSize >= 9.5) {
				clientModel.setKeyboardSize(3);
			} else if (screenSize >= 6.5) {
				clientModel.setKeyboardSize(2);
			} else if (screenSize >= 4.5) {
				clientModel.setKeyboardSize(1);
			} else {
				clientModel.setKeyboardSize(0);
			}
		}
		setKeyboardSize(clientModel.getKeyboardSize());

		setMidiChannel(clientModel.getMidiChannel());

		synth.tuning = clientModel.getSampleRateReducer();

		synth.tuningCents = clientModel.getTuningCents();

		saveButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);
		chorusWidth.setOnSeekBarChangeListener(this);
		octave2Radio.setOnCheckedChangeListener(this);
		octave3Radio.setOnCheckedChangeListener(this);
		octave4Radio.setOnCheckedChangeListener(this);
		octave5Radio.setOnCheckedChangeListener(this);
		octave6Radio.setOnCheckedChangeListener(this);

		harmonic1.setOnSeekBarChangeListener(this);
		harmonic2.setOnSeekBarChangeListener(this);
		harmonic3.setOnSeekBarChangeListener(this);
		harmonic4.setOnSeekBarChangeListener(this);
		harmonic5.setOnSeekBarChangeListener(this);
		harmonic6.setOnSeekBarChangeListener(this);
		harmonic7.setOnSeekBarChangeListener(this);
		harmonic8.setOnSeekBarChangeListener(this);
		harmonic9.setOnSeekBarChangeListener(this);
		harmonic10.setOnSeekBarChangeListener(this);
		harmonic11.setOnSeekBarChangeListener(this);
		harmonic12.setOnSeekBarChangeListener(this);
		harmonic13.setOnSeekBarChangeListener(this);
		harmonic14.setOnSeekBarChangeListener(this);
		harmonic15.setOnSeekBarChangeListener(this);
		harmonic16.setOnSeekBarChangeListener(this);
		harmonic17.setOnSeekBarChangeListener(this);
		harmonic18.setOnSeekBarChangeListener(this);
		harmonic19.setOnSeekBarChangeListener(this);
		harmonic20.setOnSeekBarChangeListener(this);
		harmonic21.setOnSeekBarChangeListener(this);
		harmonic22.setOnSeekBarChangeListener(this);
		harmonic23.setOnSeekBarChangeListener(this);
		harmonic24.setOnSeekBarChangeListener(this);
		harmonic25.setOnSeekBarChangeListener(this);
		harmonic26.setOnSeekBarChangeListener(this);
		harmonic27.setOnSeekBarChangeListener(this);
		harmonic28.setOnSeekBarChangeListener(this);
		harmonic29.setOnSeekBarChangeListener(this);
		harmonic30.setOnSeekBarChangeListener(this);
		harmonic31.setOnSeekBarChangeListener(this);
		harmonic32.setOnSeekBarChangeListener(this);
		filterTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView av, View v, int arg2, long arg3) {
				if (filterTypeSpinner.getSelectedItem().equals("Low Pass")) {
					synth.filterType = synth.FILTER_LOWPASS;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Band Pass")) {
					synth.filterType = synth.FILTER_BANDPASS;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Fade")) {
					synth.filterType = synth.FILTER_FADE;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("High Pass")) {
					synth.filterType = synth.FILTER_HIGHPASS;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Comb 1")) {
					synth.filterType = synth.FILTER_COMB1;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Comb 2")) {
					synth.filterType = synth.FILTER_COMB2;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Comb 3")) {
					synth.filterType = synth.FILTER_COMB3;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Comb 4")) {
					synth.filterType = synth.FILTER_COMB4;
					synth.filterFixed = false;
				} else if (filterTypeSpinner.getSelectedItem().equals("Formant 1")) {
					synth.filterType = synth.FILTER_FORMANT1;
					synth.filterFixed = true;
				} else if (filterTypeSpinner.getSelectedItem().equals("Formant 2")) {
					synth.filterType = synth.FILTER_FORMANT2;
					synth.filterFixed = true;
				} else if (filterTypeSpinner.getSelectedItem().equals("Formant 3")) {
					synth.filterType = synth.FILTER_FORMANT3;
					synth.filterFixed = true;
				} else if (filterTypeSpinner.getSelectedItem().equals("Formant 4")) {
					synth.filterType = synth.FILTER_FORMANT4;
					synth.filterFixed = true;
				}
				synth.genWaves();
				synth.updateParams();
			}

			@Override
			public void onNothingSelected(AdapterView av) {
			}

		});
		filterResonance.setOnSeekBarChangeListener(this);
		filterLow.setOnSeekBarChangeListener(this);
		filterHigh.setOnSeekBarChangeListener(this);
		filterEnvAttack.setOnSeekBarChangeListener(this);
		filterEnvDecay.setOnSeekBarChangeListener(this);
		filterEnvSustain.setOnSeekBarChangeListener(this);
		filterEnvRelease.setOnSeekBarChangeListener(this);
		ampVolume.setOnSeekBarChangeListener(this);
		ampAttack.setOnSeekBarChangeListener(this);
		ampDecay.setOnSeekBarChangeListener(this);
		ampSustain.setOnSeekBarChangeListener(this);
		ampRelease.setOnSeekBarChangeListener(this);
		ampOverdrive.setOnSeekBarChangeListener(this);
		ampOverdrivePerVoiceCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				synth.ampOverdrivePerVoice = isChecked;
				synth.updateParams();
			}
		});
		vibratoAmount.setOnSeekBarChangeListener(this);
		vibratoRate.setOnSeekBarChangeListener(this);
		vibratoSyncCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				synth.vibratoSync = isChecked;
				synth.updateParams();
			}
		});
		vibratoAttack.setOnSeekBarChangeListener(this);
		vibratoDecay.setOnSeekBarChangeListener(this);
		vibrato2Amount.setOnSeekBarChangeListener(this);
		vibrato2Rate.setOnSeekBarChangeListener(this);
		vibrato2SyncCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				synth.vibrato2Sync = isChecked;
				synth.updateParams();
			}
		});
		vibrato2Attack.setOnSeekBarChangeListener(this);
		vibrato2Decay.setOnSeekBarChangeListener(this);
		echoAmount.setOnSeekBarChangeListener(this);
		echoDelay.setOnSeekBarChangeListener(this);
		echoFeedback.setOnSeekBarChangeListener(this);
		flangeAmount.setOnSeekBarChangeListener(this);
		flangeRate.setOnSeekBarChangeListener(this);
		portamentoAmount.setOnSeekBarChangeListener(this);
		sequence1.setOnSeekBarChangeListener(this);
		sequence2.setOnSeekBarChangeListener(this);
		sequence3.setOnSeekBarChangeListener(this);
		sequence4.setOnSeekBarChangeListener(this);
		sequence5.setOnSeekBarChangeListener(this);
		sequence6.setOnSeekBarChangeListener(this);
		sequence7.setOnSeekBarChangeListener(this);
		sequence8.setOnSeekBarChangeListener(this);
		sequence9.setOnSeekBarChangeListener(this);
		sequence10.setOnSeekBarChangeListener(this);
		sequence11.setOnSeekBarChangeListener(this);
		sequence12.setOnSeekBarChangeListener(this);
		sequence13.setOnSeekBarChangeListener(this);
		sequence14.setOnSeekBarChangeListener(this);
		sequence15.setOnSeekBarChangeListener(this);
		sequence16.setOnSeekBarChangeListener(this);
		// sequenceRate.setOnSeekBarChangeListener(this);
		sequenceRateUpButton.setOnTouchListener(this);
		sequenceRateDownButton.setOnTouchListener(this);
		sequenceLoopCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				synth.sequenceLoop = isChecked;
				synth.updateParams();
			}
		});
		keyboard.setOnTouchListener(this);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakelock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "WaveSynth:WakeLock");
	}

	@Override
	protected void onDestroy() {
		synth.stop();
		super.onDestroy();
	}
	
	boolean selectingCustomTheme;

	/**
	 * Needed for in-app purchase, file chooser, and other actions that launch separate activities.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Pass on the activity result to clientModel for proper in-app billing
		// behavior
		if (clientModel.handleActivityResult(requestCode, resultCode, data)) {
			return;
		}
		
		if (selectingCustomTheme) {
			if (data != null) {
				String path = "";
				Uri mImageCaptureUri = data.getData();
				System.out.println("Selected Uri is " + mImageCaptureUri);
				path = ContentUriUtil.getPath(this, mImageCaptureUri);
				System.out.println("Image selected: " + path);
				setCustomBackground(path);
				ClientModel.getClientModel().setCustomBackgroundPath(path);
				ClientModel.getClientModel().savePreferences(this);
			}
			selectingCustomTheme = false;
		}
		
		// not handled, so let default handle
		super.onActivityResult(requestCode, resultCode, data);
	}

	@SuppressLint("NewApi")
	private void setCustomBackground(String path) {
		if (path == null || path.length() == 0) {
			return;
		}
		System.out.println("Loading custom background " + path);
		try {
			File file = new File(path);
			InputStream inStream = new BufferedInputStream(new FileInputStream(file), 65536);
			Bitmap tbitmap = BitmapFactory.decodeStream(inStream);
			inStream.close();
			Bitmap bitmap = tbitmap.copy(tbitmap.getConfig(), true);
			tbitmap.recycle();
			mainLayout.setBackground(new BitmapDrawable(bitmap));
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(MainActivity.this, "Custom background cannot be opened.", Toast.LENGTH_SHORT).show();
		}
	}

	boolean stopUpdateThread;
	UpdateThread updateThread;

	class UpdateThread extends Thread {

		boolean stopThread;;

		public void safeStop() {
			stopThread = true;
		}

		@Override
		public void run() {
			try {
				while (!stopThread) {
					if (synth != null) {
						final int time = synth.getRecordTime();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								int mins = time / 60;
								int secs = time % 60;
								if (secs < 10) {
									recordTime.setText("" + mins + ":0" + secs);
								} else {
									recordTime.setText("" + mins + ":" + secs);
								}
							}
						});
						Thread.sleep(250);
					}
				}
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v == buyButton) {
//			if (!clientModel.isFullVersion() && !clientModel.isGoggleDogPass() && Locale.getDefault().getLanguage().startsWith("en")) {
//				final MessageDialog fullVersionPrompt = new MessageDialog(this, "Full Version", "Requires full version.", new String[] { "Buy", "Cancel" });
//				fullVersionPrompt.setOnDismissListener(new DialogInterface.OnDismissListener() {
//					@Override
//					public void onDismiss(DialogInterface dialog) {
//						if (fullVersionPrompt.getButtonPressed() == 0) {
							clientModel.buyFullVersion();
//						}
//					}
//				});
//				fullVersionPrompt.show();
//				return;
//			}
		} else if (v == saveButton) {
			saveSound();
		} else if (v == sendButton) {
			sendSound();
		} else if (v == deleteButton) {
			deleteSound();
		} else if (v == recordStartButton) {
			startRecording();
		} else if (v == recordStopButton) {
			stopRecording();
		} else if (v == recordPlayButton) {
			playbackRecording();
		} else if (v == recordSaveButton) {
			saveRecording();
		} else if (v == settingsButton) {
			final SettingsDialog settingsDialog = new SettingsDialog(this);
			settingsDialog.show();
		} else {
			soundPane.setVisibility(View.GONE);
			harmonicsPane.setVisibility(View.GONE);
			filterPane.setVisibility(View.GONE);
			ampPane.setVisibility(View.GONE);
			vibratoPane.setVisibility(View.GONE);
			vibrato2Pane.setVisibility(View.GONE);
			echoPane.setVisibility(View.GONE);
			sequencerPane.setVisibility(View.GONE);
			recordPane.setVisibility(View.GONE);
			soundButton.setSelected(false);
			harmonicsButton.setSelected(false);
			filterButton.setSelected(false);
			ampButton.setSelected(false);
			vibratoButton.setSelected(false);
			vibrato2Button.setSelected(false);
			echoButton.setSelected(false);
			sequencerButton.setSelected(false);
			recordButton.setSelected(false);
			if (v == soundButton) {
				soundPane.setVisibility(View.VISIBLE);
				soundButton.setSelected(true);
			} else if (v == harmonicsButton) {
				harmonicsPane.setVisibility(View.VISIBLE);
				harmonicsButton.setSelected(true);
			} else if (v == filterButton) {
				filterPane.setVisibility(View.VISIBLE);
				filterButton.setSelected(true);
			} else if (v == ampButton) {
				ampPane.setVisibility(View.VISIBLE);
				ampButton.setSelected(true);
			} else if (v == vibratoButton) {
				vibratoPane.setVisibility(View.VISIBLE);
				vibratoButton.setSelected(true);
			} else if (v == vibrato2Button) {
				vibrato2Pane.setVisibility(View.VISIBLE);
				vibrato2Button.setSelected(true);
			} else if (v == echoButton) {
				echoPane.setVisibility(View.VISIBLE);
				echoButton.setSelected(true);
			} else if (v == sequencerButton) {
				sequencerPane.setVisibility(View.VISIBLE);
				sequencerButton.setSelected(true);
			} else if (v == recordButton) {
				recordPane.setVisibility(View.VISIBLE);
				recordButton.setSelected(true);
			}
		}
	}

	public void setTheme(String option, String customImagePath) {
		if (option == null || option.equals(OnyxTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.onyx_background);
		} else if (option.equals(WoodTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.wood_background);
		} else if (option.equals(AuraTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.aura_background);
		} else if (option.equals(IceTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.ice_background);
		} else if (option.equals(TeaTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.tea_background);
		} else if (option.equals(MetalTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.metal_background);
		} else if (option.equals(SpaceTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.space_background);
		} else if (option.equals(SunsetTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.sunset_background);
		} else if (option.equals(TropicalTheme.class.getName())) {
			mainLayout.setBackgroundResource(R.raw.tropical_background);
		} else if (option.equals(CustomTheme.class.getName())) {
			if (customImagePath != null) {
				setCustomBackground(customImagePath);
			} else {
				mainLayout.setBackgroundResource(R.raw.onyx_background);
				selectingCustomTheme = true;
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Complete action using"), 0);
			}
		}
	}

	public void setKeyboardSize(int keysSelection) {
		if (keysSelection == 0) { // 13
			key14.setVisibility(View.GONE);
			key15.setVisibility(View.GONE);
			key16.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key16spacer).setVisibility(View.GONE);
			key17.setVisibility(View.GONE);
			key18.setVisibility(View.GONE);
			key19.setVisibility(View.GONE);
			key20.setVisibility(View.GONE);
			key21.setVisibility(View.GONE);
			key22.setVisibility(View.GONE);
			key23.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key23spacer).setVisibility(View.GONE);
			key24.setVisibility(View.GONE);
			key25.setVisibility(View.GONE);
			key26.setVisibility(View.GONE);
			key27.setVisibility(View.GONE);
			key28.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key28spacer).setVisibility(View.GONE);
			key29.setVisibility(View.GONE);
			key30.setVisibility(View.GONE);
			key31.setVisibility(View.GONE);
			key32.setVisibility(View.GONE);
		} else if (keysSelection == 1) { // 20
			key14.setVisibility(View.VISIBLE);
			key15.setVisibility(View.VISIBLE);
			key16.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key16spacer).setVisibility(View.VISIBLE);
			key17.setVisibility(View.VISIBLE);
			key18.setVisibility(View.VISIBLE);
			key19.setVisibility(View.VISIBLE);
			key20.setVisibility(View.VISIBLE);
			key21.setVisibility(View.GONE);
			key22.setVisibility(View.GONE);
			key23.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key23spacer).setVisibility(View.GONE);
			key24.setVisibility(View.GONE);
			key25.setVisibility(View.GONE);
			key26.setVisibility(View.GONE);
			key27.setVisibility(View.GONE);
			key28.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key28spacer).setVisibility(View.GONE);
			key29.setVisibility(View.GONE);
			key30.setVisibility(View.GONE);
			key31.setVisibility(View.GONE);
			key32.setVisibility(View.GONE);
		} else if (keysSelection == 2) { // 25
			key14.setVisibility(View.VISIBLE);
			key15.setVisibility(View.VISIBLE);
			key16.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key16spacer).setVisibility(View.VISIBLE);
			key17.setVisibility(View.VISIBLE);
			key18.setVisibility(View.VISIBLE);
			key19.setVisibility(View.VISIBLE);
			key20.setVisibility(View.VISIBLE);
			key21.setVisibility(View.VISIBLE);
			key22.setVisibility(View.VISIBLE);
			key23.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key23spacer).setVisibility(View.VISIBLE);
			key24.setVisibility(View.VISIBLE);
			key25.setVisibility(View.VISIBLE);
			key26.setVisibility(View.GONE);
			key27.setVisibility(View.GONE);
			key28.setVisibility(View.GONE);
			keyboardPane.findViewById(R.id.key28spacer).setVisibility(View.GONE);
			key29.setVisibility(View.GONE);
			key30.setVisibility(View.GONE);
			key31.setVisibility(View.GONE);
			key32.setVisibility(View.GONE);
		} else if (keysSelection == 3) { // 32
			key14.setVisibility(View.VISIBLE);
			key15.setVisibility(View.VISIBLE);
			key16.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key16spacer).setVisibility(View.VISIBLE);
			key17.setVisibility(View.VISIBLE);
			key18.setVisibility(View.VISIBLE);
			key19.setVisibility(View.VISIBLE);
			key20.setVisibility(View.VISIBLE);
			key21.setVisibility(View.VISIBLE);
			key22.setVisibility(View.VISIBLE);
			key23.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key23spacer).setVisibility(View.VISIBLE);
			key24.setVisibility(View.VISIBLE);
			key25.setVisibility(View.VISIBLE);
			key26.setVisibility(View.VISIBLE);
			key27.setVisibility(View.VISIBLE);
			key28.setVisibility(View.VISIBLE);
			keyboardPane.findViewById(R.id.key28spacer).setVisibility(View.VISIBLE);
			key29.setVisibility(View.VISIBLE);
			key30.setVisibility(View.VISIBLE);
			key31.setVisibility(View.VISIBLE);
			key32.setVisibility(View.VISIBLE);
		}
	}

	public void setMidiChannel(int midiChannel) {
		this.myMidiChannel = midiChannel;
	}

	public void setTuning(int tuning) {
		synth.tuning = tuning;
		synth.updateParams();
	}

	public void setTuningCents(int cents) {
		synth.tuningCents = cents;
		synth.updateParams();
	}

	public void updateSoundSpinner() {
		ArrayAdapter<CharSequence> soundAdapter = new ArrayAdapter(this, R.layout.spinner_item);

		try {
			if (getIntent().getData() != null) {
				soundAdapter.add(getIntent().getData().toString());
			}
			String[] assets = getAssets().list("");
			for (int i = 0; i < assets.length; i++) {
				String filename = assets[i];
				int synthpos = filename.indexOf(".easysynth");
				if (synthpos >= 0) {
					String name = filename.substring(0, synthpos);
					soundAdapter.add(name);
				}
			}
			File filesDir = getFilesDir();
			File[] files = filesDir.listFiles();
			// Arrays.sort(files, new Comparator<File>() {
			//
			// };
			for (int i = 0; i < files.length; i++) {
				String filename = files[i].getName();
				boolean found = false;
				for (int j = 0; j < assets.length; j++) {
					if (filename.equals(assets[j])) {
						found = true;
					}
				}
				if (!found) {
					int synthpos = filename.indexOf(".easysynth");
					if (synthpos >= 0) {
						String name = filename.substring(0, synthpos);
						soundAdapter.add(name);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		soundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		soundSpinner.setAdapter(soundAdapter);
	}

	public void saveSound() {
		if (!clientModel.isFullVersion() && !clientModel.isGoggleDogPass()) {
			MessageDialog messageDialog = new MessageDialog(this, "Save Disabled", "Buy full version to enable save.", null);
			messageDialog.show();
			return;
		}
		String initiallSoundName = (String) soundSpinner.getSelectedItem();
		if (initiallSoundName.startsWith("file://")) {
			initiallSoundName = initiallSoundName.substring(initiallSoundName.lastIndexOf("/") + 1);
			initiallSoundName = initiallSoundName.substring(0, initiallSoundName.length() - ".easysynth".length());
		}
		final String initialSoundName = initiallSoundName;
		final InputDialog promptForName = new InputDialog(this, "Save", "Save sound as ", initialSoundName, new String[] { "Save", "Cancel" });
		promptForName.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				if (promptForName.getButtonPressed() == 0) {
					String soundName = promptForName.getValue();
					Sound sound = synth.generateSound();
					clientModel.saveObject(sound, soundName + ".easysynth", false);
					dirty = false;
					System.out.println("Saved sound as " + soundName + ".easysynth");

					// Uncomment to save for update
					// clientModel.exportObject(sound, soundName +
					// ".easysynth");

					if (!soundName.equals(initialSoundName)) {
						updateSoundSpinner();
						// find item
						int newSoundPosition = -1;
						for (int i = 0; i < soundSpinner.getCount(); i++) {
							if (soundName.equals(soundSpinner.getItemAtPosition(i))) {
								newSoundPosition = i;
							}
						}
						if (newSoundPosition >= 0) {
							soundSpinner.setSelection(newSoundPosition);
						}
					}
				}
			}
		});
		promptForName.show();
	}

	public void sendSound() {
		// if (!clientModel.isFullVersion()) {
		// final MessageDialog fullVersionPrompt = new MessageDialog(this, "Full
		// Version", "Sending sounds requires full version. Do you want to buy
		// the full version?", new String[] { "Buy", "Cancel" });
		// fullVersionPrompt.setOnDismissListener(new
		// DialogInterface.OnDismissListener() {
		// @Override
		// public void onDismiss(DialogInterface dialog) {
		// if (fullVersionPrompt.getButtonPressed() == 0) {
		// boolean launched = clientModel.buyFullVersion();
		// if (!launched) {
		// final MessageDialog messageDialog = new
		// MessageDialog(MainActivity.this, null, "There is a problem with
		// accessing the store.\nCheck your network connection and try again.",
		// new String[] { "OK" }, null);
		// messageDialog.show();
		// }
		// }
		// }
		// });
		// fullVersionPrompt.show();
		// return;
		// }
		final String soundName = (String) soundSpinner.getSelectedItem();
		try {
			Sound sound = synth.generateSound();
			File file = clientModel.exportObject(sound, soundName + ".easysynth");
			System.out.println("Exported sound as " + soundName + ".easysynth");

			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("application/vnd.gallantrealm.easysynth");
			Uri uri = Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, uri);
			intent.putExtra(Intent.EXTRA_SUBJECT, soundName);
			if (clientModel.isAmazon()) {
				intent.putExtra(Intent.EXTRA_TEXT, "Sharing an EasySynth instrument with you.  You can play it by installing EasySynth from the Amazon AppStore!");
			} else {
				intent.putExtra(Intent.EXTRA_TEXT, "Sharing an EasySynth instrument with you.  You can play it by installing EasySynth from Google Play at  http://play.google.com/store/apps/details?id=com.gallantrealm.easysynth");
			}
			clientModel.getContext().startActivity(intent);
		} catch (Exception e) { // might fail
			// ignore
		}
	}

	public void loadSound(String soundName) {
		long soundPlayTime = System.currentTimeMillis() - soundLoadTime;
		System.out.println("recordMetric: " + clientModel.getInstrumentName() + ": " + (soundPlayTime / 60000.0));
		// NewRelic.recordMetric(clientModel.getWorldName(), "SoundPlayTime",
		// soundPlayTime / 60000.0);

		if (soundName.startsWith("file://")) { // playing a sent sound
			sound = (Sound) clientModel.loadObject(soundName, false);
			applySound(sound);
			System.out.println("Loaded external file sound " + soundName);
		} else {
			sound = (Sound) clientModel.loadObject(soundName + ".easysynth", false);
			applySound(sound);
			System.out.println("Loaded internal file sound " + soundName + ".easysynth");
			clientModel.setInstrumentName(soundName);
			clientModel.savePreferences(this);
		}
		soundLoadTime = System.currentTimeMillis();
	}

	public void deleteSound() {
		final String soundName = (String) soundSpinner.getSelectedItem();
		final MessageDialog promptForDelete = new MessageDialog(this, "Delete", "Delete sound?", new String[] { "OK", "Cancel" });
		promptForDelete.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				if (promptForDelete.getButtonPressed() == 0) {
					clientModel.deleteObject(soundName + ".easysynth", false);
					System.out.println("Deleted sound " + soundName + ".easysynth");
					updateSoundSpinner();
				}
			}
		});
		promptForDelete.show();
	}

	public boolean unsavedRecording;

	public void startRecording() {
		if (unsavedRecording) {
			final MessageDialog message = new MessageDialog(MainActivity.this, null, "Erase unsaved recording?", new String[] { "OK", "Cancel" });
			message.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					if (message.getButtonPressed() == 0) {
						synth.startRecording();
						if (updateThread == null) {
							stopUpdateThread = false;
							updateThread = new UpdateThread();
							updateThread.start();
						}
						unsavedRecording = true;
					}
				}
			});
			message.show();
		} else {
			synth.startRecording();
			if (updateThread == null) {
				stopUpdateThread = false;
				updateThread = new UpdateThread();
				updateThread.start();
			}
			unsavedRecording = true;
		}
	}

	public void stopRecording() {
		synth.stopRecording();
		if (updateThread != null) {
			updateThread.safeStop();
			updateThread = null;
		}
	}

	public void playbackRecording() {
		synth.playbackRecording();
		if (updateThread == null) {
			stopUpdateThread = false;
			updateThread = new UpdateThread();
			updateThread.start();
		}
	}

	final private int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 123;

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
		case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				saveRecording();
			} else {
				Toast.makeText(MainActivity.this, "Cannot save.  Permission denied.", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	public String lastRecordName = "jam";

	@SuppressLint("NewApi")
	public void saveRecording() {
		if (!clientModel.isFullVersion() && !clientModel.isGoggleDogPass()) {
			MessageDialog messageDialog = new MessageDialog(this, "Save Disabled", "Buy full version to enable save.", null);
			messageDialog.show();
			return;
		}
		if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
			return;
		}
		File rootDir = new File("/sdcard");
		if (!rootDir.exists()) {
			rootDir = new File("/mnt/sdcard");
			if (!rootDir.exists()) {
				rootDir = Environment.getExternalStorageDirectory();
			}
		}
		final File easySynthDir = new File(rootDir.getPath() + "/EasySynth");
		if (!easySynthDir.exists()) {
			easySynthDir.mkdirs();
		}
		final InputDialog inputDialog = new InputDialog(this, "Save Recording", "Recording name:", lastRecordName, new String[] { "Save", "Cancel" });
		inputDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				if (inputDialog.getButtonPressed() == 0) {
					final String recordname = inputDialog.getValue();
					lastRecordName = recordname;
					final String filename = easySynthDir.getPath() + "/" + recordname + ".wav";
					System.out.println("Saving recording to " + filename);

					File file = new File(filename);
					synth.saveRecording(filename);
					sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

					System.out.println("Recording saved.");
					unsavedRecording = false;

					Toast toast = Toast.makeText(MainActivity.this, "Saved to " + filename, Toast.LENGTH_LONG);
					toast.show();

					// Intent intent = new Intent(Intent.ACTION_SEND);
					// intent.setType("audio/wav");
					// Uri uri = Uri.fromFile(file);
					// intent.putExtra(Intent.EXTRA_STREAM, uri);
					// intent.putExtra(Intent.EXTRA_SUBJECT, recordname +
					// ".wav");
					// intent.putExtra(Intent.EXTRA_TEXT,
					// "Sharing a recording with you. I made it LIVE using
					// EasySynth!");
					// try {
					// clientModel.getContext().startActivity(intent);
					// } catch (Exception e) {
					// MessageDialog message = new
					// MessageDialog(MainActivity.this, null, "Recording saved
					// at " +
					// filename + ". You will need to use a file manager app to
					// access the file.", new String[] { "OK"
					// });
					// message.show();
					// }

				}
			}
		});
		inputDialog.show();
	}

	boolean applyingASound = false;

	private void applySound(Sound sound) {
		if (sound == null) {
			System.out.println("Sound is null!");
			return;
		}
		applyingASound = true;
		synth.applySound(sound);
		updateControls();
		applyingASound = false;
	}

	private void updateControls() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!clientModel.isFullVersion() && !clientModel.isGoggleDogPass() && Locale.getDefault().getLanguage().startsWith("en")) {
					buyButton.setVisibility(View.VISIBLE);
				} else {
					buyButton.setVisibility(View.GONE);
				}
				applyingASound = true;
				// set the controls
				if (synth.mode == 0) {
					monoRadio.setChecked(true);
				} else if (synth.mode == 1) {
					polyRadio.setChecked(true);
				} else if (synth.mode == 2) {
					chorusRadio.setChecked(true);
				}
				chorusWidth.setProgress((int) Math.sqrt(synth.chorusWidth * 10000));
				portamentoAmount.setProgress((int) (synth.portamentoAmount * 101));
				velocitySpinner.setSelection(synth.velocityType);
				expressionSpinner.setSelection(synth.expressionType);
				if (synth.octave == 1) {
					octave2Radio.setChecked(true);
				} else if (synth.octave == 2) {
					octave3Radio.setChecked(true);
				} else if (synth.octave == 3) {
					octave4Radio.setChecked(true);
				} else if (synth.octave == 4) {
					octave5Radio.setChecked(true);
				} else if (synth.octave == 5) {
					octave6Radio.setChecked(true);
				}
				keySpinner.setSelection(synth.key);
				holdButton.setChecked(synth.hold);
				harmonic1.setProgress((int) (Math.sqrt(synth.harmonics[0] * 20000)));
				harmonic2.setProgress((int) (Math.sqrt(synth.harmonics[1] * 20000)));
				harmonic3.setProgress((int) (Math.sqrt(synth.harmonics[2] * 20000)));
				harmonic4.setProgress((int) (Math.sqrt(synth.harmonics[3] * 20000)));
				harmonic5.setProgress((int) (Math.sqrt(synth.harmonics[4] * 20000)));
				harmonic6.setProgress((int) (Math.sqrt(synth.harmonics[5] * 20000)));
				harmonic7.setProgress((int) (Math.sqrt(synth.harmonics[6] * 20000)));
				harmonic8.setProgress((int) (Math.sqrt(synth.harmonics[7] * 20000)));
				harmonic9.setProgress((int) (Math.sqrt(synth.harmonics[8] * 20000)));
				harmonic10.setProgress((int) (Math.sqrt(synth.harmonics[9] * 20000)));
				harmonic11.setProgress((int) (Math.sqrt(synth.harmonics[10] * 20000)));
				harmonic12.setProgress((int) (Math.sqrt(synth.harmonics[11] * 20000)));
				if (synth.harmonics.length > 12) {
					harmonic13.setProgress((int) (Math.sqrt(synth.harmonics[12] * 20000)));
					harmonic14.setProgress((int) (Math.sqrt(synth.harmonics[13] * 20000)));
					harmonic15.setProgress((int) (Math.sqrt(synth.harmonics[14] * 20000)));
					harmonic16.setProgress((int) (Math.sqrt(synth.harmonics[15] * 20000)));
				} else {
					harmonic13.setProgress(0);
					harmonic14.setProgress(0);
					harmonic15.setProgress(0);
					harmonic16.setProgress(0);
				}
				if (synth.harmonics.length > 16) {
					harmonic17.setProgress((int) (Math.sqrt(synth.harmonics[16] * 20000)));
					harmonic18.setProgress((int) (Math.sqrt(synth.harmonics[17] * 20000)));
					harmonic19.setProgress((int) (Math.sqrt(synth.harmonics[18] * 20000)));
					harmonic20.setProgress((int) (Math.sqrt(synth.harmonics[19] * 20000)));
					harmonic21.setProgress((int) (Math.sqrt(synth.harmonics[20] * 20000)));
					harmonic22.setProgress((int) (Math.sqrt(synth.harmonics[21] * 20000)));
					harmonic23.setProgress((int) (Math.sqrt(synth.harmonics[22] * 20000)));
					harmonic24.setProgress((int) (Math.sqrt(synth.harmonics[23] * 20000)));
					harmonic25.setProgress((int) (Math.sqrt(synth.harmonics[24] * 20000)));
					harmonic26.setProgress((int) (Math.sqrt(synth.harmonics[25] * 20000)));
					harmonic27.setProgress((int) (Math.sqrt(synth.harmonics[26] * 20000)));
					harmonic28.setProgress((int) (Math.sqrt(synth.harmonics[27] * 20000)));
					harmonic29.setProgress((int) (Math.sqrt(synth.harmonics[28] * 20000)));
					harmonic30.setProgress((int) (Math.sqrt(synth.harmonics[29] * 20000)));
					harmonic31.setProgress((int) (Math.sqrt(synth.harmonics[30] * 20000)));
					harmonic32.setProgress((int) (Math.sqrt(synth.harmonics[31] * 20000)));
				} else {
					harmonic17.setProgress(0);
					harmonic18.setProgress(0);
					harmonic19.setProgress(0);
					harmonic20.setProgress(0);
					harmonic21.setProgress(0);
					harmonic22.setProgress(0);
					harmonic23.setProgress(0);
					harmonic24.setProgress(0);
					harmonic25.setProgress(0);
					harmonic26.setProgress(0);
					harmonic27.setProgress(0);
					harmonic28.setProgress(0);
					harmonic29.setProgress(0);
					harmonic30.setProgress(0);
					harmonic31.setProgress(0);
					harmonic32.setProgress(0);
				}
				if (synth.filterType == WaveSynth.FILTER_LOWPASS) {
					filterTypeSpinner.setSelection(0);
				} else if (synth.filterType == WaveSynth.FILTER_BANDPASS) {
					filterTypeSpinner.setSelection(1);
				} else if (synth.filterType == WaveSynth.FILTER_FADE) {
					filterTypeSpinner.setSelection(3);
				} else if (synth.filterType == WaveSynth.FILTER_HIGHPASS) {
					filterTypeSpinner.setSelection(2);
				} else if (synth.filterType == WaveSynth.FILTER_COMB1) {
					filterTypeSpinner.setSelection(4);
				} else if (synth.filterType == WaveSynth.FILTER_COMB2) {
					filterTypeSpinner.setSelection(5);
				} else if (synth.filterType == WaveSynth.FILTER_COMB3) {
					filterTypeSpinner.setSelection(6);
				} else if (synth.filterType == WaveSynth.FILTER_COMB4) {
					filterTypeSpinner.setSelection(7);
				} else if (synth.filterType == WaveSynth.FILTER_FORMANT1) {
					filterTypeSpinner.setSelection(8);
				} else if (synth.filterType == WaveSynth.FILTER_FORMANT2) {
					filterTypeSpinner.setSelection(9);
				} else if (synth.filterType == WaveSynth.FILTER_FORMANT3) {
					filterTypeSpinner.setSelection(10);
				} else if (synth.filterType == WaveSynth.FILTER_FORMANT4) {
					filterTypeSpinner.setSelection(11);
				}
				filterResonance.setProgress((int) (synth.filterResonance * 100));
				filterLow.setProgress((int) (synth.filterLow * 100));
				filterHigh.setProgress((int) (synth.filterHigh * 100));
				filterEnvAttack.setProgress((int) (Math.sqrt((synth.filterEnvAttack) * 50)));
				filterEnvDecay.setProgress((int) (Math.sqrt((synth.filterEnvDecay) * 50)));
				filterEnvSustain.setProgress((int) (synth.filterEnvSustain * 100));
				filterEnvRelease.setProgress((int) (Math.sqrt((synth.filterEnvRelease) * 50)));
				ampVolume.setProgress((int) (synth.ampVolume * 100));
				ampAttack.setProgress((int) (Math.sqrt((synth.ampAttack) * 50)));
				ampDecay.setProgress((int) (Math.sqrt((synth.ampDecay) * 50)));
				ampSustain.setProgress((int) (synth.ampSustain * 100));
				ampRelease.setProgress((int) (Math.sqrt((synth.ampRelease) * 50)));
				ampOverdrive.setProgress(synth.ampOverdrive);
				ampOverdrivePerVoiceCheckBox.setChecked(synth.ampOverdrivePerVoice);
				vibratoDestinationSpinner.setSelection(synth.vibratoDestination);
				vibratoTypeSpinner.setSelection(synth.vibratoType);
				vibratoAmount.setProgress((int) (Math.sqrt(synth.vibratoAmount * 25000)));
				vibratoRate.setProgress((int) (Math.pow((synth.vibratoRate - 1) * 1000, 1.0 / 3))); // synth.vibratoRate
																									// =
																									// progress
																									// *
																									// progress
																									// *
																									// progress
																									// /
																									// 1000.0f
																									// +
																									// 1;
				vibratoSyncCheckBox.setChecked(synth.vibratoSync);
				vibratoAttack.setProgress((int) (Math.sqrt((synth.vibratoAttack) * 50)));
				vibratoDecay.setProgress((int) (Math.sqrt((synth.vibratoDecay) * 50)));
				vibrato2DestinationSpinner.setSelection(synth.vibrato2Destination);
				vibrato2TypeSpinner.setSelection(synth.vibrato2Type);
				vibrato2Amount.setProgress((int) (Math.sqrt(synth.vibrato2Amount * 25000)));
				vibrato2Rate.setProgress((int) (Math.pow((synth.vibrato2Rate - 1) * 1000, 1.0 / 3))); // synth.vibrato2Rate
																										// =
																										// progress
																										// *
																										// progress
																										// *
																										// progress
																										// /
																										// 1000.0f
																										// +
																										// 1;
				vibrato2SyncCheckBox.setChecked(synth.vibrato2Sync);
				vibrato2Attack.setProgress((int) (Math.sqrt((synth.vibrato2Attack) * 50)));
				vibrato2Decay.setProgress((int) (Math.sqrt((synth.vibrato2Decay) * 50)));
				echoAmount.setProgress((int) (synth.echoAmount * 100));
				echoDelay.setProgress((int) (Math.sqrt((synth.echoDelay * 10201))));
				echoFeedback.setProgress((int) (synth.echoFeedback * 101));
				flangeAmount.setProgress((int) (synth.flangeAmount * 100));
				flangeRate.setProgress((int) (synth.flangeRate * 100));
				sequence1.setProgress((synth.sequence[0]));
				sequence2.setProgress((synth.sequence[1]));
				sequence3.setProgress((synth.sequence[2]));
				sequence4.setProgress((synth.sequence[3]));
				sequence5.setProgress((synth.sequence[4]));
				sequence6.setProgress((synth.sequence[5]));
				sequence7.setProgress((synth.sequence[6]));
				sequence8.setProgress((synth.sequence[7]));
				if (synth.sequence.length > 8) {
					sequence1.setProgress((synth.sequence[0]));
					sequence2.setProgress((synth.sequence[1]));
					sequence3.setProgress((synth.sequence[2]));
					sequence4.setProgress((synth.sequence[3]));
					sequence5.setProgress((synth.sequence[4]));
					sequence6.setProgress((synth.sequence[5]));
					sequence7.setProgress((synth.sequence[6]));
					sequence8.setProgress((synth.sequence[7]));
					sequence9.setProgress((synth.sequence[8]));
					sequence10.setProgress((synth.sequence[9]));
					sequence11.setProgress((synth.sequence[10]));
					sequence12.setProgress((synth.sequence[11]));
					sequence13.setProgress((synth.sequence[12]));
					sequence14.setProgress((synth.sequence[13]));
					sequence15.setProgress((synth.sequence[14]));
					sequence16.setProgress((synth.sequence[15]));
				} else {
					sequence9.setProgress(0);
					sequence10.setProgress(0);
					sequence11.setProgress(0);
					sequence12.setProgress(0);
					sequence13.setProgress(0);
					sequence14.setProgress(0);
					sequence15.setProgress(0);
					sequence16.setProgress(0);
				}
				// sequenceRate.setProgress((int) synth.sequenceRate);
				sequenceRateTextView.setText(String.valueOf((int) synth.sequenceRate));
				sequenceLoopCheckBox.setChecked(synth.sequenceLoop);

				applyingASound = false;
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (getIntent().getData() != null) {
			System.out.println("Invocation params: " + getIntent().getData());
			loadSound(getIntent().getData().toString());
		}
		wakelock.acquire();
		soundLoadTime = System.currentTimeMillis();
		synth.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (updateThread != null) {
			stopRecording();
		}
		wakelock.release();
	}

	@Override
	protected void onResume() {
		super.onResume();
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean isScreenOn = pm.isScreenOn();
		if (isScreenOn) {
			// synth.start();
		}
		soundLoadTime = System.currentTimeMillis();
	}

	@Override
	protected void onPause() {
		long soundPlayTime = System.currentTimeMillis() - soundLoadTime;
		System.out.println("recordMetric: " + clientModel.getInstrumentName() + ": " + (soundPlayTime / 60000.0));
		// NewRelic.recordMetric(clientModel.getWorldName(), "SoundPlayTime",
		// soundPlayTime / 60000.0);
		super.onPause();
		// synth.stop();
	}

	int[] lastNote = new int[20]; // for finger
	int lastNote1;
	int lastNote2;
	int lastNote3;
	int lastNote4;
	int[] lastVoice = new int[20];

	private static final int PRESS = 1;
	private static final int RELEASE = 2;
	private static final int SLIDE = 0;

	private float lastTouchDownX;
	private float lastTouchDownY;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int index = event.getActionIndex();
		int pointerCount = event.getPointerCount();

		float x = event.getX(index);
		float y = event.getY(index);
		if (keyboardLocation == null) {
			keyboardLocation = new int[2];
			keyboard.getLocationOnScreen(keyboardLocation);
		}
		x += keyboardLocation[0];
		y += keyboardLocation[1];
		if (event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
			if (v == sequenceRateUpButton) {
				synth.sequenceRate = Math.min(1000, synth.sequenceRate + 1);
				synth.updateParams();
				updateControls();
				startAutoRepeat(v);
			} else if (v == sequenceRateDownButton) {
				synth.sequenceRate = Math.max(0, synth.sequenceRate - 1);
				synth.updateParams();
				updateControls();
				startAutoRepeat(v);
			} else {
				doKey(event.getPointerId(index) + 1, x, y, PRESS, pointerCount);
				lastTouchDownX = x;
				lastTouchDownY = y;
			}
		} else if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
			if (v == sequenceRateUpButton) {
				stopAutoRepeat();
			} else if (v == sequenceRateDownButton) {
				stopAutoRepeat();
			} else {
				doKey(event.getPointerId(index) + 1, x, y, RELEASE, pointerCount);
			}
		} else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
			if (v == sequenceRateUpButton) {
			} else if (v == sequenceRateDownButton) {
			} else {
				for (int i = 0; i < pointerCount; i++) {
					int historySize = event.getHistorySize();
					for (int h = 0; h < historySize; h++) {
						doKey(event.getPointerId(i) + 1, event.getHistoricalX(i, h) + keyboardLocation[0], Math.max(0, event.getHistoricalY(i, h) + keyboardLocation[1]), SLIDE, pointerCount);
					}
					doKey(event.getPointerId(i) + 1, event.getX(i) + keyboardLocation[0], Math.max(0, event.getY(i) + keyboardLocation[1]), SLIDE, pointerCount);
				}
			}
		}
		return true;
	}

	class AutoRepeatThread extends Thread {
		View v;
		boolean stoppit;
		int sleepTime = 200;

		public AutoRepeatThread(View v) {
			super("AutoRepeatThread");
			this.v = v;
		}

		@Override
		public void run() {
			try {
				while (!stoppit) {
					Thread.sleep(sleepTime);
					sleepTime = Math.max(10, (int) (sleepTime * 0.95f));
					if (!stoppit) {
						if (v == sequenceRateUpButton) {
							synth.sequenceRate = Math.min(1000, synth.sequenceRate + 1);
						} else if (v == sequenceRateDownButton) {
							synth.sequenceRate = Math.max(0, synth.sequenceRate - 1);
						}
						synth.updateParams();
						MainActivity.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								updateControls();
							}
						});
					}
				}
			} catch (InterruptedException e) {
			}
		}

		public void safeStop() {
			stoppit = true;
		}
	}

	private AutoRepeatThread autoRepeat;

	private void startAutoRepeat(View v) {
		if (autoRepeat != null) {
			autoRepeat.safeStop();
			autoRepeat = null;
		}
		autoRepeat = new AutoRepeatThread(v);
		autoRepeat.start();
	}

	private void stopAutoRepeat() {
		if (autoRepeat != null) {
			autoRepeat.safeStop();
			autoRepeat = null;
		}
	}

	private float initialX; // for pitch bend
	private float initialY; // for expression

	private final boolean[] keyHeld = new boolean[32];

	/** Show which keys are held */
	@SuppressLint("NewApi")
	private void showHolds() {
		for (int i = 0; i < 32; i++) {
			if (Build.VERSION.SDK_INT >= 11) {
				getKeyForNote(i).setActivated(keyHeld[i]);
			} else {
				getKeyForNote(i).setPressed(keyHeld[i]);
			}
		}
	}

	/** Turn off all keys */
	@SuppressLint("NewApi")
	private void clearHolds() {
		for (int i = 0; i < 32; i++) {
			keyHeld[i] = false;
		}
		showHolds();
	}

	@SuppressLint("NewApi")
	private void doKey(int finger, float x, float y, int type, int fingers) {

		if (!isPointInsideView(x, y, keyboard)) {
			int[] location = new int[2];
			keyboard.getLocationInWindow(location);
			y = location[1] + 4;
		}

		// test black keys first, then white
		int note = -1;
		while (note == -1) {
			if (isPointInsideView(x, y, key2)) {
				note = 1;
			} else if (isPointInsideView(x, y, key4)) {
				note = 3;
			} else if (isPointInsideView(x, y, key7)) {
				note = 6;
			} else if (isPointInsideView(x, y, key9)) {
				note = 8;
			} else if (isPointInsideView(x, y, key11)) {
				note = 10;
			} else if (isPointInsideView(x, y, key14)) {
				note = 13;
			} else if (isPointInsideView(x, y, key16)) {
				note = 15;
			} else if (isPointInsideView(x, y, key19)) {
				note = 18;
			} else if (isPointInsideView(x, y, key21)) {
				note = 20;
			} else if (isPointInsideView(x, y, key23)) {
				note = 22;
			} else if (x <= 0 || isPointInsideView(x, y, key1)) {
				note = 0;
			} else if (isPointInsideView(x, y, key3)) {
				note = 2;
			} else if (isPointInsideView(x, y, key5)) {
				note = 4;
			} else if (isPointInsideView(x, y, key6)) {
				note = 5;
			} else if (isPointInsideView(x, y, key8)) {
				note = 7;
			} else if (isPointInsideView(x, y, key10)) {
				note = 9;
			} else if (isPointInsideView(x, y, key12)) {
				note = 11;
			} else if (isPointInsideView(x, y, key13)) {
				note = 12;
			} else if (isPointInsideView(x, y, key15)) {
				note = 14;
			} else if (isPointInsideView(x, y, key17)) {
				note = 16;
			} else if (isPointInsideView(x, y, key18)) {
				note = 17;
			} else if (isPointInsideView(x, y, key20)) {
				note = 19;
			} else if (isPointInsideView(x, y, key21)) {
				note = 20;
			} else if (isPointInsideView(x, y, key22)) {
				note = 21;
			} else if (isPointInsideView(x, y, key24)) {
				note = 23;
			} else if (isPointInsideView(x, y, key25)) {
				note = 24;
			} else if (isPointInsideView(x, y, key26)) {
				note = 25;
			} else if (isPointInsideView(x, y, key27)) {
				note = 26;
			} else if (isPointInsideView(x, y, key28)) {
				note = 27;
			} else if (isPointInsideView(x, y, key29)) {
				note = 28;
			} else if (isPointInsideView(x, y, key30)) {
				note = 29;
			} else if (isPointInsideView(x, y, key31)) {
				note = 30;
			} else if (isPointInsideView(x, y, key32)) {
				note = 31;
			} else {
				// fudge x a bit and try again
				x = x - 4;
			}
		}

		int[] coords = new int[2];
		keyboard.getLocationOnScreen(coords);
		float velocity = Math.min(1.0f, 4.0f - 4.0f * (y - coords[1]) / keyboard.getHeight());

		int voice = 1;

		if (synth.mode == WaveSynth.MODE_MONOPHONIC) {
			if (type == PRESS) {
				keyPress(1, note, velocity);
				initialX = x;
				initialY = y;
			} else if (type == RELEASE) {
				if (fingers == 1) {
					keyRelease(1, lastNote[finger]);
				} else {
					getKeyForNote(note).setPressed(false);
				}
			} else if (type == SLIDE) {
				if (note != lastNote[finger]) {
					synth.keyRelease(1);
					// getKeyForNote(lastNote[finger]).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
					// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0,
					// 0));
					getKeyForNote(lastNote[finger]).setPressed(false);
					synth.keyPress(1, note, velocity, true, false);
					// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
					// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0,
					// 0, 0));
					getKeyForNote(note).setPressed(true);
				} else {
					synth.expression(FastMath.max(0, initialY - y) / keyboard.getHeight());
				}
			}
		} else if (synth.mode == WaveSynth.MODE_CHORUS) {
			if (type == PRESS) {
				keyPress(1, note, velocity);
				initialX = x;
				initialY = y;
			} else if (type == RELEASE) {
				if (fingers == 1) {
					keyRelease(1, lastNote[finger]);
				} else {
					getKeyForNote(note).setPressed(false);
				}
			} else if (type == SLIDE) {
				if (note != lastNote[finger]) {
					synth.keyRelease(1);
					synth.keyRelease(2);
					synth.keyRelease(3);
					synth.keyRelease(4);
					getKeyForNote(lastNote[finger]).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
					synth.keyPress(1, note, velocity, true, false);
					getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
				} else {
					synth.expression(FastMath.max(0, initialY - y) / keyboard.getHeight());
				}
			}
		} else { // polyphonic
			// if (type == SLIDE && fingers == 1 && synth.portamentoAmount > 0)
			// { // use last voice so it sounds
			// consistent
			// when doing a single finger glide
			// voice = lastVoice[finger];
			// } else {
			voice = synth.getVoice(note);
			// }
			// if (type == PRESS) {
			// System.out.println("Down: Finger " + finger + " note " + note + "
			// voice " + voice);
			// } else if (type == RELEASE) {
			// System.out.println("Up: Finger " + finger + " note " + note + "
			// voice " + voice);
			// }
			if (type == PRESS) {
				keyPress(voice, note, velocity);
			} else if (type == RELEASE) {
				keyRelease(voice, note);
			} else if (type == SLIDE) {
				if (!holdButton.isChecked()) {
					if (lastVoice[finger] != voice) {
						keyRelease(lastVoice[finger], lastNote[finger]);
						getKeyForNote(lastNote[finger]).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
						synth.keyPress(voice, note, velocity, true, false);
						getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
					}
				}
			}
			// if (voice == 1) {
			// if (type == PRESS) {
			// keyPress(voice, note, velocity);
			// } else if (type == RELEASE) {
			// keyRelease(voice, note);
			// } else if (type == SLIDE) {
			// if (!holdButton.isChecked()) {
			// if (lastVoice[finger] != voice) {
			// keyRelease(lastVoice[finger], lastNote[finger]);
			// getKeyForNote(lastNote[finger]).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// }
			// if (note != lastNote1 && synth.keyDown1) {
			// synth.keyRelease(voice);
			// getKeyForNote(lastNote1).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// } else if (note == lastNote1 && !synth.keyDown1) {
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// }
			// }
			// }
			// lastNote1 = note;
			// } else if (voice == 2) {
			// if (type == PRESS) {
			// keyPress(voice, note, velocity);
			// } else if (type == RELEASE) {
			// keyRelease(voice, note);
			// } else if (type == SLIDE) {
			// if (!holdButton.isChecked()) {
			// if (lastVoice[finger] != voice) {
			// keyRelease(lastVoice[finger], lastNote[finger]);
			// }
			// if (note != lastNote2 && synth.keyDown2) {
			// synth.keyRelease(voice);
			// getKeyForNote(lastNote2).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// } else if (note == lastNote2 && !synth.keyDown2) {
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// }
			// }
			// }
			// lastNote2 = note;
			// } else if (voice == 3) {
			// if (type == PRESS) {
			// keyPress(voice, note, velocity);
			// } else if (type == RELEASE) {
			// keyRelease(voice, note);
			// } else if (type == SLIDE) {
			// if (!holdButton.isChecked()) {
			// if (lastVoice[finger] != voice) {
			// keyRelease(lastVoice[finger], lastNote[finger]);
			// }
			// if (note != lastNote3 && synth.keyDown3) {
			// synth.keyRelease(voice);
			// getKeyForNote(lastNote3).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// } else if (note == lastNote3 && !synth.keyDown3) {
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// }
			// }
			// }
			// lastNote3 = note;
			// } else { // voice 4
			// if (type == PRESS) {
			// keyPress(voice, note, velocity);
			// } else if (type == RELEASE) {
			// keyRelease(voice, note);
			// } else if (type == SLIDE) {
			// if (!holdButton.isChecked()) {
			// if (lastVoice[finger] != voice) {
			// keyRelease(lastVoice[finger], lastNote[finger]);
			// }
			// if (note != lastNote4 && synth.keyDown4) {
			// synth.keyRelease(voice);
			// getKeyForNote(lastNote4).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// } else if (note == lastNote4 && !synth.keyDown4) {
			// synth.keyPress(voice, note, velocity, true, false);
			// getKeyForNote(note).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
			// SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
			// }
			// }
			// }
			// lastNote4 = note;
			// }
		}
		lastNote[finger] = note;
		lastVoice[finger] = voice;
		// if the action is up, remove the upped finger from the lastvoice array
		if (type == RELEASE) {
			for (int i = finger; i < lastVoice.length - 1; i++) {
				lastVoice[finger] = lastVoice[finger + 1];
				lastNote[finger] = lastNote[finger + 1];
			}
		}
	}

	@SuppressLint("NewApi")
	private void keyPress(int voice, int note, float velocity) {
		if (holdButton.isChecked()) {
			if (!keyHeld[note]) {
				synth.expression(0);
				synth.keyPress(voice, note, velocity, false, true);
				if (synth.mode != WaveSynth.MODE_POLYPHONIC) {
					clearHolds();
				}
				keyHeld[note] = true;
			} else {
				synth.keyRelease(voice);
				keyHeld[note] = false;
			}
			showHolds();
		} else {
			synth.expression(0);
			synth.keyPress(voice, note, velocity, false, true);
		}
		getKeyForNote(note).setPressed(true);
	}

	@SuppressLint("NewApi")
	private void keyRelease(int voice, int note) {
		if (!holdButton.isChecked()) {
			synth.keyRelease(voice);
		}
		getKeyForNote(note).setPressed(false);
	}

	private View getKeyForNote(int note) {
		if (note <= 0) {
			return key1;
		} else if (note == 1) {
			return key2;
		} else if (note == 2) {
			return key3;
		} else if (note == 3) {
			return key4;
		} else if (note == 4) {
			return key5;
		} else if (note == 5) {
			return key6;
		} else if (note == 6) {
			return key7;
		} else if (note == 7) {
			return key8;
		} else if (note == 8) {
			return key9;
		} else if (note == 9) {
			return key10;
		} else if (note == 10) {
			return key11;
		} else if (note == 11) {
			return key12;
		} else if (note == 12) {
			return key13;
		} else if (note == 13) {
			return key14;
		} else if (note == 14) {
			return key15;
		} else if (note == 15) {
			return key16;
		} else if (note == 16) {
			return key17;
		} else if (note == 17) {
			return key18;
		} else if (note == 18) {
			return key19;
		} else if (note == 19) {
			return key20;
		} else if (note == 20) {
			return key21;
		} else if (note == 21) {
			return key22;
		} else if (note == 22) {
			return key23;
		} else if (note == 23) {
			return key24;
		} else if (note == 24) {
			return key25;
		} else if (note == 25) {
			return key26;
		} else if (note == 26) {
			return key27;
		} else if (note == 27) {
			return key28;
		} else if (note == 28) {
			return key29;
		} else if (note == 29) {
			return key30;
		} else if (note == 30) {
			return key31;
		} else if (note == 31) {
			return key32;
		} else {
			return key32;
		}
	}

	private boolean isPointInsideView(float x, float y, View view) {
		if (view.getVisibility() == View.GONE) {
			return false;
		}
		int location[] = new int[2];
		view.getLocationOnScreen(location);
		int viewX = location[0];
		int viewY = location[1];

		// point is inside view bounds
		if ((x > viewX && x < (viewX + view.getWidth())) && (y > viewY && y < (viewY + view.getHeight()))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			if (buttonView == monoRadio) {
				synth.mode = 0;
			} else if (buttonView == polyRadio) {
				synth.mode = 1;
			} else if (buttonView == chorusRadio) {
				synth.mode = 2;
			} else if (buttonView == octave2Radio) {
				synth.octave = 1;
			} else if (buttonView == octave3Radio) {
				synth.octave = 2;
			} else if (buttonView == octave4Radio) {
				synth.octave = 3;
			} else if (buttonView == octave5Radio) {
				synth.octave = 4;
			} else if (buttonView == octave6Radio) {
				synth.octave = 5;
			}
			synth.updateParams();
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (applyingASound) {
			return;
		}
		if (seekBar == chorusWidth) {
			synth.chorusWidth = progress * progress / 10000.0f;
		} else if (seekBar == harmonic1) {
			updateWaves();
		} else if (seekBar == harmonic2) {
			updateWaves();
		} else if (seekBar == harmonic3) {
			updateWaves();
		} else if (seekBar == harmonic4) {
			updateWaves();
		} else if (seekBar == harmonic5) {
			updateWaves();
		} else if (seekBar == harmonic6) {
			updateWaves();
		} else if (seekBar == harmonic7) {
			updateWaves();
		} else if (seekBar == harmonic8) {
			updateWaves();
		} else if (seekBar == harmonic9) {
			updateWaves();
		} else if (seekBar == harmonic10) {
			updateWaves();
		} else if (seekBar == harmonic11) {
			updateWaves();
		} else if (seekBar == harmonic12) {
			updateWaves();
		} else if (seekBar == harmonic13) {
			updateWaves();
		} else if (seekBar == harmonic14) {
			updateWaves();
		} else if (seekBar == harmonic15) {
			updateWaves();
		} else if (seekBar == harmonic16) {
			updateWaves();
		} else if (seekBar == harmonic17) {
			updateWaves();
		} else if (seekBar == harmonic18) {
			updateWaves();
		} else if (seekBar == harmonic19) {
			updateWaves();
		} else if (seekBar == harmonic20) {
			updateWaves();
		} else if (seekBar == harmonic21) {
			updateWaves();
		} else if (seekBar == harmonic22) {
			updateWaves();
		} else if (seekBar == harmonic23) {
			updateWaves();
		} else if (seekBar == harmonic24) {
			updateWaves();
		} else if (seekBar == harmonic25) {
			updateWaves();
		} else if (seekBar == harmonic26) {
			updateWaves();
		} else if (seekBar == harmonic27) {
			updateWaves();
		} else if (seekBar == harmonic28) {
			updateWaves();
		} else if (seekBar == harmonic29) {
			updateWaves();
		} else if (seekBar == harmonic30) {
			updateWaves();
		} else if (seekBar == harmonic31) {
			updateWaves();
		} else if (seekBar == harmonic32) {
			updateWaves();
		} else if (seekBar == filterResonance) {
			synth.filterResonance = progress / 100.0f;
			updateWaves();
		} else if (seekBar == filterLow) {
			synth.filterLow = progress / 100.0f;
		} else if (seekBar == filterHigh) {
			synth.filterHigh = progress / 100.0f;
		} else if (seekBar == filterEnvAttack) {
			synth.filterEnvAttack = progress * progress / 50.0f;
		} else if (seekBar == filterEnvDecay) {
			synth.filterEnvDecay = progress * progress / 50.0f;
		} else if (seekBar == filterEnvSustain) {
			synth.filterEnvSustain = progress / 100.0f;
		} else if (seekBar == filterEnvRelease) {
			synth.filterEnvRelease = progress * progress / 50.0f;
		} else if (seekBar == ampVolume) {
			synth.ampVolume = progress / 100.0f;
		} else if (seekBar == ampAttack) {
			synth.ampAttack = progress * progress / 50.0f;
		} else if (seekBar == ampDecay) {
			if (progress >= 99) {
				progress = 1000; // make it near infinite
			}
			synth.ampDecay = progress * progress / 50.0f;
		} else if (seekBar == ampSustain) {
			synth.ampSustain = progress / 100.0f;
		} else if (seekBar == ampRelease) {
			synth.ampRelease = progress * progress / 50.0f;
		} else if (seekBar == ampOverdrive) {
			synth.ampOverdrive = progress;
		} else if (seekBar == vibratoAmount) {
			synth.vibratoAmount = progress * progress / 25000.0f;
		} else if (seekBar == vibratoRate) {
			synth.vibratoRate = progress * progress * progress / 1000.0f + 1;
		} else if (seekBar == vibratoAttack) {
			synth.vibratoAttack = progress * progress / 50.0f;
		} else if (seekBar == vibratoDecay) {
			if (progress >= 99) {
				progress = 1000; // make it near infinite
			}
			synth.vibratoDecay = progress * progress / 50.0f;
		} else if (seekBar == vibrato2Amount) {
			synth.vibrato2Amount = progress * progress / 25000.0f;
		} else if (seekBar == vibrato2Rate) {
			synth.vibrato2Rate = progress * progress * progress / 1000.0f + 1;
		} else if (seekBar == vibrato2Attack) {
			synth.vibrato2Attack = progress * progress / 50.0f;
		} else if (seekBar == vibrato2Decay) {
			if (progress >= 99) {
				progress = 1000; // make it near infinite
			}
			synth.vibrato2Decay = progress * progress / 50.0f;
		} else if (seekBar == echoAmount) {
			synth.echoAmount = progress / 100.0f;
		} else if (seekBar == echoDelay) {
			synth.echoDelay = progress * progress / 10201.0f;
		} else if (seekBar == echoFeedback) {
			synth.echoFeedback = progress / 101.0f; // avoid growing
		} else if (seekBar == flangeAmount) {
			synth.flangeAmount = progress / 100.0f;
		} else if (seekBar == flangeRate) {
			synth.flangeRate = progress / 100.0f;
		} else if (seekBar == portamentoAmount) {
			synth.portamentoAmount = progress / 101.0f;
		} else if (seekBar == sequence1) {
			updateSequence();
		} else if (seekBar == sequence2) {
			updateSequence();
		} else if (seekBar == sequence3) {
			updateSequence();
		} else if (seekBar == sequence4) {
			updateSequence();
		} else if (seekBar == sequence5) {
			updateSequence();
		} else if (seekBar == sequence6) {
			updateSequence();
		} else if (seekBar == sequence7) {
			updateSequence();
		} else if (seekBar == sequence8) {
			updateSequence();
		} else if (seekBar == sequence9) {
			updateSequence();
		} else if (seekBar == sequence10) {
			updateSequence();
		} else if (seekBar == sequence11) {
			updateSequence();
		} else if (seekBar == sequence12) {
			updateSequence();
		} else if (seekBar == sequence13) {
			updateSequence();
		} else if (seekBar == sequence14) {
			updateSequence();
		} else if (seekBar == sequence15) {
			updateSequence();
		} else if (seekBar == sequence16) {
			updateSequence();
			// } else if (seekBar == sequenceRate) {
			// synth.sequenceRate = progress;
		}
		synth.updateParams();
		dirty = true;
	}

	private void updateWaves() {
		float[] harmonics = new float[32];
		harmonics[0] = harmonic1.getProgress() * harmonic1.getProgress() / 20000.0f;
		harmonics[1] = harmonic2.getProgress() * harmonic2.getProgress() / 20000.0f;
		harmonics[2] = harmonic3.getProgress() * harmonic3.getProgress() / 20000.0f;
		harmonics[3] = harmonic4.getProgress() * harmonic4.getProgress() / 20000.0f;
		harmonics[4] = harmonic5.getProgress() * harmonic5.getProgress() / 20000.0f;
		harmonics[5] = harmonic6.getProgress() * harmonic6.getProgress() / 20000.0f;
		harmonics[6] = harmonic7.getProgress() * harmonic7.getProgress() / 20000.0f;
		harmonics[7] = harmonic8.getProgress() * harmonic8.getProgress() / 20000.0f;
		harmonics[8] = harmonic9.getProgress() * harmonic9.getProgress() / 20000.0f;
		harmonics[9] = harmonic10.getProgress() * harmonic10.getProgress() / 20000.0f;
		harmonics[10] = harmonic11.getProgress() * harmonic11.getProgress() / 20000.0f;
		harmonics[11] = harmonic12.getProgress() * harmonic12.getProgress() / 20000.0f;
		harmonics[12] = harmonic13.getProgress() * harmonic13.getProgress() / 20000.0f;
		harmonics[13] = harmonic14.getProgress() * harmonic14.getProgress() / 20000.0f;
		harmonics[14] = harmonic15.getProgress() * harmonic15.getProgress() / 20000.0f;
		harmonics[15] = harmonic16.getProgress() * harmonic16.getProgress() / 20000.0f;
		harmonics[16] = harmonic17.getProgress() * harmonic17.getProgress() / 20000.0f;
		harmonics[17] = harmonic18.getProgress() * harmonic18.getProgress() / 20000.0f;
		harmonics[18] = harmonic19.getProgress() * harmonic19.getProgress() / 20000.0f;
		harmonics[19] = harmonic20.getProgress() * harmonic20.getProgress() / 20000.0f;
		harmonics[20] = harmonic21.getProgress() * harmonic21.getProgress() / 20000.0f;
		harmonics[21] = harmonic22.getProgress() * harmonic22.getProgress() / 20000.0f;
		harmonics[22] = harmonic23.getProgress() * harmonic23.getProgress() / 20000.0f;
		harmonics[23] = harmonic24.getProgress() * harmonic24.getProgress() / 20000.0f;
		harmonics[24] = harmonic25.getProgress() * harmonic25.getProgress() / 20000.0f;
		harmonics[25] = harmonic26.getProgress() * harmonic26.getProgress() / 20000.0f;
		harmonics[26] = harmonic27.getProgress() * harmonic27.getProgress() / 20000.0f;
		harmonics[27] = harmonic28.getProgress() * harmonic28.getProgress() / 20000.0f;
		harmonics[28] = harmonic29.getProgress() * harmonic29.getProgress() / 20000.0f;
		harmonics[29] = harmonic30.getProgress() * harmonic30.getProgress() / 20000.0f;
		harmonics[30] = harmonic31.getProgress() * harmonic31.getProgress() / 20000.0f;
		harmonics[31] = harmonic32.getProgress() * harmonic32.getProgress() / 20000.0f;
		synth.harmonics = harmonics;
		synth.genWaves();
	}

	public void updateSequence() {
		int[] sequence = new int[16];
		sequence[0] = sequence1.getProgress();
		sequence[1] = sequence2.getProgress();
		sequence[2] = sequence3.getProgress();
		sequence[3] = sequence4.getProgress();
		sequence[4] = sequence5.getProgress();
		sequence[5] = sequence6.getProgress();
		sequence[6] = sequence7.getProgress();
		sequence[7] = sequence8.getProgress();
		sequence[8] = sequence9.getProgress();
		sequence[9] = sequence10.getProgress();
		sequence[10] = sequence11.getProgress();
		sequence[11] = sequence12.getProgress();
		sequence[12] = sequence13.getProgress();
		sequence[13] = sequence14.getProgress();
		sequence[14] = sequence15.getProgress();
		sequence[15] = sequence16.getProgress();
		synth.sequence = sequence;
		synth.updateSequence();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	// --- MIDI ----

	/*

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
	}

	public void onMidiInputDeviceDetached(MidiInputDevice midiInputDevice) {
		// TODO Auto-generated method stub

	}

	public void onMidiOutputDeviceDetached(MidiOutputDevice midiOutputDevice) {
		// TODO Auto-generated method stub

	}

	public void onMidiInputDeviceAttached(MidiInputDevice midiInputDevice) {
		// TODO Auto-generated method stub

	}

	public void onMidiOutputDeviceAttached(MidiOutputDevice midiOutputDevice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice sender, int cable, int byte1, int byte2, int byte3) {
	}

	@Override
	public void onMidiCableEvents(MidiInputDevice sender, int cable, int byte1, int byte2, int byte3) {
	}

	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice sender, int cable, byte[] bytes) {
	}

	@Override
	public void onMidiSystemExclusive(MidiInputDevice sender, int cable, byte[] systemExclusive) {
	}

	@Override
	public void onMidiNoteOn(MidiInputDevice sender, int cable, int channel, int midinote, int midivelocity) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
		int note = midinote - 60 + 12;
		float velocity = FastMath.min(1.0f, (midivelocity + 1) / 64.0f);
		if (synth.mode == EasySynth.MODE_MONOPHONIC) {
			synth.keyPress(1, note, velocity, false, true);
			lastNote1 = note;
		} else if (synth.mode == EasySynth.MODE_CHORUS) {
			synth.keyPress(1, note, velocity, false, true);
			lastNote1 = note;
			lastNote2 = note;
			lastNote3 = note;
			lastNote4 = note;
		} else if (synth.mode == EasySynth.MODE_POLYPHONIC) {
			int voice = synth.getVoice(note);
			synth.keyPress(voice, note, velocity, false, true);
			if (voice == 1) {
				lastNote1 = note;
			} else if (voice == 2) {
				lastNote2 = note;
			} else if (voice == 3) {
				lastNote3 = note;
			} else if (voice == 4) {
				lastNote4 = note;
			}
		}
	}

	@Override
	public void onMidiNoteOff(MidiInputDevice sender, int cable, int channel, int midinote, int midivelocity) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
		int note = midinote - 60 + 12;
		if (synth.mode == EasySynth.MODE_MONOPHONIC) {
			if (lastNote1 == note) {
				synth.keyRelease(1);
			}
		} else if (synth.mode == EasySynth.MODE_CHORUS) {
			if (lastNote1 == note) {
				synth.keyRelease(1);
				synth.keyRelease(2);
				synth.keyRelease(3);
				synth.keyRelease(4);
			}
		} else if (synth.mode == EasySynth.MODE_POLYPHONIC) {
			if (lastNote1 == note) {
				synth.keyRelease(1);
			} else if (lastNote2 == note) {
				synth.keyRelease(2);
			} else if (lastNote3 == note) {
				synth.keyRelease(3);
			} else if (lastNote4 == note) {
				synth.keyRelease(4);
			}
		}
	}

	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice sender, int cable, int channel, int note, int pressure) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
	}

	@Override
	public void onMidiControlChange(MidiInputDevice sender, int cable, int channel, int function, int value) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
		if (function == 0) { // ?
		} else if (function == 1) { // modulation amount
			// float currentVibratoAmount = progress * progress / 25000.0f;
			synth.expression(value / 128.0f);
		} else if (function == 3) { // chorus (pulse) width
			synth.chorusWidth = value / 128.0f;
			updateControls();
		} else if (function == 7) { // overall volume
			AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) ((value / 128.0f) * am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)), 0);
		} else if (function == 10) { // pan
			// doesn't exist
		} else if (function == 14) { // amp level
			synth.ampVolume = value / 128.0f;
			updateControls();
		} else if (function == 16) { // vibrato rate
			synth.vibratoRate = value;
			updateControls();
		} else if (function == 17) { // tremelo rate (actually lfo2)
			// doesn't exist
		} else if (function == 18) { // vibrato amount
			synth.vibratoAmount = value / 128.0f;
			updateControls();
		} else if (function == 22) { // tremelo amount (actually lfo2)
			// doesn't exist
		} else if (function == 28) { // filter sustain
			synth.filterEnvSustain = value / 128.0f;
			updateControls();
		} else if (function == 29) { // filter release
			synth.filterEnvRelease = value;
			updateControls();
		} else if (function == 31) { // amp sustain
			synth.ampSustain = value / 128.0f;
			updateControls();
		} else if (function == 64) { // damper pedal
			if (value > 0) {
				synth.ampRelease = synth.ampDecay;
			} else {
				synth.ampRelease = sound.ampRelease;
			}
		} else if (function == 70) {

		} else if (function == 71) { // filter resonance
			synth.filterResonance = value / 128.0f;
			synth.genWaves();
			updateControls();
		} else if (function == 72) { // amp release
			synth.ampRelease = value;
			updateControls();
		} else if (function == 73) { // amp attack
			synth.ampAttack = value;
			updateControls();
		} else if (function == 74) { // filter cutoff
			synth.filterLow = value / 128.0f;
			updateControls();
		} else if (function == 75) { // amp decay
			synth.ampDecay = value;
			updateControls();
		} else if (function == 76) { // vibrato rate
			synth.vibratoRate = value / 128.0f;
			updateControls();
		} else if (function == 77) { // vibrato amount
			synth.vibratoAmount = value / 128.0f;
			updateControls();
		} else if (function == 78) { // vibrato attack
			synth.vibratoAttack = value;
			updateControls();
		} else if (function == 81) { // filter depth
			synth.filterHigh = value / 128.0f;
			updateControls();
		} else if (function == 82) { // filter attack
			synth.filterEnvAttack = value;
			updateControls();
		} else if (function == 83) { // filter decay
			synth.filterEnvDecay = value;
			updateControls();
		} else if (function == 91) { // reverb amount
			synth.echoFeedback = value / 128.0f;
			updateControls();
		} else if (function == 93) { // chorus amount
			synth.echoAmount = value / 128.0f;
			updateControls();
		}

		// Special commands
		else if (function == 120) { // all sound off
			synth.allSoundOff();
		} else if (function == 121) { // reset all controllers
			// not implemented
		} else if (function == 122) { // local control
			// not implemented
		} else if (function == 123) { // all notes off
			synth.keyRelease(1);
			synth.keyRelease(2);
			synth.keyRelease(3);
			synth.keyRelease(4);
		} else if (function == 124) { // omni mode off
			// not supported
		} else if (function == 125) { // omni mode on
			// the default
		} else if (function == 126) { // mono mode on
			synth.keyRelease(1);
			synth.keyRelease(2);
			synth.keyRelease(3);
			synth.keyRelease(4);
			monoRadio.setChecked(true);
			polyRadio.setChecked(false);
			chorusRadio.setChecked(false);
		} else if (function == 127) { // poly mode on
			synth.keyRelease(1);
			synth.keyRelease(2);
			synth.keyRelease(3);
			synth.keyRelease(4);
			polyRadio.setChecked(true);
			chorusRadio.setChecked(false);
			monoRadio.setChecked(false);
		}
		synth.updateParams();
	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable, int channel, int program) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
	}

	@Override
	public void onMidiChannelAftertouch(MidiInputDevice sender, int cable, int channel, int pressure) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
		synth.expression(pressure / 128.0f);
	}

	@Override
	public void onMidiPitchWheel(MidiInputDevice sender, int cable, int channel, int amount) {
		if (myMidiChannel != 0 && channel != myMidiChannel) {
			return;
		}
		synth.pitchBend((amount - 8192) / 8192.0f / 4.0f);
	}

	@Override
	public void onMidiSingleByte(MidiInputDevice sender, int cable, int byte1) {
	}

	@Override
	public void onDeviceDetached(UsbDevice usbDevice) {
	}
*/

	/**
	 * Override the back button to prompt for quit.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && !event.isAltPressed()) {
			final MessageDialog dialog = new MessageDialog(this, null, "Are you sure you want to quit?", new String[] { "Yes", "No" }, null);
			dialog.show();
			dialog.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss(DialogInterface d) {
					int rc = dialog.getButtonPressed();
					if (rc == 0) {
						finish();
					}
				}
			});
			return true; // overriding the standard action handling
		}
		return false;
	}

}

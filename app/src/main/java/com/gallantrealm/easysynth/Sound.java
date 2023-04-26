package com.gallantrealm.easysynth;

import java.io.Serializable;

public class Sound implements Serializable {

	public static final int VELOCITY_NONE = 0;
	public static final int VELOCITY_VOLUME = 1;
	public static final int VELOCITY_FILTER = 2;
	public static final int VELOCITY_BOTH = 3;

	private static final long serialVersionUID = 1L;
	boolean zeroAdjustedEnvelopes;

	int mode; // 0=mono, 1=poly, 2=chorus
	int octave;
	int key;
	boolean chorusWidthSet;
	float chorusWidth;
	float portamentoAmount;
	int velocityType;
	int expressionType;
	boolean hold;

	float[] harmonics;

	int filterType;
	float filterResonance;
	float filterCutoff;
	float filterEnvAmount;
	float filterEnvAttack;
	float filterEnvDecay;
	float filterEnvSustain;
	float filterEnvRelease;

	float ampAttack;
	float ampDecay;
	float ampSustain;
	float ampRelease;
	int ampOverdrive;
	boolean ampOverdrivePerVoice;

	int vibratoDestination;
	int vibratoType;
	float vibratoAmount;
	float vibratoRate;
	boolean vibratoSync;
	boolean vibratoEnvelopeSet;
	float vibratoAttack;
	float vibratoDecay;

	int vibrato2Destination;
	int vibrato2Type;
	float vibrato2Amount;
	float vibrato2Rate;
	boolean vibrato2Sync;
	boolean vibrato2EnvelopeSet;
	float vibrato2Attack;
	float vibrato2Decay;

	float echoAmount;
	float echoDelay;
	float echoFeedback;
	float flangeAmount;
	float flangeRate;

	int[] sequence;
	float sequenceRate;
	boolean sequenceLoop;
	boolean bpmSequenceRate;

	float ampVolume;
}

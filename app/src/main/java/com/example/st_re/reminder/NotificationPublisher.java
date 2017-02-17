package com.example.st_re.reminder;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;


public class NotificationPublisher extends BroadcastReceiver implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    public void onReceive(Context context, Intent intent) {
        String notification = intent.getStringExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        Intent intent1 = new Intent(context, TTS.class);
        intent1.putExtra("hello", "hi");
        context.startService(intent1);
        //notificationManager.notify(id, notification);
        //tts = new TextToSpeech(context.getApplicationContext(),this);
        //speakOut(notification);
    }

    private void speakOut(String text) {
        String utteranceId=this.hashCode() + "";
        //Get the text typed
        //If no text is typed, tts will read out 'You haven't typed text'
        //else it reads out the text you typed
        if (text.length() == 0) {
            tts.speak("No text typed", TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        }

    }


    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            }
        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }
}

/*

public class SpeakingText extends AppCompatActivity implements TextToSpeech.OnInitListener {
    //create TextToSpeech native object
    private TextToSpeech tts;
    private Button btnSpeak; //btnSpeak -> layout
    private EditText txtText; // txtText -> layout

    public void onCreate(Bundle savedInstanceState) { //here, you initialize your activity
        super.onCreate(savedInstanceState);  //When we override a method, we have the option of completely replacing the method in our class, or of extending the existing parent class' method. By calling super.onCreate(savedInstanceState);, you tell the Dalvik VM to run your code in addition to the existing code in the onCreate() of the parent class. If you leave out this line, then only your code is run. The existing code is ignored completely.
        //Set application layout
        setContentView(R.layout.speaking_test); //call the layout resource.
        //Initialize the tts object
        tts = new TextToSpeech(this, this); // TextToSPeech Synthesizes speech from text for immediate playback or to create a sound file.
        //Refer 'Speak' button
        btnSpeak = (Button) findViewById(R.id.btnSpeak); // Looks for the child view with the given id (btnSPeak)
        //Refer 'Text' control
        txtText = (EditText) findViewById(R.id.txtText);
        //Handle onClick event for button 'Speak'
        btnSpeak.setOnClickListener(new View.OnClickListener() { //Register a callback to be invoked when this view is clicked (btnSPeak) //  View.OnclickListener = The callback that will run

            public void onClick(View arg0) {  //Called when a view has been clicked.
                //Method yet to be defined
                speakOut();
            }

        });
    }

    public void onInit(int status) { //Called to signal the completion of the TextToSpeech engine initialization.
        // TODO Auto-generated method stub
        //TTS is successfully initialized
        if (status == TextToSpeech.SUCCESS) {
            //Setting speech language
            int result = tts.setLanguage(Locale.US);
            //If your device doesn't support language you set above
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Cook simple toast message with message
                Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            }
            //Enable the button - It was disabled in main.xml (Go back and Check it)
            else {
                btnSpeak.setEnabled(true);
            }
            //TTS is not initialized properly
        } else {
            Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
            Log.e("TTS", "Initilization Failed");
        }
    }

    private void speakOut() {
        //Get the text typed
        String text = txtText.getText().toString();
        //If no text is typed, tts will read out 'You haven't typed text'
        //else it reads out the text you typed
        if (text.length() == 0) {
            tts.speak("No text typed", TextToSpeech.QUEUE_FLUSH, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
*/
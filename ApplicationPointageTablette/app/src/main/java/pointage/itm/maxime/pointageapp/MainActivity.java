package pointage.itm.maxime.pointageapp;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private NfcAdapter nfcAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat heure = new SimpleDateFormat("HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");

        Calendar c = Calendar.getInstance();
        //je sépare en 2 variables l'heure et la minute
        String heure1 = heure.format(c.getTime());
        String minute1 = minute.format(c.getTime());

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        layout = findViewById(R.id.BackLayout);

        if(nfcAdapter != null && nfcAdapter.isEnabled())
        {
            Toast.makeText(this,"NFC activé : "+heure1+ " : "+minute1 , Toast.LENGTH_LONG).show();
            layout.setBackgroundColor(getResources().getColor(R.color.vert));
            String id = "Test";
            String horaire = heure1+":"+minute1;
            User user = new User(id,horaire);
        }
        else {
            Toast.makeText(this, "NFC Désactivé :", Toast.LENGTH_LONG).show();
            layout.setBackgroundColor(getResources().getColor(R.color.rouge));
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LectureDonnees(intent);
    }

    private void LectureDonnees(Intent intent) {
        String action = intent.getAction();

        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action))
        {
            String uriString = intent.getDataString();

            if(uriString != null)
            {
                Toast.makeText(this, uriString, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SimpleDateFormat heure = new SimpleDateFormat("HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");

        Calendar c = Calendar.getInstance();
        //je sépare en 2 variables l'heure et la minute
        String heure1 = heure.format(c.getTime());
        String minute1 = minute.format(c.getTime());

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        layout = findViewById(R.id.BackLayout);

        if(nfcAdapter != null && nfcAdapter.isEnabled())
        {
            Toast.makeText(this,"NFC activé : "+heure1+ " : "+minute1 , Toast.LENGTH_LONG).show();
            layout.setBackgroundColor(getResources().getColor(R.color.vert));
            String id = "Test";
            String horaire = heure1+":"+minute1;
            User user = new User(id,horaire);

        }
        else {
            Toast.makeText(this, "NFC Désactivé :", Toast.LENGTH_LONG).show();
            layout.setBackgroundColor(getResources().getColor(R.color.rouge));
        }
        enableForegroundDispatchSystem();
    }
    @Override
    protected void onPause(){
        super.onPause();
        disableForegroundDispatchSystem();
    }
    private void enableForegroundDispatchSystem() {

        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }
}

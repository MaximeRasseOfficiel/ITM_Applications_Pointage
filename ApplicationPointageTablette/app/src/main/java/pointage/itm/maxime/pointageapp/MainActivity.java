package pointage.itm.maxime.pointageapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;

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
    protected void onResume() {
        super.onResume();
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
}

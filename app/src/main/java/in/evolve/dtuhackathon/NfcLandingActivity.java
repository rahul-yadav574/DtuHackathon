package in.evolve.dtuhackathon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class NfcLandingActivity extends AppCompatActivity {

    private Socket socket;
    private String TAG = "NfcLandingActivity";
    private int RC_NFC = 574;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_landing);

        sharedPrefUtil = new SharedPrefUtil(NfcLandingActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG,"It May be a tag;");

        if (socket==null){
            initialiseHospitalSocket();
        }

        if (socket!=null && !socket.connected()){
            socket.connect();
        }
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())  && Constants.MODE==0){
            Constants.MODE =1;
            processNfcTag(getIntent());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_NFC){
            showNfcStatus();
        }
    }

    void processNfcTag(Intent intent){
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);


        for (String techUsed : tag.getTechList()){
            Log.d(TAG,""+techUsed);
        }

        String data = NfcTagUtils.readTag(tag);

        Log.d(TAG,data+"and the daata is");

        if(socket==null) {
            initialiseHospitalSocket();
        }

        if (socket!=null && !socket.connected()){
            socket.connect();
        }


    }

    void initialiseHospitalSocket(){

        try{
            socket = IO.socket(Constants.SOCKETS_SERVER);
            socket.connect();
            Log.d(TAG,"Connecting to Menu Socket Server");
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        if (!socket.connected()){
            socket.connect();
        }

    }

    void showNfcStatus(){

        Boolean nfcState = UtilFunction.checkNFCStatus(NfcLandingActivity.this);

        if (nfcState == null){
            // nfcInfo.setText("NFC NOT AVAILABLE");
            Intent intent=new Intent(NfcLandingActivity.this,MainActivity.class);
            startActivity(intent);
        }

        else if (nfcState){
            //nfcInfo.setText("NFC AVAILABLE ... USE ANY ACTIVE OR PASSIVE DEVICE TO USE WITH IT...");
        }
        else {
            showSwitchOnNfcDialog();
        }
    }

    void showSwitchOnNfcDialog(){

        new AlertDialog.Builder(NfcLandingActivity.this)
                .setMessage("NFC Is Not Enabled. In Order To Enjoy Our Services Enable Your Wifi..")
                .setCancelable(false)
                .setPositiveButton("ENABLE NFC", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        } else {
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        }
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NfcLandingActivity.this.finish();
                    }
                })
                .create().show();
    }

}

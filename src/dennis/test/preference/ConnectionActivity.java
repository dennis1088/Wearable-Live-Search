package dennis.test.preference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import rit.cs.reu.wearablelive.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectionActivity extends Activity {
	
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.connection);
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		String address = sharedPreferences.getString("ip_address", "129.21.57.128");
		String port = sharedPreferences.getString("port_number", "4444");
		
		TextView addressTextView = (TextView) findViewById(R.id.data_ip_address);
		TextView portTextView = (TextView) findViewById(R.id.data_port_number);
		mWebView = (WebView) findViewById(R.id.web_results);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		addressTextView.setText(address);
		portTextView.setText(port);
		
		Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket(address, 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
            	Toast.makeText(this, fromServer, Toast.LENGTH_LONG).show();
            	mWebView.loadUrl("http://www.google.com/search?q="+fromServer);
            }
            
            out.close();
            in.close();
            kkSocket.close();
            
        } catch (UnknownHostException e) {
            Toast.makeText(this, "Host is not known", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
        	Toast.makeText(this, "Port is not a number", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
        	Toast.makeText(this, "Couldnt get io  connection for server", Toast.LENGTH_LONG).show();
        	Log.e("CONNECTION ERROR", e.getMessage());
        }
		
	}
	

}

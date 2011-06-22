package dennis.test.preference;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import rit.cs.reu.wearablelive.R;

public class PreferenceTestActivity extends PreferenceActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preference);
        setContentView(R.layout.main);
        
        Button connectButton = (Button) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent connectIntent = new Intent(PreferenceTestActivity.this, ConnectionActivity.class);
				startActivity(connectIntent);
			}
		});
    }
}
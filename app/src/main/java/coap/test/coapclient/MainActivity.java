package coap.test.coapclient;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import java.net.URI;
import java.net.URISyntaxException;




public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CoapTask().execute("coap://vs0.inf.ethz.ch:5683");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class CoapTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.d(MainActivity.this.getClass().getSimpleName(), "Coap Around?.");
            String url=urls[0];
            URI uri = null; // URI parameter of the request
            // input URI from command line arguments
            try {
                 uri = new URI(url);

            } catch (URISyntaxException e) {
                Log.d(MainActivity.this.getClass().getSimpleName(), "Invalid URI: " + e.getMessage());

            }
            CoapClient client = new CoapClient(uri);
            CoapResponse response = client.get();
            if (response!=null) {
                Log.d(MainActivity.this.getClass().getSimpleName(), "YES!\n"+
                        response.getCode()+"\n <- 000000 --> \n"+response.getOptions()+ "\n <--000000-> \n"+
                        response.getResponseText()+"<--0000->");

            } else {
                Log.d(MainActivity.this.getClass().getSimpleName(), "No response received.");
            }

            return response.getResponseText();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(MainActivity.this.getClass().getSimpleName(), result);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

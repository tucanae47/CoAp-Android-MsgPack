package coap.test.coapclient;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;
import org.msgpack.packer.Packer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;




public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CoapTask().execute("coap://localhost:5683/r/hello");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class CoapTask extends AsyncTask<String, Void, String> {

        @Message
        class A {
            @Index(0)
            public int h;
            @Index(1)
            public String g;
            @Index(2)
            public byte f;
            @Index(3)
            public int e;
            @Index(4)
            public String d;
            @Index(5)
            public float c;
            @Index(6)
            public byte b;
            @Index(7)
            public long a;
        }
        @Override
        protected String doInBackground(String... urls) {
            Log.d(MainActivity.this.getClass().getSimpleName(), "Coap Around?.");
            String url=urls[0];
            URI uri = null; // URI parameter of the request
            // input URI from command line arguments
            try {
                 uri = new URI(url);



            CoapClient client = new CoapClient(uri);
            //client.
            A obj = new A();
            obj.a=3234l;
            obj.b=0x40;
            obj.c=3.1426f;
            obj.d="pi";
            obj.e=20;
            obj.f=0x47;
            obj.g="pi on the network";
            obj.h=12346789;
            MessagePack msgpack = new MessagePack();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Packer packer = msgpack.createPacker(out);
            packer.write(obj);

            byte[] bytes = out.toByteArray();
            CoapResponse response = client.put(bytes, MediaTypeRegistry.APPLICATION_OCTET_STREAM);
            if (response != null) {
                Log.e(MainActivity.this.getClass().getSimpleName(), "response received" + "  _> " + response.getResponseText() + " /" + Utils.prettyPrint(response));
            } else {
                Log.e(MainActivity.this.getClass().getSimpleName(), "No response received.");
            }
            } catch (URISyntaxException e) {
                Log.d(MainActivity.this.getClass().getSimpleName(), "Invalid URI: " + e.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
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

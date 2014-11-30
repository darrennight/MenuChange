package mc.wxp.com.menuchange;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import mc.wxp.com.menuchange.OnStatChangeListener;

public class MyActivity extends Activity {

    private AddDes_Style01 mAddDes;
    private AddDes_Style02 mAddDes02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mAddDes = (AddDes_Style01) findViewById(R.id.id_score_change);
        mAddDes02 = (AddDes_Style02) findViewById(R.id.id_score_change2);
        mAddDes.setOnStateChange(mStateChangeListener);
        mAddDes02.setOnStateChange(mStateChangeListener);
    }


    private OnStatChangeListener mStateChangeListener= new OnStatChangeListener() {
        @Override
        public void changeState(boolean state) {
            if (state){
                Toast.makeText(MyActivity.this, "加一分", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MyActivity.this, "减一分", Toast.LENGTH_SHORT).show();
            }


        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

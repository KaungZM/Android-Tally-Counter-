package mm.com.fairway.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    int mValue;
    Vibrator vibrator;
    static long vibeLength;
    final String STORAGE = "MyPrefsFiles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(savedInstanceState != null) {
//            mValue = savedInstanceState.getInt("counter");
//            vibeLength = (long) savedInstanceState.getInt("vibration");
//        }

        setContentView(R.layout.activity_main);
        retrieveData();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(mValue + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieveData();
        mTextView.setText(mValue + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.vibration:
                CustomDialog customDialog = new CustomDialog(this);
                customDialog.show();
                return true;
            case R.id.reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void reset() {
        mValue = 0;
        saveData();
        onStart();
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("counter", mValue);
//        outState.putSerializable("vibration", vibeLength);
//    }

    public void trigger(View view) {
        vibrator.vibrate(vibeLength);
        mValue++;
        mTextView.setText(mValue + "");
        saveData();
    }

    public void saveData() {
        SharedPreferences storage = getSharedPreferences(STORAGE, 0);
        SharedPreferences.Editor editor = storage.edit();
        editor.putInt("count", mValue);
        editor.putInt("vibeLength", (int)vibeLength);
        editor.commit();
    }

    public void retrieveData() {
        SharedPreferences storage = getSharedPreferences(STORAGE, 0);
        mValue = storage.getInt("count", 0);
        vibeLength = storage.getInt("vibeLength", 50);
    }

}
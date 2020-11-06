package mm.com.fairway.counter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class CustomDialog extends AlertDialog {
    Context context;
    SeekBar seekBar;
    Button button;
    TextView percentage;

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibration_dialog);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        button = (Button) findViewById(R.id.OK);
        percentage = (TextView) findViewById(R.id.seekBar_label);
        percentage.setText(MainActivity.vibeLength + "%");
    }

    @Override
    protected void onStart() {
        super.onStart();
        seekBarActions();
    }

    public void seekBarActions() {
        seekBar.setProgress((int) MainActivity.vibeLength);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.vibeLength = i;
                percentage.setText(i + "%");
                Log.d("test",MainActivity.vibeLength+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

package tn.fsb.tp3calcul_imc_activity.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import tn.fsb.tp3calcul_imc_activity.R;
import tn.fsb.tp3calcul_imc_activity.vue.MainActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView textView = (TextView) findViewById(R.id.resultTextView);
        textView.setText(text);
    }
}
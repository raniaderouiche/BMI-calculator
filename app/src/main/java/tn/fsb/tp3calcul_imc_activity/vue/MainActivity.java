package tn.fsb.tp3calcul_imc_activity.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import tn.fsb.tp3calcul_imc_activity.R;
import tn.fsb.tp3calcul_imc_activity.dao.DatabaseHandler;
import tn.fsb.tp3calcul_imc_activity.model.Profile;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "tn.fsb.tp3calcul_imc_activity.EXTRA_TEXT";

    private SeekBar seekBar1,seekBar2;
    private TextView seekbarHeight,seekbarWeight;
    private int height,weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        seekbarHeight = (TextView) findViewById(R.id.displayHeight) ;

        seekbarWeight = (TextView) findViewById(R.id.displayWeight) ;

        //get name
        EditText editText = (EditText) findViewById(R.id.nameField) ;
        String name = editText.getText().toString();

        //get height
        seekBar1 = (SeekBar) findViewById(R.id.seekBar2) ;
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarHeight.setText(String.valueOf(progress));
                height = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //get weight
        seekBar2 = (SeekBar) findViewById(R.id.seekBar3) ;
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarWeight.setText(String.valueOf(progress));
                weight = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Ok button
        Button okButton = (Button) findViewById(R.id.button);
        okButton.setOnClickListener(v -> {
            double result = BMICalculate(weight,height);
            String interpretation = interpretBMI(result);
            Profile profile = new Profile();
            profile.setUsername(name);
            profile.setHeight(height);
            profile.setWeight(weight);
            profile.setMsgBMI(interpretation);
            db.addContact(profile);
            System.out.println("profile added !" + profile);
            Toast.makeText(getApplicationContext(),"Profile Saved !",Toast.LENGTH_SHORT).show();
            openActivity2(interpretation);
        });

        //Reset button
        Button resetButton = (Button) findViewById(R.id.button2);
        resetButton.setOnClickListener(v -> {
            //EditText editText = (EditText) findViewById(R.id.nameField) ;
            editText.setText("");
            seekBar1.setProgress(0);
            seekBar2.setProgress(0);
        });
    }

    //go to next activity
    private void openActivity2(String interpretation) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(EXTRA_TEXT,interpretation);
        startActivity(intent);
    }

    //get BMI
    public float BMICalculate(int weight,int height){
        return (100*100*weight)/(height*height);
    }

    //interpretation
    public String interpretBMI(double result){
        //get name
        EditText editText = (EditText) findViewById(R.id.nameField) ;
        String name = editText.getText().toString();

        if(result < 16){
            return "Hello " + name + " your BMI is " + result + " : Severely Underweight";
        }else if(result > 16 && result < 18){
            return "Hello " + name + "  your BMI is "+ result + " : Underweight";
        }else if(result > 18 &&  result < 25){
            return "Hello " + name + " your BMI is "+ result + " : Normal weight";
        }else if(result > 25 && result < 30){
            return "Hello " + name + " your BMI is "+ result + " : Overweight";
        }else if(result > 30 && result < 40){
            return "Hello " + name + " your BMI is "+ result + " : Obese";
        }else if (result > 40){
            return "Hello " + name + " your BMI is "+ result + " : Morbidly Obese";
        }else{
            return "error";
        }
    }
}
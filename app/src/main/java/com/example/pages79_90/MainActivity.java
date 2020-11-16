package com.example.pages79_90;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double currentBillTotal=0.0;//סכום החיוב
    private int currentCustomPercent;
    private EditText BillEditText;//קליטת סכום חיוב
    private EditText tip10EditText;//הצגת טיפ 10%
    private EditText total10EditText;//סכום חיוב עם טיפ 10%
    private EditText tip15EditText;//הצגת טיפ 15%
    private EditText total15EditText;//סכום חיוב עם טיפ 15%
    private EditText tip20EditText;//הצגת טיפ 20%
    private EditText total20EditText;//סכום חיוב עם טיפ 20%
    private TextView customTipTextView;//תווית הצגת מידע עבור טיפ מותאם אישית
    private EditText tipCustomEditText;//תווית הצגת מידע עבור טיפ מותאם אישית
    private EditText totalCustomEditText;//סכום חיוב עבור טיפ מותאם אישית
    private SeekBar customSeekBar;//רפרנס עבור סיקבר

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //יצירת רפרנסים
        tip10EditText=findViewById(R.id.tip10EditText);
        total10EditText=findViewById(R.id.total10EditText);
        tip15EditText=findViewById(R.id.tip15EditText);
        total15EditText=findViewById(R.id.total15EditText);
        tip20EditText=findViewById(R.id.tip20EditText);
        total20EditText=findViewById(R.id.total20EditText);
        //יצירת רפרנס לטיפ מותאם אישית
        customTipTextView=findViewById(R.id.customTipTextView);
        tipCustomEditText=findViewById(R.id.tipCustomEditText);
        totalCustomEditText=findViewById(R.id.totalCustomEditText);
        //יצירת רפרנס וקביעת מטפל אירוע שינוי תיבת טקסט
        BillEditText=(EditText) findViewById(R.id.billEditText);
        BillEditText.addTextChangedListener(BillEditTextWatcher);
        //קביעת מטפל אירוע של סייקבר
        customSeekBar=(SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }// end OnCreate

    private void updateStandard() {
        //calculate 10% tip
        double tenPercentTip = currentBillTotal * 0.1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        //display values
        tip10EditText.setText(String.format("%.02f", tenPercentTip));
        total10EditText.setText((String.format("%.02f", tenPercentTotal)));

        //calculate 15% tip
        double fifteenPercentTip = currentBillTotal * 0.15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
        //display values
        tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
        total15EditText.setText((String.format("%.02f", fifteenPercentTotal)));

        //calculate 20% tip
        double twentyPercentTip = currentBillTotal * 0.2;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;
        //display values
        tip20EditText.setText(String.format("%.02f", twentyPercentTip));
        total20EditText.setText((String.format("%.02f", twentyPercentTotal)));
    }//end updateStandard

    //updates the custom tip and total EditText
    private void updatesCustom(){
        //position of the SeekBar
        customTipTextView.setText(currentCustomPercent+"%");
        //calculate the custom tip amount
        double customTipAmount=currentBillTotal*currentCustomPercent*0.01;
        //calculate total bill+custom tip
        double customTotalAmount=currentBillTotal+customTipAmount;
        //display: tip and total bill amounts
        tipCustomEditText.setText(String.format("%.02f",customTipAmount));
        totalCustomEditText.setText(String.format("%.02f",customTotalAmount));
    }//end custom amount

    //הגדרת מאזין לאירוע קליטת חיוב
    //TextWatcher listener
    private TextWatcher BillEditTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currentBillTotal=Double.parseDouble(s.toString());
            }//end try
            catch (NumberFormatException e)
            {
                currentBillTotal=0.0;//default if an exception occurs
            }
            //עדכון טיפ רגיל ומותאם אישית
            updateStandard();
            updatesCustom();
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //----------------------end TextWatcher listener

    //When the user change the position of the SeekBar
    private SeekBar.OnSeekBarChangeListener customSeekBarListener=new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          //sets currentCustomPercent to SeekBar position
          currentCustomPercent=seekBar.getProgress();
          //update EditText for custom tip and total
            updatesCustom();
        }
    };
}


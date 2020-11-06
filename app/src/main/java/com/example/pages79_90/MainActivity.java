package com.example.pages79_90;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private double currentBillTotal;//סכום החיוב
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
        BillEditText=findViewById(R.id.billEditText);
        BillEditText.addTextChangedListener(BillEditTextWatcher);
        //קביעת מטפל אירוע של סייקבר
        customSeekBar=findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }// end OnCreate

    private void updateStandard(){
        //calculate 10% tip
        double tenPercentTip=currentBillTotal*0.1;
        double tenPercentTotal=currentBillTotal+tenPercentTip;
        //display values
        tip10EditText.setText(String.format("%0.02f",tenPercentTip));
        total10EditText.setText((String.format("0.02%",tenPercentTotal)));

        //calculate 15% tip
        double fifteenPercentTip=currentBillTotal*0.15;
        double fifteenPercentTotal=currentBillTotal+fifteenPercentTip;
        //display values
        tip15EditText.setText(String.format("%0.02f",fifteenPercentTip));
        total15EditText.setText((String.format("0.02%",fifteenPercentTotal)));
    }
    private SeekBar.OnSeekBarChangeListener customSeekBarListener;
    private TextWatcher BillEditTextWatcher;
}

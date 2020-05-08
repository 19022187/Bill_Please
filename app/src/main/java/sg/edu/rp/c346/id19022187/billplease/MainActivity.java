package sg.edu.rp.c346.id19022187.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etAmt, etPax, etDiscount;
    ToggleButton tSvs, tGst;
    Button btnSplit, btnReset;
    TextView tvTotal, tvPax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.editTextAmt);
        etPax = findViewById(R.id.editTextPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        tSvs = findViewById(R.id.toggleButtonSvs);
        tGst = findViewById(R.id.toggleButtonGst);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tvTotal = findViewById(R.id.textViewTotal);
        tvPax = findViewById(R.id.textViewPax);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmt.getText().toString().trim().length() == 0){
                    return;
                }
                if (etPax.getText().toString().trim().length() == 0){
                    return;
                }
                double amt = 0.0;
                if (!tSvs.isChecked() && !tGst.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString());
                }
                else if (tSvs.isChecked() && !tGst.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                }
                else if (!tSvs.isChecked() && tGst.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.1;
                }
                else {
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                }

                if (etDiscount.getText().toString().trim().length() !=0){
                    double dis = Double.parseDouble(etDiscount.getText().toString().trim());
                    amt = amt * (1 - dis/100.0);
                }

                tvTotal.setText("Total Bill: $" + String.format("%.2f", amt));
                int numPax = Integer.parseInt(etPax.getText().toString());
                tvPax.setText("Each pays: $" + String.format("%.2f" , amt / numPax));

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
                etPax.setText("");
                etDiscount.setText("");
                tSvs.setText("");
                tGst.setText("");
                btnSplit.setText("");
                btnReset.setText("");
                tvTotal.setText("");
                tvPax.setText("");

            }
        });
    }
}

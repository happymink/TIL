package kr.xxunghee.design_pattern.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private TextView btnAC, btnSign, btnMod, btnDiv, btnMul, btnSub, btnAdd, btnDot, btnResult;
    private TextView num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;

    private boolean hasDot = false;
    private boolean isPositive = true;

    private double firstVal = -1;
    private double secondVal = -1;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // screen
        tvResult  = findViewById(R.id.tv_result);

        // calc button
        btnAC     = findViewById(R.id.btn_ac);
        btnAC.setOnClickListener(v -> calcAC());
        btnSign   = findViewById(R.id.btn_sign);
        btnSign.setOnClickListener(v -> calcSign());
        btnMod    = findViewById(R.id.btn_mod);
        btnMod.setOnClickListener(v -> calcMod());
        btnDiv    = findViewById(R.id.btn_div);
        btnDiv.setOnClickListener(v -> calcDiv());
        btnMul    = findViewById(R.id.btn_mul);
        btnMul.setOnClickListener(v -> calcMul());
        btnSub    = findViewById(R.id.btn_sub);
        btnSub.setOnClickListener(v -> calcSub());
        btnAdd    = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> calcAdd());
        btnDot    = findViewById(R.id.btn_dot);
        btnDot.setOnClickListener(v -> calcDot());
        btnResult = findViewById(R.id.btn_result);
        btnResult.setOnClickListener(v -> calcResult());

        // number button
        num0 = findViewById(R.id.num_0);
        num0.setOnClickListener(v -> numberClicked("0"));
        num1 = findViewById(R.id.num_1);
        num1.setOnClickListener(v -> numberClicked("1"));
        num2 = findViewById(R.id.num_2);
        num2.setOnClickListener(v -> numberClicked("2"));
        num3 = findViewById(R.id.num_3);
        num3.setOnClickListener(v -> numberClicked("3"));
        num4 = findViewById(R.id.num_4);
        num4.setOnClickListener(v -> numberClicked("4"));
        num5 = findViewById(R.id.num_5);
        num5.setOnClickListener(v -> numberClicked("5"));
        num6 = findViewById(R.id.num_6);
        num6.setOnClickListener(v -> numberClicked("6"));
        num7 = findViewById(R.id.num_7);
        num7.setOnClickListener(v -> numberClicked("7"));
        num8 = findViewById(R.id.num_8);
        num8.setOnClickListener(v -> numberClicked("8"));
        num9 = findViewById(R.id.num_9);
        num9.setOnClickListener(v -> numberClicked("9"));
    }

    private void calcAC() {
        hasDot = false;
    }

    private void calcSign() {

    }

    private void calcMod() {
        hasDot = false;
    }

    private void calcDiv() {
        hasDot = false;
    }

    private void calcMul() {
        hasDot = false;
    }

    private void calcSub() {
        hasDot = false;
    }

    private void calcAdd() {
        hasDot = false;
    }

    private void calcDot() {
        if(firstVal != -1 && secondVal == -1) {
            Toast.makeText(this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_LONG).show();
            return;
        }

        if(!hasDot) {
            tvResult.setText(tvResult.getText().toString() + ".");
            hasDot = true;
        }
    }

    private void calcResult() {

    }

    private void numberClicked(String val) {
        if(tvResult.getText().length() > 10) {
            Toast.makeText(this, "더 이상 입력되지 않습니다.", Toast.LENGTH_LONG).show();
            return;
        }

        if(tvResult.getText().toString().equals("0") || (firstVal != -1 && secondVal == -1)) {
            tvResult.setText(val);
        } else {
            tvResult.setText(tvResult.getText().toString() + val);
        }
    }
}
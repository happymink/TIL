package kr.xxunghee.design_pattern.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvFormula, tvResult;
    private TextView btnAC, btnSign, btnMod, btnDiv, btnMul, btnSub, btnAdd, btnDot, btnResult;
    private TextView num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // screen
        tvFormula = findViewById(R.id.tv_formula);
        tvResult  = findViewById(R.id.tv_result);

        // calc button
        btnAC     = findViewById(R.id.btn_ac);
        btnSign   = findViewById(R.id.btn_sign);
        btnMod    = findViewById(R.id.btn_mod);
        btnDiv    = findViewById(R.id.btn_div);
        btnMul    = findViewById(R.id.btn_mul);
        btnSub    = findViewById(R.id.btn_sub);
        btnAdd    = findViewById(R.id.btn_add);
        btnDot    = findViewById(R.id.btn_dot);
        btnResult = findViewById(R.id.btn_result);

        // number button
        num0 = findViewById(R.id.num_0);
        num1 = findViewById(R.id.num_1);
        num2 = findViewById(R.id.num_2);
        num3 = findViewById(R.id.num_3);
        num4 = findViewById(R.id.num_4);
        num5 = findViewById(R.id.num_5);
        num6 = findViewById(R.id.num_6);
        num7 = findViewById(R.id.num_7);
        num8 = findViewById(R.id.num_8);
        num9 = findViewById(R.id.num_9);
    }
}
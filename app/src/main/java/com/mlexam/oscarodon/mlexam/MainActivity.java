package com.mlexam.oscarodon.mlexam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Inputs
    protected EditText mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmount = (EditText) findViewById(R.id.totalAmount);

    }

    public void selectPaymentMethod(View view)
    {
        goToActivity(new SelectPaymentActivity());
    }

    private void goToActivity(Activity activity) {

        int integerAmount = Integer.parseInt(mAmount.getText().toString());
        Intent intent = new Intent(this, activity.getClass());
        intent.putExtra("amount",integerAmount);
        startActivity(intent);
    }
}

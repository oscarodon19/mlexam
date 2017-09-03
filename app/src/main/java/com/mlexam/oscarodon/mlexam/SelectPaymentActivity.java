package com.mlexam.oscarodon.mlexam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mercadopago.adapters.PaymentMethodsAdapter;
import com.mercadopago.decorations.DividerItemDecoration;
import com.mercadopago.util.JsonUtil;
import com.mercadopago.util.LayoutUtil;
import com.mlexam.oscarodon.mlexam.handler.HttpHandler;
import com.mlexam.oscarodon.mlexam.model.PaymentMethod;
import com.mlexam.oscarodon.mlexam.parser.PaymentMethodParser;


import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.List;

public class SelectPaymentActivity extends AppCompatActivity {

    private List<PaymentMethod> paymentMethodList;
    private String TAG = GetPaymentMethodRequest.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        Intent mIntent = getIntent();
        int amount = mIntent.getIntExtra("amount", 0);

        paymentMethodList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        mRecyclerView = (RecyclerView) findViewById(R.id.payment_methods_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new GetPaymentMethodRequest().execute();

    }

    protected void setContentView(){

        setContentView(R.layout.activity_select_payment);
    }


    public class GetPaymentMethodRequest extends AsyncTask<Void, Void, Void> {

        public static final String PAYMENT_METHOD_BASE_URL = "https://api.mercadopago.com/v1/payment_methods?public_key=444a9ef5-8a6b-429f-abdf-587639155d88";
        private PaymentMethodParser paymentMethodParser = new PaymentMethodParser();
        private String TAG = GetPaymentMethodRequest.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {

            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(PAYMENT_METHOD_BASE_URL);


            Log.i(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {

                try {

                    JSONArray paymentMethods = new JSONArray(jsonStr);

                    for (int i = 0; i < paymentMethods.length(); i++) {

                        PaymentMethod paymentMethod = paymentMethodParser.parseFromJSONObject(paymentMethods.getJSONObject(i));

                        paymentMethodList.add(paymentMethod);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
//            ListAdapter adapter = new SimpleAdapter(SelectPaymentActivity.this,paymentMethodList);
//            lv.setAdapter(adapter);
        }

    }

}


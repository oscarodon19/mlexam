package com.mlexam.oscarodon.mlexam.parser;

import com.mlexam.oscarodon.mlexam.model.PaymentMethod;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oscarodon on 2/9/17.
 */

public class PaymentMethodParser {

    public PaymentMethod parseFromJSONObject(JSONObject object) throws JSONException{

        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setId(object.getString("id"));
        paymentMethod.setName(object.getString("name"));
        paymentMethod.setPaymentTypeId(object.getString("payment_type_id"));
        paymentMethod.setStatus(object.getString("status"));

        return paymentMethod;

    }

}

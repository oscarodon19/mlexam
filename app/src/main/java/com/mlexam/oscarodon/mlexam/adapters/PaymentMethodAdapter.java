package com.mlexam.oscarodon.mlexam.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mlexam.oscarodon.mlexam.R;
import com.mlexam.oscarodon.mlexam.model.PaymentMethod;

import java.util.List;

/**
 * Created by oscarodon on 3/9/17.
 */

public class PaymentMethodAdapter extends RecyclerView<PaymentMethodAdapter,RecyclerView.ViewHolder> {

    private Activity mActivity;
    private List<PaymentMethod> mData;
    private View.OnClickListener mListener = null;

    public ViewHolder(View v, View.OnClickListener listener) {


    }
}

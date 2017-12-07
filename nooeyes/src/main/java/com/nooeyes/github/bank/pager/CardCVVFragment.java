package com.nooeyes.github.bank.pager;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.nooeyes.alex.R;

import static com.nooeyes.github.bank.CreditCardUtils.EXTRA_CARD_CVV;

/**
 * Created by sharish on 9/1/15.
 */
public class CardCVVFragment extends CreditCardFragment  {

    private EditText mCardCVVView;

    public CardCVVFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle state) {

        View v = inflater.inflate(R.layout.bank_lyt_card_cvv, group,false);
        mCardCVVView = (EditText) v.findViewById(R.id.card_cvv);
        mCardCVVView.addTextChangedListener(this);


        String cvv = "";
        if(getArguments() != null && getArguments().containsKey(EXTRA_CARD_CVV)) {
            cvv = getArguments().getString(EXTRA_CARD_CVV);
        }

        if(cvv == null) {
            cvv = "";
        }

        mCardCVVView.setText(cvv);

        return v;
    }

    @Override
    public void afterTextChanged(Editable s) {

        onEdit(s.toString());
        if(s.length() == 3) {
            onComplete();
        }

    }

    @Override
    public void focus() {

        if(isAdded()) {
            mCardCVVView.selectAll();
        }
    }
}

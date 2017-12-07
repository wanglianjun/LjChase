package com.acwlj.demo.ui.text;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.view.EmailAutoCompleteTextView;

public class AutoCompleteActivity extends BaseActivity {
    private EmailAutoCompleteTextView emailAutoText;
    private AutoCompleteTextView autoText;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_auto_complete;
    }

    @Override
    protected void initPageView() {
        emailAutoText = (EmailAutoCompleteTextView) findViewById(R.id.email_auto_text);
        autoText = (AutoCompleteTextView) findViewById(R.id.autoText);
    }

    @Override
    protected void initPageViewListener() {

    }

    @Override
    protected void process(Bundle savedInstanceState) {
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        autoText.setAdapter(adapter);
    }

}

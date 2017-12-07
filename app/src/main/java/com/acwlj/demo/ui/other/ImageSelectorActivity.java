package com.acwlj.demo.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//import butterknife.InjectView;
import butterknife.Bind;
import butterknife.OnClick;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;

/**
 * Created by dong on 14/12/24.
 */
public class ImageSelectorActivity extends BaseActivity {
    @Bind(R.id.uri)
    TextView uriView;
    @Bind(R.id.path)
    TextView pathView;
    @Bind(R.id.image)
    ImageView imageview;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_selected_image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.button)
    void selectImage() {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageIntent.setType("image/*");
        startActivityForResult(imageIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {

        }
    }

}

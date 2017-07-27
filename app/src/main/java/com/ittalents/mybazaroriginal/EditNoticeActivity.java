package com.ittalents.mybazaroriginal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ittalents.mybazaroriginal.model.Notices;

public class EditNoticeActivity extends AppCompatActivity {

    public static final int RESULT_CODE_CANCEL = 2;
    public static final int RESULT_CODE_SAVE = 3;
    private TextView labelNotice;
    private EditText titleNotice;
    private EditText descrNotice;
    private EditText phoneNotice;
    private EditText priceNotice;
    private Button saveButton;
    private Button  cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notice);

        labelNotice = (TextView) findViewById(R.id.label_notice);
        titleNotice = (EditText) findViewById(R.id.title_notice);
        descrNotice = (EditText) findViewById(R.id.description_notice);
        phoneNotice = (EditText) findViewById(R.id.phone_notice);
        priceNotice = (EditText) findViewById(R.id.price_notice);

        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        final Notices n = (Notices) getIntent().getExtras().getSerializable("notice");

        labelNotice.setText("Edit " + n.getTitle().toString());

        titleNotice.setText(n.getTitle().toString());
        descrNotice.setText(n.getTitle().toString());
        phoneNotice.setText(n.getPhone().toString());
        priceNotice.setText(n.getPrice()+"");

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CODE_CANCEL);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleNotice.setText(titleNotice.getText().toString());
                descrNotice.setText(descrNotice.getText().toString());
                phoneNotice.setText(phoneNotice.getText().toString());
                priceNotice.setText(Integer.parseInt(phoneNotice.getText().toString()));

                Intent intent = new Intent();
                intent.putExtra("title", titleNotice.getText().toString());
                intent.putExtra("description", descrNotice.getText().toString());
                intent.putExtra("phone", phoneNotice.getText().toString());
                intent.putExtra("price", priceNotice.getText().toString());

                setResult(RESULT_CODE_SAVE, intent);
                finish();

            }
        });
    }
}

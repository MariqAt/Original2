package com.ittalents.mybazaroriginal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittalents.mybazaroriginal.model.Notices;
import com.ittalents.mybazaroriginal.model.Seller;

public class SellerActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private TextView labelClient;

    private TextView title1;
    private ImageView image1;
    private TextView description1;
    private TextView price1;
    private TextView phone1;
    private Button editButton1;

    private TextView title2;
    private TextView image2;
    private TextView description2;
    private TextView price2;
    private TextView phone2;
    private Button editButton2;

    private TextView title3;
    private TextView image3;
    private TextView description3;
    private TextView price3;
    private TextView phone3;
    private Button editButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        labelClient = (TextView) findViewById(R.id.label_client);

        if ( getIntent().getExtras() == null) {
            if (getIntent().getExtras().getSerializable("seller") == null) {
                return;
            }
        }
        Seller seller = (Seller) getIntent().getExtras().getSerializable("seller");

        labelClient.setText("Welcome, " + seller.getUsername() + " (logged as seller)");

        final Notices n1 = seller.getNotices().get(0);
        title1 = (TextView) findViewById(R.id.edit_title1);
        image1 = (ImageView) findViewById(R.id.edit_image);
        description1 = (TextView) findViewById(R.id.edit_description);
        price1 = (TextView) findViewById(R.id.edit_price);
        phone1 = (TextView) findViewById(R.id.edit_phone);

        title1.setText(n1.getTitle());
        image1.setImageResource(n1.getImage());
        description1.setText(n1.getDescription());
        price1.setText(n1.getPrice()+"");
        phone1.setText(n1.getPhone());


        editButton1 = (Button) findViewById(R.id.edit_notice1);
        editButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerActivity.this, EditNoticeActivity.class);
                intent.putExtra("notice", n1);
                startActivityForResult(intent, 11);
            }
        });

        final Notices n2 = seller.getNotices().get(1);
        title2 = (TextView) findViewById(R.id.edit_title2);
        image2 = (TextView) findViewById(R.id.edit_image2);
        description2 = (TextView) findViewById(R.id.edit_description2);
        price2 = (TextView) findViewById(R.id.edit_price2);
        phone2 = (TextView) findViewById(R.id.edit_phone2);

        title2.setText(n2.getTitle());
        image2.setText(n2.getImage()+"");
        description2.setText(n2.getDescription());
        price2.setText(n2.getPrice()+"");
        phone2.setText(n2.getPhone());

        editButton2 = (Button) findViewById(R.id.edit_notice2);
        editButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerActivity.this, EditNoticeActivity.class);
                intent.putExtra("notice", n2);
                startActivityForResult(intent, 12);
            }
        });

        final Notices n3 = seller.getNotices().get(2);
        title3 = (TextView) findViewById(R.id.edit_title1);
        image3 = (TextView) findViewById(R.id.edit_image3);
        description3 = (TextView) findViewById(R.id.edit_description3);
        price3 = (TextView) findViewById(R.id.edit_price3);
        phone3 = (TextView) findViewById(R.id.edit_phone3);

        title3.setText(n3.getTitle());
        image3.setText(n3.getImage()+"");
        description3.setText(n3.getDescription());
        price3.setText(n3.getPrice()+"");
        phone3.setText(n3.getPhone());

        editButton3 = (Button) findViewById(R.id.edit_notice3);
        editButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerActivity.this, EditNoticeActivity.class);
                intent.putExtra("notice", n3);
                startActivityForResult(intent, 13);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == EditNoticeActivity.RESULT_CODE_CANCEL) {
                Toast.makeText(this, "Your notice is not change!", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == EditNoticeActivity.RESULT_CODE_SAVE) {


            }

    }
}

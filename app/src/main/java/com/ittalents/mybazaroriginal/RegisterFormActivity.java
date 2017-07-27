package com.ittalents.mybazaroriginal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ittalents.mybazaroriginal.model.Seller;
import com.ittalents.mybazaroriginal.model.User;

public class RegisterFormActivity extends AppCompatActivity {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText confirmPassEdit;
    private EditText emailEdit;
    private Button registrationButton;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        usernameEdit = (EditText) findViewById(R.id.username_edit);
        passwordEdit = (EditText) findViewById(R.id.pass_edit);
        confirmPassEdit = (EditText) findViewById(R.id.pass2_edit);
        emailEdit = (EditText) findViewById(R.id.email_edit);
        registrationButton = (Button) findViewById(R.id.registration_button);
        rg = (RadioGroup) findViewById(R.id.radio_group);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int checked = group.getCheckedRadioButtonId();
                switch (checked) {
                    case R.id.seller_rb:
                        MainActivity.isSeller = true;
                        break;
                    case R.id.clients_rb:
                        MainActivity.isSeller = false;
                        break;
                }
            }
        });

        final RadioButton rbSeller = (RadioButton) findViewById(R.id.seller_rb);
        final RadioButton rbClient = (RadioButton) findViewById(R.id.clients_rb);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validDate()) {
                    if (rbSeller.isChecked()) {
                        Seller seller = new Seller(usernameEdit.getText().toString(),
                                passwordEdit.getText().toString(), emailEdit.getText().toString());
                        MainActivity.sellers.add(seller);

                        Intent intent = new Intent(RegisterFormActivity.this, SellerActivity.class);
                        intent.putExtra("seller", seller);
                        startActivity(intent);
                    } else
                    if (rbClient.isChecked()) {
                        User user = new User(usernameEdit.getText().toString(),
                                passwordEdit.getText().toString(), emailEdit.getText().toString());
                        MainActivity.clients.add(user);

                        Intent intent = new Intent(RegisterFormActivity.this, UserActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        }
                    } else
                    if (!rbClient.isChecked() && !rbSeller.isChecked()) {
                        Toast.makeText(RegisterFormActivity.this, "You have to choice: Seller or Client", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validDate() {
        boolean isValid = true;
        String name = usernameEdit.getText().toString();
        if (name.isEmpty()) {
            isValid = false;
            usernameEdit.requestFocus();
            usernameEdit.setError("Username must be not empty!");
        }
        String pass1 = passwordEdit.getText().toString();
        if (pass1.isEmpty()) {
            isValid = false;
            passwordEdit.requestFocus();
            passwordEdit.setError("Password not must be empty!");
        }
        String pass2 = confirmPassEdit.getText().toString();
        if (pass2.isEmpty()) {
            isValid = false;
            confirmPassEdit.requestFocus();
            confirmPassEdit.setError("Confirm password must be not empty!");
        }
        if (!pass1.equals(pass2)) {
            isValid = false;
            confirmPassEdit.requestFocus();
            confirmPassEdit.setError("Confirm password must be equals as password");
        }
        String mail = emailEdit.getText().toString();
        if (mail.isEmpty()) {
            isValid = false;
            emailEdit.requestFocus();
            emailEdit.setError("Mail must be not empty!");
        }
        return isValid;
    }

}

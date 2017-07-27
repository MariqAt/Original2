package com.ittalents.mybazaroriginal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittalents.mybazaroriginal.model.Seller;
import com.ittalents.mybazaroriginal.model.User;

public class LoginFormActivity extends AppCompatActivity {

    private EditText usernameLoginform;
    private EditText passLoginform;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        usernameLoginform = (EditText) findViewById(R.id.username_edit_loginform);
        passLoginform = (EditText) findViewById(R.id.pass_edit_loginform);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validData()) {
                    if (MainActivity.isSeller) {
                        for (Seller seller : MainActivity.sellers) {
                            if (seller.getUsername().equals(usernameLoginform.getText().toString()) &&
                                    seller.getPassword().equals(passLoginform.getText().toString())) {

                                Intent intent = new Intent(LoginFormActivity.this, SellerActivity.class);
                                intent.putExtra("seller", seller);
                                startActivity(intent);
                                break;
                            } else {
                                Toast.makeText(LoginFormActivity.this, "Yor password or your username are not valid!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else
                    if (!MainActivity.isSeller) {
                        for (User u : MainActivity.clients) {
                            if (u.getUsername().equals(usernameLoginform.getText().toString()) &&
                                    u.getPassword().equals(passLoginform.getText().toString())) {

                                Intent intent = new Intent(LoginFormActivity.this, SellerActivity.class);
                                intent.putExtra("seller", u);
                                startActivity(intent);
                                break;
                            } else {
                                Toast.makeText(LoginFormActivity.this, "Yor password or your username are not valid!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

    }

    private boolean validData() {
        boolean isValid = true;
        String name = usernameLoginform.getText().toString();
        if (name.isEmpty()) {
            isValid = false;
            usernameLoginform.requestFocus();
            usernameLoginform.setError("Username must be not empty!");
        }
        String pass = passLoginform.getText().toString();
        if (pass.isEmpty()) {
            isValid = false;
            passLoginform.requestFocus();
            passLoginform.setError("Password must be not empty!");
        }
        return isValid;
    }
}

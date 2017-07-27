package com.ittalents.mybazaroriginal;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ittalents.mybazaroriginal.model.Notices;
import com.ittalents.mybazaroriginal.model.Seller;
import com.ittalents.mybazaroriginal.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button loginSellerButton;
    private Button loginClientButton;
    private Button registerAccountButton;
    private Button alertDialogBtn;
    private Button startTimer;
    private Button pauseTimer;
    private CountDownTimer countDownTimer;
    private ViewStub stub;

    public static ArrayList<Seller> sellers = new ArrayList<>();
    public static ArrayList<User> clients = new ArrayList<>();
    public static boolean isSeller;

    {
        Seller user1 = new Seller("pesho", "pesho", "pesho");
        user1.addNotice(new Notices("Обувки", "Нова, Размер: 38", R.mipmap.ic_launcher, 100, "0888888888"));
        user1.addNotice(new Notices("Чанта", "Нова, GUESS", R.mipmap.ic_launcher, 400, "0888888888"));
        user1.addNotice(new Notices("Рокля", "Нова, Размер: универсал", R.mipmap.ic_launcher, 150, "0888888888"));
        this.sellers.add(user1);

        Seller user2 = new Seller("minka", "minka", "minka");
        user2.addNotice(new Notices("Чанта", "addidas", R.mipmap.ic_launcher, 100,"0888998811"));
        user2.addNotice(new Notices("Пола", "Нова", R.mipmap.ic_launcher, 100, "0888998811"));
        user2.addNotice(new Notices("Панталон", "Нова, Размер: С", R.mipmap.ic_launcher, 100, "0888998811"));
        this.sellers.add(user2);

        User user3 = new User("stamat", "stamat", "stamat");
        this.clients.add(user3);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginSellerButton = (Button) findViewById(R.id.button_login_as_seller);
        loginClientButton = (Button) findViewById(R.id.button_login_as_client);
        registerAccountButton = (Button) findViewById(R.id.button_register_new_account);
        //alertDialogBtn = (Button) findViewById(R.id.button_alert_dialog);
        startTimer = (Button) findViewById(R.id.start_timer);
        pauseTimer = (Button) findViewById(R.id.pause_timer);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 countDownTimer = new CountDownTimer(20000, 1000, true);
                countDownTimer.onTick(2000);

            }
        });

        pauseTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null) {
                    countDownTimer.pause();
                }
                Toast.makeText(getBaseContext(), "countDownTimer, is null", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout m = (LinearLayout) findViewById(R.id.mimi);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        m.setBackgroundResource(R.drawable.mimi);

//        alertDialogBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createAlertDialog();
//            }
//        });



        loginSellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginFormActivity.class);
                isSeller = true;
                startActivity(intent);
            }
        });

        loginClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginFormActivity.class);
                isSeller = false;
                startActivity(intent);
            }
        });

        registerAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterFormActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        builder.setView(R.layout.timer_popup);
        builder.create();
        builder.show();
    }
}

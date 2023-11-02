package com.example.bgsodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.bgsodev.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int randomCode;
    int basamakSayisi=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.CreateCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.CreateCodeButton.setClickable(false);
                randomCode=CreateCode();
                binding.CodeText.setText("Giriş Kodunuz: "+randomCode);
                new CountDownTimer(30000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.timeLeftText.setText("Kalan Süre: "+ millisUntilFinished/1000);
                        binding.loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(randomCode==Integer.parseInt(String.valueOf(binding.CodeEditText.getText()))){
                                    cancel();
                                    Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Yanlış Kod Girdiniz!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    @Override
                    public void onFinish() {
                        Toast.makeText(getApplicationContext(),"Verilen süre içerisinde giremediniz. Tekrar Deneyiniz!",Toast.LENGTH_LONG).show();
                        binding.CreateCodeButton.setClickable(true);
                    }
                }.start();

            }
        });




    }

    public int CreateCode(){
        int min = (int) Math.pow(10, basamakSayisi - 1);
        int max = (int) Math.pow(10, basamakSayisi);
        Random random = new Random();
        randomCode = min + random.nextInt(max);
        return randomCode;
    }
}
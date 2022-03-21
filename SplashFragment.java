package com.example.automatedwarehouseinventory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class SplashFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_splash, container, false);
        ImageView logo = view.findViewById(R.id.logoImageView);
        YoYo.with(Techniques.FadeIn).duration(3000).playOn(logo);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            YoYo.with(Techniques.FadeOut).playOn(logo);
        }, 3250);
        return view;
    }
}

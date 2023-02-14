package com.example.fooddeliveryapp.activity.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fooddeliveryapp.activity.fragment.LoginFragment;
import com.example.fooddeliveryapp.activity.fragment.RegistrationFragment;

public class EnterPagerAdapter extends FragmentPagerAdapter {

    final Fragment[] fragments = new Fragment[2];

    public EnterPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new LoginFragment();
        fragments[1] = new RegistrationFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "Авторизация" : "Регистрация";
    }
}

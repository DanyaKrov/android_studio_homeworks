package com.example.fooddeliveryapp.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.buttonEnter.setOnClickListener(view -> {
            String email = binding.inputEmail.getInputText();
            String password = binding.inputPassword.getInputText();
            Toast.makeText(getContext(), "Signed:" + email + " " + password, Toast.LENGTH_SHORT).show();

        });
        return binding.getRoot();
    }
}
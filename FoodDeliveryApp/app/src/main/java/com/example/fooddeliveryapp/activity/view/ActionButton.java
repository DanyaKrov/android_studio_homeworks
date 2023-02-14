package com.example.fooddeliveryapp.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ViewActionButtonBinding;
import com.example.fooddeliveryapp.databinding.ViewInputFieldBinding;

public class ActionButton extends FrameLayout {
    ViewActionButtonBinding binding;

    public ActionButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public ActionButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }
    private void initView(AttributeSet attrs){
        binding = ViewActionButtonBinding.inflate(LayoutInflater.from(getContext()), this, true);
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ActionButton, 0, 0);
        try {
            String text = attributes.getString(R.styleable.ActionButton_android_text);
            binding.getRoot().setText(text);

            boolean textAlwaysCaps = attributes.getBoolean(R.styleable.ActionButton_android_textAllCaps, true);
            binding.getRoot().setAllCaps(textAlwaysCaps);
        }
        finally {
            attributes.recycle();
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        binding.getRoot().setOnClickListener(l);
    }
}

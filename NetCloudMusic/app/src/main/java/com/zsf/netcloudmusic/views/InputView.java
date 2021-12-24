package com.zsf.netcloudmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zsf.netcloudmusic.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/24
 */
public class InputView extends FrameLayout {
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View mView;
    private ImageView mIcon;
    private EditText mEditText;

    public InputView(@NonNull Context context) {
        this(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        //获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon = ta.getResourceId(R.styleable.inputView_input_icon, R.mipmap.logo);
        inputHint = ta.getString(R.styleable.inputView_input_hint);
        isPassword = ta.getBoolean(R.styleable.inputView_is_password, false);
        ta.recycle();

        //绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        mIcon = mView.findViewById(R.id.icon_phone);
        mEditText = mView.findViewById(R.id.et_input);

        //布局关联属性
        mIcon.setImageResource(inputIcon);
        mEditText.setHint(inputHint);
        mEditText.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);
        addView(mView);
    }

    /**
     * 返回输入的内容
     * @return
     */
    public String getInputStr() {
        return mEditText.getText().toString().trim();
    }
}

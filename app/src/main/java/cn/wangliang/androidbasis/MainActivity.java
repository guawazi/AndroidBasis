package cn.wangliang.androidbasis;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SuggestionSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.github.mzule.activityrouter.router.Routers;

import butterknife.BindView;
import cn.wangliang.androidbasis.ui.base.BaseActivity;
import cn.wangliang.androidbasis.util.CommonUtils;

import static android.text.style.DynamicDrawableSpan.ALIGN_BOTTOM;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        Window window = getWindow();
        // 6.0 以上 设置状态栏颜色 setStatusBarColor,改变状态栏字颜色 SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.RED);
            // 设置浅色状态栏时的界面显示
            View decor = window.getDecorView();
            int ui = decor.getSystemUiVisibility();
            // 关于判断是否是浅色，还有一种算法
            if (true) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);
        }

        // 5.0 - 6.0 之间 设置状态栏颜色 setStatusBarColor 改变状态栏字颜色 只有小米和魅族提供了方法修改，其他手机没有办法修改
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.WHITE);
        }


//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(Color.WHITE);
//        }
//        // 去掉系统状态栏下的windowContentOverlay
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            View v = window.findViewById(android.R.id.content);
//            if (v != null) {
//                v.setForeground(null);
//            }
//        }
//        // 设置浅色状态栏时的界面显示
//        View decor = window.getDecorView();
//        int ui = decor.getSystemUiVisibility();
//        if (true) {
//            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//        } else {
//            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//        }
//        decor.setSystemUiVisibility(ui);
    }

    @Override
    protected void initEventAndData() {


        // setSpan(Object what, int start, int end, int flags)
        // 1. what: 想设置的 span
        // 2. start end ： 对应的范围，左闭右开
        // 3. flags : 在对应范围前插入或者之后添加的内容是否应用该 span
        // SPAN_INCLUSIVE_EXCLUSIVE  前面包括，后面不包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本不会应用该样式
        // SPAN_INCLUSIVE_INCLUSIVE  前面包括，后面包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本也会应用该样式
        // SPAN_EXCLUSIVE_EXCLUSIVE  前面不包括，后面不包括
        // SPAN_EXCLUSIVE_INCLUSIVE  前面不包括，后面包括

        ///////////// BackgroundColorSpan /////////////

        int length = 0;
        SpannableStringBuilder builder = new SpannableStringBuilder("hello");
        builder.setSpan(new BackgroundColorSpan(Color.RED), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.append(System.lineSeparator());

        length = builder.length();
        builder.append("world");
        builder.setSpan(new BackgroundColorSpan(Color.GREEN), length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ///////////// ClickableSpan /////////////

        length = builder.length();
        builder.append("点击试试");
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) { // 点击事件
                CommonUtils.showToastShort("我被点了");
                Routers.open(MainActivity.this,"common://main");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                // 点击前的背景样式
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(true);
                // 点击后的背景样式是textview 的高亮属性  textColorHighlight
            }
        }, length , builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //在单击链接时凡是要执行的动作，都必须设置MovementMethod对象
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());

        builder.insert(length,"我是新加的");

        ///////////// ForegroundColorSpan /////////////

        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("文字颜色");
        builder.setSpan(new ForegroundColorSpan(Color.RED){
                            @Override
                            public void updateDrawState(TextPaint ds) {
//                                super.updateDrawState(ds);
                                // 也可以在这里面自己设置
                                ds.setColor(Color.GRAY);
                                ds.setUnderlineText(true);
                            }
                        },
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ///////////// MaskFilterSpan /////////////

        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("添加滤镜");
        builder.setSpan(new MaskFilterSpan(new BlurMaskFilter(1, BlurMaskFilter.Blur.INNER)),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ///////////// StrikethroughSpan /////////////
        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("添加删除线");
        builder.setSpan(new StrikethroughSpan(){
                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                            }
                        },
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ///////////// SuggestionSpan /////////////
        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("建议的文字");
        builder.setSpan(new SuggestionSpan(this,
                        new String[]{SuggestionSpan.SUGGESTION_SPAN_PICKED_BEFORE},SuggestionSpan.FLAG_AUTO_CORRECTION),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        ///////////// UnderlineSpan /////////////
        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("下划线的文字");
        builder.setSpan(new UnderlineSpan(),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ////////////////  AbsoluteSizeSpan  //////////////////////

        builder.append("这是以前的部分");
        length = builder.length();
        builder.append(System.lineSeparator());
        builder.append("字体要放大的部分");
        builder.setSpan(new AbsoluteSizeSpan(80),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.append("这是变会原来的部分");


        ////////////////  DynamicDrawableSpan  //////////////////////
        length = builder.length();
        builder.append("这是要被替换成图片的");
        builder.append(System.lineSeparator());
        builder.setSpan(new DynamicDrawableSpan() {
                            @Override
                            public Drawable getDrawable() {
                                Drawable drawable = CommonUtils.getDrawable(R.mipmap.ic_launcher_round);
                                drawable.setBounds(0,0,100,100);
                                return drawable;
                            }
                        },
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ////////////////  ImageSpan  //////////////////////
        length = builder.length();
        builder.append("这是要被替换成图片的安第斯你能拿到四点你似的");
        builder.append(System.lineSeparator());
        builder.setSpan(new ImageSpan(this,R.mipmap.ic_launcher,ALIGN_BOTTOM),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ////////////////  RelativeSizeSpan  //////////////////////
        length = builder.length();
        builder.append("这是要被替换成图片的安第斯你能拿到四点你似的");
        builder.append(System.lineSeparator());
        builder.setSpan(new ScaleXSpan(5),
                length, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        mTvContent.setText(builder);
    }
}

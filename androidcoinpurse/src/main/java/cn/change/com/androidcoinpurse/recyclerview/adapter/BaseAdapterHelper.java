package cn.change.com.androidcoinpurse.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * 适配器帮助类基类
 * Created by chang on 2016/2/26.
 */
public class BaseAdapterHelper extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public BaseAdapterHelper(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    /**
     * 取得View
     *
     * @param viewId the View 唯一标识
     * @param <T>    the View
     * @return the View
     */
    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 可以使用这个函数来自定义获取View的操作
     *
     * @param viewId the View 唯一标识
     * @param <T>    the View
     * @return the View
     */
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    /**
     * 给View设置背景色
     *
     * @param viewId the View 唯一标识
     * @param color  颜色值
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 给View设置背景图片
     *
     * @param viewId the View 唯一标识
     * @param resId  图片资源唯一标识
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setBackgroundRes(int viewId, int resId) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 给TextView设置文字
     *
     * @param viewId TextView 唯一标识
     * @param text   文字
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setText(int viewId, String text) {
        TextView textView = retrieveView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 给TextView设置文字颜色
     *
     * @param viewId the View 唯一标识
     * @param color  颜色值(数值)
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setTextColor(int viewId, int color) {
        TextView view = retrieveView(viewId);
        view.setTextColor(color);
        return this;
    }


    /**
     * 给TextView设置文字颜色
     *
     * @param viewId TextView唯一标识
     * @param color  颜色值(color资源Id)
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setTextColorRes(int viewId, int color) {
        TextView view = retrieveView(viewId);
        view.setTextColor(view.getResources().getColor(color));
        return this;
    }


    /**
     * 给ImageView设置图片
     *
     * @param viewId ImageView 唯一标识
     * @param resId  图片资源唯一标识
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setImageResource(int viewId, int resId) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 给ImageView设置图片
     *
     * @param viewId   ImageView 唯一标识
     * @param drawable Drawable
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * 给ImageView设置图片
     *
     * @param viewId ImageView 唯一标识
     * @param bitmap Bitmap
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 加载网络图片
     * @param context 上下文
     * @param viewId ImageView 唯一标识
     * @param imgUrl 图片网络地址
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setImageWithUrl(Context context, int viewId,String imgUrl){
        ImageView imageView = retrieveView(viewId);
        Picasso.with(context).load(imgUrl).into(imageView);
        return this;
    }

    /**
     * 设置透明度
     *
     * @param viewId the View 唯一标识
     * @param value  透明度 0-1
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(value, value);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            retrieveView(viewId).startAnimation(alphaAnimation);
        }
        return this;
    }

    /**
     * 设置显示与否
     *
     * @param viewId  the View 唯一标识
     * @param visible 是否显示 true VISIBLE , false GONE
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setVisible(int viewId, boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    // TODO 可以在这里用SpannableString添加一个文字超链接的函数

    /**
     * 设置文字超链接，电话，邮件等
     *
     * @param viewId TextView 唯一标识
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper linkify(int viewId) {
        TextView textView = retrieveView(viewId);
        Linkify.addLinks(textView, Linkify.ALL);
        return this;
    }

    /**
     * 设置字体
     *
     * @param viewId   TextView 唯一标识
     * @param typeface 字体
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setTypeface(int viewId, Typeface typeface) {
        TextView textView = retrieveView(viewId);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * 设置多个多个TextView字体相同字体
     *
     * @param typeface 字体
     * @param viewIds  多个TextView 唯一标识
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            setTypeface(viewId, typeface);
        }
        return this;
    }


    /**
     * 设置ProgressBar的进度值
     *
     * @param viewId   ProgressBar唯一标识
     * @param progress 进度值
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setProgress(int viewId, int progress) {
        ProgressBar progressBar = retrieveView(viewId);
        progressBar.setProgress(progress);
        return this;
    }

    /**
     * 设置ProgressBar的进度值
     *
     * @param viewId   ProgressBar唯一标识
     * @param progress 进度值
     * @param max      最大值
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setProgress(int viewId, int progress, int max) {
        ProgressBar progressBar = retrieveView(viewId);
        progressBar.setMax(max);
        progressBar.setProgress(progress);
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param viewId   the View 唯一标识
     * @param listener 点击监听
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     *
     * @param viewId   the View 唯一标识
     * @param listener 长按监听
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 设置tag
     *
     * @param viewId the View 唯一标识
     * @param obj    tag
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setTag(int viewId, Object obj) {
        View view = retrieveView(viewId);
        view.setTag(obj);
        return this;
    }

    /**
     * 设置check状态
     * @param viewId the View 唯一标识
     * @param checkable check状态
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setChecked(int viewId,boolean checkable){
        Checkable view = retrieveView(viewId);
        view.setChecked(checkable);
        return this;
    }

    /**
     * 设置适配器
     * @param viewId the View 唯一标识
     * @param adapter 适配器
     * @return BaseAdapterHelper
     */
    public BaseAdapterHelper setAdapter(int viewId, Adapter adapter){
        AdapterView view = retrieveView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * 获取ConvertView
     *
     * @return ConvertView
     */
    public View getConvertView() {
        return itemView;
    }

    public TextView getTextView(int viewId){
        return retrieveView(viewId);
    }

    public ImageView getImageView(int viewId){
        return retrieveView(viewId);
    }

    public Button getButton(int viewId){
        return retrieveView(viewId);
    }

}

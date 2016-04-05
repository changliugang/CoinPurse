package cn.change.com.androidcoinpurse.recyclerview.adapter;

/**
 * 多类型item支持接口
 * Created by chang on 2016/2/29.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

    int getCount();

}

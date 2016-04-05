package cn.change.com.androidcoinpurse.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * QuickAdapter的基类
 * Created by chang on 2016/2/26.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper>
        extends RecyclerView.Adapter<BaseAdapterHelper> implements OnClickListener {

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected Context context;

    protected int layoutResId;

    protected List<T> data;

    private OnItemClickListener mOnItemClickListener = null;
    // 流式布局相关
    private List<Integer> mHeights;
    private boolean isStaggered = false;
    private int minHeight = 100;
    private int heightOffset = 300;

    // FooterRefresh相关
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    private boolean isFooterRefresh;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;
    public static final int TYPE_ITEM = 0;  //普通Item View
    public static final int TYPE_FOOTER = 1;  //顶部FootView


    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.data = data == null ? new ArrayList<T>() : data;
        if (isShowWithStaggered())
        setRandomHeight(data == null ? 0 : data.size());
    }

    public BaseQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this(context, multiItemTypeSupport, null);
    }

    public BaseQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        this.context = context;
        this.multiItemTypeSupport = multiItemTypeSupport;
        this.data = data == null ? new ArrayList<T>() : data;
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data,int minHeight,int heightOffset) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.data = data == null ? new ArrayList<T>() : data;
        this.minHeight = minHeight;
        this.heightOffset = heightOffset;
        setRandomHeight(data == null ? 0 : data.size());
    }

    @Override
    public int getItemCount() {
        if (multiItemTypeSupport != null) {
            return multiItemTypeSupport.getCount();
        }
        return data.size();
    }

    public T getItem(int position) {
        if (position >= data.size()) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (multiItemTypeSupport != null) {
            return multiItemTypeSupport.getItemViewType(position, getItem(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (multiItemTypeSupport != null) {
            int layoutId = multiItemTypeSupport.getLayoutId(viewType);
            view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(layoutResId, parent, false);
        }
        view.setOnClickListener(this);
        BaseAdapterHelper bah = new BaseAdapterHelper(view);
        return bah;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper holder, int position) {
        holder.itemView.setTag(position);
        T item = getItem(position);
        if (isShowWithStaggered()) {

            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            lp.height = mHeights.get(position);
        }

        if (isFooterRefresh){
//            switch (load_more_status){
//                case PULLUP_LOAD_MORE:
//                    holder.setText(R.id.foot_view_item_tv,"上拉加载更多...");
////                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
//                    break;
//                case LOADING_MORE:
//                    holder.setText(R.id.foot_view_item_tv,"正在加载更多数据...");
////                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
//                    break;
//            }
        }

        convert((H) holder, item);
    }



    public boolean isShowWithStaggered() {
        return isStaggered;
    }

    private void setRandomHeight(int count) {
        mHeights = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mHeights.add((int) (minHeight + Math.random() * heightOffset));
        }
    }

    public void setStaggeredConfig(int minHeight, int heightOffset) {
        this.minHeight = minHeight;
        this.heightOffset = heightOffset;
    }

    public void isStaggered(boolean isStaggered) {
        this.isStaggered = isStaggered;
    }

    public void isFooterRefresh(boolean isFooterRefresh){
        this.isFooterRefresh = isFooterRefresh;
    }

    /**
     * 适配逻辑实现函数
     *
     * @param helper 适配器帮助类
     * @param item   适配对象
     */
    protected abstract void convert(H helper, T item);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void add(T item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> lists) {
        int startIndex = data.size();
        data.addAll(startIndex, lists);
        notifyItemRangeInserted(startIndex, lists.size());
    }

    public void replaceAll(List<T> lists) {
        data.clear();
        addAll(lists);
    }

    public void set(int index, T item) {
        data.set(index, item);
        notifyDataSetChanged();
    }

    public void set(T oldItem, T newItem) {
        set(data.indexOf(oldItem), newItem);
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        data.remove(item);
        notifyDataSetChanged();
    }

    public boolean contains(T item) {
        return data.contains(item);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

}

package dd.surfit.test.applicationdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dd.surfit.test.applicationdemo.ComponentListDS.ListComponent;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ListAdapter extends BaseAdapter {
    private final String TAG = "ListAdapter";
    ListComponent listComponent ;
    private LayoutInflater inflater;
    private Context context;
    private List<ListComponent> lc;

    public ListAdapter(ListComponent listComponent,Context context){
        this.listComponent = listComponent;
        this.lc = listComponent.getChildren();
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return lc.size();
    }

    @Override
    public Object getItem(int i) {
        return lc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.item_adapter_root_list,null);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.list_name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        if(lc != null && lc.size() > 0){
            final ListComponent component = lc.get(i);
            Log.d(TAG,lc.get(i) + "+++");
            holder.textView.setText(lc.get(i).getName());
            if(lc.get(i).getChildren() != null && lc.get(i).getChildren().size() >0) {
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list",component);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
            }else{

                    holder.textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (component.getActivity() != null) {
                                Toast.makeText(context, "跳到Activity", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, component.getActivity());
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, "未配置界面", Toast.LENGTH_SHORT).show();
                            }
                        }

                });
            }
        }


        return view;
    }

    class ViewHolder{
        TextView textView;
    }
}

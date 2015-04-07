package dd.surfit.test.applicationdemo.ComponentListDS;

import android.app.Activity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public abstract class ListComponent implements Serializable {

    public String name;

    public String describe;

    public List<ListComponent> children;

    public Class activity;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public ListComponent()
    {

    }

    public List<ListComponent> getChildren() {
        return children;
    }

    public void setChildren(List<ListComponent> children) {
        this.children = children;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }

    public String getName() {
        if(name == null){
            return "hahha";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void add(ListComponent c);
    public abstract void remove(ListComponent c);
    public abstract void display(int depth);
}

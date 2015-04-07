package dd.surfit.test.applicationdemo.ComponentListDS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ListComposite extends ListComponent {

    public ListComposite(){
        super();
        if (children == null)
        {
            children = new ArrayList<ListComponent>();
        }
    }

    @Override
    public void add(ListComponent c) {
        this.children.add(c);
    }

    @Override
    public void remove(ListComponent c) {
        this.children.remove(c);
    }

    @Override
    public void display(int depth) {

    }

}

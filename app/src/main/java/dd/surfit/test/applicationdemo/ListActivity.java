package dd.surfit.test.applicationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import dd.surfit.test.applicationdemo.ComponentListDS.ListComponent;
import dd.surfit.test.applicationdemo.ComponentListDS.ListComposite;
import dd.surfit.test.applicationdemo.ComponentListDS.ListLeaf;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ListActivity extends Activity {
    private final String TAG = "ListActivity";
    private ListComponent listComponent;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            listComponent = (ListComponent) bundle.getSerializable("list");
        }

        listAdapter = new ListAdapter(listComponent,ListActivity.this);
        listView.setAdapter(listAdapter);

    }
}

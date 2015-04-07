package dd.surfit.test.applicationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import dd.surfit.test.applicationdemo.ComponentListDS.ListComponent;
import dd.surfit.test.applicationdemo.ComponentListDS.ListComposite;
import dd.surfit.test.applicationdemo.ComponentListDS.ListLeaf;
import dd.surfit.test.applicationdemo.ComponentListDS.ListXMLPull;


public class MainActivity extends Activity {
    ListComponent rootlist = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        TextView textView = (TextView) findViewById(R.id.tv_hello);


        /*
        ListComponent rootlist1 = new ListComposite("root1");
        ListComponent rootlist2 = new ListComposite("root2");
        ListComponent leaf1 = new ListLeaf("leaf1");
        ListComponent leaf2 = new ListLeaf("leaf2");
        ListComponent leaf3 = new ListLeaf("leaf3");
        ListComponent leaf4 = new ListLeaf("leaf4");
        ListComponent leaf5 = new ListLeaf("leaf5");
        ListComponent leaf6 = new ListLeaf("leaf6");

        rootlist.add(rootlist1);
        rootlist.add(rootlist2);
        rootlist.add(leaf1);

        rootlist1.add(leaf2);
        rootlist1.add(leaf3);
        rootlist1.add(leaf4);
        rootlist1.add(leaf5);

        rootlist2.add(leaf6);*/
       //
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                Bundle bundle = new Bundle();
                try {
                    rootlist = ListXMLPull.initListComponent(MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                bundle.putSerializable("list",rootlist);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

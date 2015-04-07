package dd.surfit.test.applicationdemo.ComponentListDS;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ListXMLPull {

    ArrayList<ListComponent> list = new ArrayList<ListComponent>();
    static ListComposite rootList = null;
    public static ListComponent initListComponent(Context context) throws IOException, XmlPullParserException {

        ListComponent listLeaf = null;
        // 由android.util.Xml创建一个XmlPullParser实例
        XmlPullParser parser = Xml.newPullParser();
        // 设置输入流 并指明编码方式
        InputStream is = context.getAssets().open("root_list.xml");
        parser.setInput(is, "UTF-8");
        // 产生第一个事件
        int eventType = parser.getEventType();

        if(eventType!=XmlPullParser.END_DOCUMENT){//只要不是文档结束事件
            rootList = new ListComposite(); // 初始化rootList集合
            pull(eventType,rootList,parser);
        }
        return rootList;
    }

    private static ListComponent pull(int eventType,ListComponent rootList,XmlPullParser parser) throws IOException, XmlPullParserException{
        ListComponent listLeaf = null;
        int count = 0;
        while (!(eventType == XmlPullParser.END_TAG && "children".equals(parser.getName())) &&  eventType!=XmlPullParser.END_DOCUMENT) {
            Log.d("", eventType + parser.getName() + "");
            Log.d("",parser.getDepth() + "" + "-");
            switch (eventType) {
                // 判断当前事件是否为文档开始事件
                case XmlPullParser.START_DOCUMENT:
                    Log.d("pull","初始化rootList集合");
                    listLeaf = rootList;
                    //count==1 时 是根解析 需要特殊处理
                    count = 1;
                    break;

                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("ListComposite") && count != 1) { // 判断开始标签元素是否是listComposite
                        listLeaf = new ListComposite();
                        Log.d("pull","判断开始标签元素是否是listComposite");
                    } else if (parser.getName().equals("children")) { // 判断开始标签元素是否是children
                        Log.d("pull","判断开始标签元素是否是children");
                        eventType = parser.next();
                        pull(eventType, listLeaf, parser);
                        if (count != 1)
                        rootList.add(listLeaf); //
                        Log.d("pull","将listComposite添加到rootList集合");
                    } else if (parser.getName().equals("ListLeaf")) {
                        Log.d("pull","判断开始标签元素是否是ListLeaf");
                        listLeaf = new ListLeaf();
                    } else if (parser.getName().equals("describe")) {
                        Log.d("pull","判断开始标签元素是否是describe");
                        // 得到book标签的属性值，并设置book的id
                        eventType = parser.next();
                        listLeaf.setDescribe(parser.getText() == null ? "" : parser.getText());
                    } else if (parser.getName().equals("name")) { // 判断开始标签元素是否是name
                        Log.d("pull","判断开始标签元素是否是name");
                        eventType = parser.next();
                        listLeaf.setName(parser.getText() == null ? "" : parser.getText());
                    } else if (parser.getName().equals("activity")) { // 判断开始标签元素是否是name
                        Log.d("pull","判断开始标签元素是否是name");
                        eventType = parser.next();
                        if(parser.getText() != null) {
                            String s = "dd.surfit.test.applicationdemo." + parser.getText();
                            Class c = null;
                            try {
                                c = Class.forName(s);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            listLeaf.setActivity(c);
                        }
                    }
                    break;

                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("ListLeaf")) {
                        Log.d("pull","将listLeaf添加到rootList集合");
                        rootList.add(listLeaf); // 将listLeaf添加到rootList集合
                        listLeaf = null;
                    }
                    // 进入下一个元素并触发相应事件
                    break;
            }
            eventType = parser.next();
        }
        eventType = parser.next();
        listLeaf = null;
        return rootList;
    }
}

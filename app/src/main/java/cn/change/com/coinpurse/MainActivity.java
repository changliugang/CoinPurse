package cn.change.com.coinpurse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;

import cn.change.com.androidcoinpurse.adapter.BaseAdapterHelper;
import cn.change.com.androidcoinpurse.adapter.QuickAdapter;
import cn.change.com.coinpurse.acache.ACacheStringActivity;

public class MainActivity extends AppCompatActivity {

    ListView mainList;
    String[] items ={"ACacheString"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.main_listview);
        QuickAdapter<String> adapter = new QuickAdapter<String>(this,android.R.layout.simple_list_item_1,Arrays.asList(items)) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(android.R.id.text1,item);
            }
        };
        mainList.setAdapter(adapter);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,ACacheStringActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}

package cn.change.com.coinpurse.acache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.change.com.androidcoinpurse.cache.ACache;
import cn.change.com.coinpurse.R;

public class ACacheStringActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_1 = "String_content";

    EditText inputContent;
    TextView showContent;
    ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acache);

        mCache = ACache.get(this);

        inputContent = (EditText) findViewById(R.id.editText);
        Button confirm = (Button) findViewById(R.id.button);
        Button show = (Button) findViewById(R.id.button2);
        Button clear = (Button) findViewById(R.id.button3);
        showContent = (TextView) findViewById(R.id.textView);

        if (confirm != null)
            confirm.setOnClickListener(this);
        if (show != null)
            show.setOnClickListener(this);
        if (clear != null)
            clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String content = inputContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mCache.put(KEY_1, content);
                    Toast.makeText(ACacheStringActivity.this, "Cache:" + content, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ACacheStringActivity.this, "You have not specified a valid content.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                String cacheContent = mCache.getAsString(KEY_1);
                if (!TextUtils.isEmpty(cacheContent))
                    showContent.setText(cacheContent);
                else
                    Toast.makeText(ACacheStringActivity.this, "Nothing to show !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                mCache.remove(KEY_1);
                showContent.setText("");
                break;
        }
    }
}

/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.protobuf;

import java.io.IOException;
import java.io.InputStream;

import seker.common.BaseActivity;
import seker.training.TrainingApplication;
import seker.training.dataprocess.protobuf.ChannelProto.Channel;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class ProtoActivity extends BaseActivity {
    
    public static final String TAG = "google.json";
    
    public static final boolean LOG = TrainingApplication.GLOBAL_LOG & true;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            InputStream is = getAssets().open("data/data.pb");
            Channel channel = new ProtoParser().parse(is);
            if (LOG) {
                Log.i(TAG, channel+"");
            }
            
            ListView listview = new ListView(this);
            ChannelAdapter adapter = new ChannelAdapter(channel);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(adapter);
            setContentView(listview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

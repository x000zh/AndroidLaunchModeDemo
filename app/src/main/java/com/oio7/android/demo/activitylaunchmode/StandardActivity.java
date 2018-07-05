package com.oio7.android.demo.activitylaunchmode;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class StandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(this.getClass().getSimpleName());

        setContentView(R.layout.activity_main);

        refreshState();

        Button launchStandard = findViewById(R.id.launchStandard);
        launchStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StandardActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button launchSingleTop = findViewById(R.id.launchSingleTop);
        launchSingleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SingleTopActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button launchSingleTask = findViewById(R.id.launchSingleTask);
        launchSingleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SingleTaskActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button launchSingleInstance = findViewById(R.id.launchSingleInstance);
        launchSingleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SingleInstanceActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        Button launchFLAG_ACTIVITY_CLEAR_TOP = findViewById(R.id.launchIntentFLAG_ACTIVITY_CLEAR_TOP);
        launchFLAG_ACTIVITY_CLEAR_TOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(intent);
            }
        });

        Button launchFLAG_ACTIVITY_BROUGHT_TO_FRONT = findViewById(R.id.launchIntentFLAG_ACTIVITY_BROUGHT_TO_FRONT);
        launchFLAG_ACTIVITY_BROUGHT_TO_FRONT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                v.getContext().startActivity(intent);
            }
        });

        Button launchFLAG_ACTIVITY_SINGLE_TOP = findViewById(R.id.launchIntentFLAG_ACTIVITY_SINGLE_TOP);
        launchFLAG_ACTIVITY_SINGLE_TOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                v.getContext().startActivity(intent);
            }
        });

        Button launchFLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = findViewById(R.id.launchIntentFLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        launchFLAG_ACTIVITY_EXCLUDE_FROM_RECENTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void refreshState(){

        String text = String.format("Now Activity is %s[%s]\n Now Task Id is %d\n",
                this.getClass().getSimpleName(), toString(), getTaskId());

        ActivityManager m = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        while(itr.hasNext()){
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)itr.next();
            int id = runningTaskInfo.id;
            CharSequence desc= runningTaskInfo.description;
            int numOfActivities = runningTaskInfo.numActivities;
            String topActivity = runningTaskInfo.topActivity.getShortClassName();
            text += String.format("-- Task [%d] has %d Activity [top: %s] - %s\n",
                    id, numOfActivities, topActivity, desc );
        }

        TextView message = findViewById(R.id.message);

        String old = message.getText().toString();

        if( "".equals(old) || null == old ){
            old = "{ empty }\n";
        }

        message.setText(text + "==== onNewIntent Trigger ====\n" + old);
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        refreshState();
    }
}

package com.example.nut.realmspeedtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nut.realmspeedtest.models.Person;
import com.example.nut.realmspeedtest.stopwatch.StopWatch;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private Realm realm;
    private RealmConfiguration realmConfiguration;
    private StopWatch stopWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRealm();
        initStopWatch();
        queryLargeData();
    }

    private void initStopWatch() {
        stopWatch = new StopWatch();
    }

    private void queryLargeData() {
        showStatus("StartQuery");
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                stopWatch.start();
                for(int i = 0; i < 10000; ++i) {
                    Person person = realm.createObject(Person.class);
                    person.setName("Person No."+i);
                    person.setAge(i);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                stopWatch.stop();
                showStatus(String.format("Spend time: %.2f second(s).",stopWatch.getElapsed()));
                RealmResults<Person> results = realm.where(Person.class).findAll();
                showStatus(String.format("Number of rows is %d.", results.size()));
                realm.close();
            }
        });
    }

    private void initRealm() {
        realmConfiguration = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void showStatus(String txt) {
        Log.e(TAG, txt);
    }


}

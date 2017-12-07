package com.acwlj.demo.ui.library;

import android.os.Bundle;

import com.acwlj.demo.R;
import com.acwlj.demo.model.realm.Dog;
import com.acwlj.demo.model.realm.Person;
import com.acwlj.demo.ui.common.BaseActivity;
import io.realm.Realm;

/**
 * @author dong on 16/1/7.
 */
public class RealmActivity extends BaseActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_realm;
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        super.process(savedInstanceState);

        realm.beginTransaction();

        Dog dog = new Dog();
        dog.setName("dong");

        Person person = new Person();
        person.setDog(dog);

        realm.copyToRealm(person);

        realm.commitTransaction();
    }
}

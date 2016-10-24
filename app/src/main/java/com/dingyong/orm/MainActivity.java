package com.dingyong.orm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtn,mButtonQuery,mButtonQueryList,mButtonQueryAll,mButtonDelete,mButtonUpdate;
    private UserDao mUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button) findViewById(R.id.btn_create);
        mButtonQuery = (Button) findViewById(R.id.btn_query);
        mButtonQueryList = (Button) findViewById(R.id.btn_query_list);
        mButtonQueryAll = (Button) findViewById(R.id.btn_query_list_all);
        mButtonDelete = (Button) findViewById(R.id.btn_delete);
        mButtonUpdate = (Button) findViewById(R.id.btn_update);
        mBtn.setOnClickListener(this);
        mButtonQuery.setOnClickListener(this);
        mButtonQueryList.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
        mButtonQueryAll.setOnClickListener(this);
        mUserDao = new UserDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                for (int i = 10 ;i<20 ;i++){
                    User user = new User(i,"周杰伦"+i,"北京"+i,i%2==0?"男":"女");
                    mUserDao.addUser(user);
                }
                break;
            case R.id.btn_query:
                User user = mUserDao.queryUserById(10);
                Log.d("====>","query:"+user.toString());
                break;
            case R.id.btn_query_list:
                List<User> userList = mUserDao.queryWhereLike("周杰伦","男");
                for (User u: userList){
                    Log.d("====>","query:"+u.toString());
                }
                break;
            case R.id.btn_delete:
                mUserDao.delete("周杰>>>>伦");
                break;
            case R.id.btn_update:
                mUserDao.update("昆凌","女");
                break;
            case R.id.btn_query_list_all:
                List<User> listAll =   mUserDao.queryUserAll();
                for (User u: listAll){
                    Log.d("====>","query:"+u.toString());
                }
                break;

        }
    }
}

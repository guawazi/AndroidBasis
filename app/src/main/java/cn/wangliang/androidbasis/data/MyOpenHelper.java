package cn.wangliang.androidbasis.data;

import android.content.Context;

import cn.wangliang.androidbasis.data.database.dao.DaoMaster;

public class MyOpenHelper extends DaoMaster.OpenHelper{

    public MyOpenHelper(Context context, String name){
        super(context,name);
    }
}
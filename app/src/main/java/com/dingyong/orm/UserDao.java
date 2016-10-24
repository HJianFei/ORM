package com.dingyong.orm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class UserDao {
    private Context mContext;
    private DbHelp mDbHelp;
    private Dao<User,Integer> mUserDao;

    public UserDao(Context context) {
        mContext = context;
        mDbHelp = DbHelp.getmInstace(mContext);
        try {
            mUserDao = mDbHelp.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     * @param user
     */
    public void addUser(User user){
        try {
            mUserDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    public User queryUserById(int id){
        try {
            User user = mUserDao.queryForId(id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有的的的数据
     * @return
     */
    public List<User> queryUserAll(){
        try {
            List<User> users = mUserDao.queryForAll();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 匹配查询
     * SELECT * FROM tb_user   WHERE name='' AND sex = '';
     * @param name
     * @param sex
     * @return
     */
    public  List<User> queryWhereEq(String name,String sex){
        QueryBuilder queryBuilder = mUserDao.queryBuilder();
        try {
            List<User> userList = queryBuilder.where().eq("name",name).and().eq("sex",sex).query();
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * like查询
     * @param name
     *
     * @param sex
     * @return
     */
    public  List<User> queryWhereLike(String name,String sex){
        QueryBuilder queryBuilder = mUserDao.queryBuilder();
        try {
            List<User> userList = queryBuilder.where().eq("sex",sex).query();
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id删除数据
     * */
    public int delete(int id){
        try {
            return mUserDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1000;
    }

    /**
     * 删除name = name的数据,
     * @param name
     */
    public void delete(String name){
        DeleteBuilder deleteBuilder = mUserDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("name",name);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据
     * @param name
     * @param sex
     */
    public void update(String name,String sex){
        UpdateBuilder updateBuilder = mUserDao.updateBuilder();
        try {
            updateBuilder.updateColumnValue("name",name).where().eq("sex",sex);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

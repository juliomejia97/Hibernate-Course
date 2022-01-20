package com.infiniteskills.data.dao;

import com.infiniteskills.data.dao.interfaces.UserDao;
import com.infiniteskills.data.entities.User;

import java.util.List;

public class UserHibernateDao extends AbstractDao<User,Long> implements UserDao {
    @Override
    public List<User> findByFirstName(String Name) {
        return null;
    }
}

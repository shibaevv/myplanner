package com.argus.financials.dao.hibernate;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.argus.financials.dao.UserDao;
import com.argus.financials.domain.hibernate.User;

/**
 * @author vchibaev (Valeri SHIBAEV)
 */
@Repository
public class UserDaoImpl extends BaseDAOImpl implements UserDao
{

    /* (non-Javadoc)
     * @see com.argus.financials.dao.UserDao#findByLogin(java.lang.String)
     */
    public User findByLogin(String login)
    {
        try
        {
            User user = (User) getEntityManager()
                .createQuery("FROM User WHERE login = :login AND password IS NULL")
                .setParameter("login", login)
                .getSingleResult();
            return user;
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (EntityNotFoundException e)
        {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.argus.financials.dao.UserDao#findByLoginPassword(java.lang.String, java.lang.String)
     */
    public User findByLoginPassword(String login, String password)
    {
        try
        {
            User user = (User) getEntityManager()
                .createQuery("FROM User WHERE login = :login AND password = :password")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
            return user;
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (EntityNotFoundException e)
        {
            return null;
        }
    }

}
package au.com.totemsoft.myplanner.utils;

import java.util.Date;

import au.com.totemsoft.myplanner.domain.hibernate.User;

/**
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */
public final class ThreadLocalUtils
{

    /** ThreadLocal storage for date. */
    private static final ThreadLocal<Date> dateStorage = new ThreadLocal<Date>();

    /** ThreadLocal storage for user. */
    private static final ThreadLocal<User> userStorage = new ThreadLocal<User>();

    /**
     * hide ctor.
     */
    private ThreadLocalUtils()
    {
        super();
    }

    /**
     * @return thread safe Date
     */
    public static Date getDate()
    {
        synchronized (dateStorage)
        {
            return dateStorage.get();
        }
    }

    /**
     * Set new thread safe Date.
     * @param date
     */
    public static void setDate(final Date date)
    {
        synchronized (dateStorage)
        {
            dateStorage.set(date);
        }
    }

    /**
     * Clear date storage.
     */
    public static void clearDate()
    {
        setDate(null);
    }

    /**
     * @return thread safe User
     */
    public static User getUser()
    {
        synchronized (userStorage)
        {
            return userStorage.get();
        }
    }

    /**
     * Set new thread safe User.
     * @param date
     */
    public static void setUser(final User user)
    {
        synchronized (userStorage)
        {
            userStorage.set(user);
        }
    }

    /**
     * Clear user storage.
     */
    public static void clearUser()
    {
        setUser(null);
    }

    /**
     * Clear thread storage.
     */
    public static void clear()
    {
        setDate(null);
        setUser(null);
    }

}
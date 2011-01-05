package com.argus.financials.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name = "UserPerson")
@PrimaryKeyJoinColumn(name = "UserPersonID")
@Where(clause = "ActiveUser IS NULL OR ActiveUser = 'Y'")
public class User extends Person
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1526914584867529644L;

    @Column(name = "AdviserTypeCodeID", nullable = false)
    private Integer typeId;

    @Column(name = "LoginName", nullable = false)
    private String login;

    @Column(name = "LoginPassword")
    private String password;

//  @Column(name = "ActiveUser")
//  @Type(type = "yes_no")
//  private Boolean active;

    /**
     * @return the typeId
     */
    public Integer getTypeId()
    {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /* (non-Javadoc)
     * @see com.argus.financials.domain.hibernate.AbstractBase#toString()
     */
    @Override
    public String toString()
    {
        return getLogin();
    }

}
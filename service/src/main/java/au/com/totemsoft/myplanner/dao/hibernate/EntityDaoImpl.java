package au.com.totemsoft.myplanner.dao.hibernate;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import au.com.totemsoft.myplanner.api.bean.ICountry;
import au.com.totemsoft.myplanner.api.dao.EntityDao;
import au.com.totemsoft.myplanner.domain.hibernate.refdata.Country;
import au.com.totemsoft.myplanner.domain.hibernate.refdata.MaritalCode;
import au.com.totemsoft.myplanner.domain.hibernate.refdata.SexCode;
import au.com.totemsoft.myplanner.domain.hibernate.refdata.State;
import au.com.totemsoft.myplanner.domain.hibernate.refdata.TitleCode;

/**
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */
@Repository
public class EntityDaoImpl extends BaseDAOImpl implements EntityDao
{

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.api.dao.EntityDao#findCountry(java.lang.Integer)
     */
    @Override
    public ICountry findCountry(Integer id) {
        return findById(Country.class, id);
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findCountries()
     */
    @SuppressWarnings("unchecked")
    public List<Country> findCountries()
    {
        return getEntityManager()
            .createQuery("FROM Country ORDER BY description")
            .getResultList();
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findStates(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<State> findStates(Integer countryId)
    {
        if (ICountry.AUSTRALIA_ID.equals(countryId))
        {
            return getEntityManager()
                .createQuery("FROM State ORDER BY description")
                //.setParameter("countryId", countryId) // TODO: add countryId column
                .getResultList();
        }
        return Collections.emptyList();
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findSexCode(java.lang.Integer)
     */
    @Override
    public SexCode findSexCode(Integer id) {
        return findById(SexCode.class, id);
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.api.dao.EntityDao#findSexCode(java.lang.String)
     */
    @Override
    public SexCode findSexCode(String codeDesc) {
        try {
            return (SexCode) getEntityManager()
                .createQuery("FROM SexCode WHERE description = :codeDesc")
                .setParameter("codeDesc", codeDesc)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findMaritalCode(java.lang.Integer)
     */
    @Override
    public MaritalCode findMaritalCode(Integer id) {
        return findById(MaritalCode.class, id);
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.api.dao.EntityDao#findMaritalCode(java.lang.String)
     */
    @Override
    public MaritalCode findMaritalCode(String codeDesc) {
        try {
            return (MaritalCode) getEntityManager()
                .createQuery("FROM MaritalCode WHERE description = :codeDesc")
                .setParameter("codeDesc", codeDesc)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findMaritalCodes()
     */
    @SuppressWarnings("unchecked")
    public List<MaritalCode> findMaritalCodes()
    {
        return getEntityManager()
            .createQuery("FROM MaritalCode ORDER BY description")
            .getResultList();
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findTitleCode(java.lang.Integer)
     */
    @Override
    public TitleCode findTitleCode(Integer id) {
        return findById(TitleCode.class, id);
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.api.dao.EntityDao#findTitleCode(java.lang.String)
     */
    @Override
    public TitleCode findTitleCode(String codeDesc) {
        try {
            return (TitleCode) getEntityManager()
                .createQuery("FROM TitleCode WHERE description = :codeDesc")
                .setParameter("codeDesc", codeDesc)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see au.com.totemsoft.myplanner.dao.EntityDao#findTitleCodes()
     */
    @SuppressWarnings("unchecked")
    public List<TitleCode> findTitleCodes()
    {
        return getEntityManager()
            .createQuery("FROM TitleCode ORDER BY description")
            .getResultList();
    }

}
package com.argus.financials.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.argus.financials.config.FPSLocale;
import com.argus.financials.dao.EntityDao;
import com.argus.financials.domain.hibernate.refdata.Country;
import com.argus.financials.domain.hibernate.refdata.MaritalCode;
import com.argus.financials.domain.hibernate.refdata.SexCode;
import com.argus.financials.domain.hibernate.refdata.State;
import com.argus.financials.domain.hibernate.refdata.TitleCode;
import com.argus.financials.domain.util.CountryComparator;
import com.argus.financials.service.client.EntityService;

public class EntityServiceImpl implements EntityService
{

    @Autowired
    private EntityDao entityDao;

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findCountries()
     */
    public List<Country> findCountries()
    {
        List<Country> result = entityDao.findCountries();
        String defaultCountry = FPSLocale.getInstance().getDisplayCountry();
        Collections.sort(result, new CountryComparator(defaultCountry));
        return result;
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findMaritalCode(java.lang.Long)
     */
    public MaritalCode findMaritalCode(Long id)
    {
        return entityDao.findById(MaritalCode.class, id);
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findMaritalCodes()
     */
    public List<MaritalCode> findMaritalCodes()
    {
        List<MaritalCode> result = entityDao.findMaritalCodes();
        return result;
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findSexCode(java.lang.Long)
     */
    public SexCode findSexCode(Long id)
    {
        return entityDao.findById(SexCode.class, id);
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findTitleCode(java.lang.Long)
     */
    public TitleCode findTitleCode(Long id)
    {
        return entityDao.findById(TitleCode.class, id);
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findTitleCodes()
     */
    public List<TitleCode> findTitleCodes()
    {
        List<TitleCode> result = entityDao.findTitleCodes();
        return result;
    }

    /* (non-Javadoc)
     * @see com.argus.financials.service.EntityService#findStates(java.lang.Integer)
     */
    public List<State> findStates(Integer countryId)
    {
        List<State> result = entityDao.findStates(countryId);
        return result;
    }

}
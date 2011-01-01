package com.argus.financials.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.argus.financials.dao.EntityDao;
import com.argus.financials.domain.hibernate.refdata.Country;
import com.argus.financials.domain.hibernate.refdata.Country.CountryComparator;
import com.argus.financials.service.EntityService;

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
        Collections.sort(result, new CountryComparator());
        return result;
    }

}
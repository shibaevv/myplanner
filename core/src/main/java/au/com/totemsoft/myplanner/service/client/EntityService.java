package au.com.totemsoft.myplanner.service.client;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.totemsoft.myplanner.api.bean.ICountry;
import au.com.totemsoft.myplanner.api.bean.IMaritalCode;
import au.com.totemsoft.myplanner.api.bean.ISexCode;
import au.com.totemsoft.myplanner.api.bean.IState;
import au.com.totemsoft.myplanner.api.bean.ITitleCode;

@Service
public interface EntityService
{

    List<ICountry> findCountries();

    List<IState> findStates(Integer countryId);

    IMaritalCode findMaritalCode(Integer id);
    IMaritalCode findMaritalCode(String codeDesc);
    List<IMaritalCode> findMaritalCodes();

    ISexCode findSexCode(Integer id);
    ISexCode findSexCode(String codeDesc);

    ITitleCode findTitleCode(Integer id);
    ITitleCode findTitleCode(String codeDesc);
    List<ITitleCode> findTitleCodes();

}
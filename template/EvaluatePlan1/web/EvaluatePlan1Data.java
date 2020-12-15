package com.tav.web.data;

import com.tav.web.bo.ServiceResult;
import com.tav.web.common.Config;
import com.tav.web.common.RestRequest;
import com.tav.web.dto.EvaluatePlan1DTO;
import com.tav.web.dto.SearchCommonFinalDTO;
import com.tav.web.dto.ObjectCommonSearchDTO;
import java.util.List;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvaluatePlan1Data {
	protected static final Logger logger = Logger.getLogger(EvaluatePlan1Data.class);
	private static final String subUrl = "/evaluatePlan1RsServiceRest";

	@Autowired
	private Config config;

	// get all
	public List<EvaluatePlan1DTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		String url = config.getRestURL() + subUrl + "/getAll/" + offset + "/" + limit;
		try {
			List<EvaluatePlan1DTO> jsResult = RestRequest.postAndReturnObjectArray(url, searchDTO, EvaluatePlan1DTO.class);
			if (jsResult == null) {
				return null;
			} else {
				return jsResult;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	//get count
	public Integer getCount(SearchCommonFinalDTO searchDTO) {
		String url = config.getRestURL() + subUrl + "/getCount";
		return (Integer) RestRequest.postAndReturnObject(url, searchDTO, Integer.class);
	}

	public ServiceResult addObj(EvaluatePlan1DTO cbChaDTO) {
		String url = config.getRestURL() + subUrl + "/addDTO/";
		ServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, cbChaDTO, ServiceResult.class);
		return result;
	}
	public ServiceResult updateBO(EvaluatePlan1DTO cbChaDTO) {
		String url = config.getRestURL() + subUrl + "/updateBO/";
		ServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, cbChaDTO, ServiceResult.class);
		return result;
	}

	public ServiceResult deleteObj(ObjectCommonSearchDTO objectCommonSearchDTO) {
		String url = config.getRestURL() + subUrl + "/deleteList/";
		ServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url, objectCommonSearchDTO, ServiceResult.class);
		return result;
	}

	public EvaluatePlan1DTO getOneById(Long id) {
		String url = config.getRestURL() + subUrl + "/getOneById/" + id;
		EvaluatePlan1DTO item = (EvaluatePlan1DTO) RestRequest.getObject(url, EvaluatePlan1DTO.class);
		return item;
	}


}
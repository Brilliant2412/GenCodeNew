package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.EvaluatePlan1BO;
import com.tav.service.common.Constants;
import com.tav.service.dao.EvaluatePlan1DAO;
import com.tav.service.dao.ObjectReationDAO;
import com.tav.service.dto.EvaluatePlan1DTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("evaluatePlan1BusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EvaluatePlan1BusinessImpl extends
		BaseFWBusinessImpl<EvaluatePlan1DAO, EvaluatePlan1DTO, EvaluatePlan1BO> implements EvaluatePlan1Business {

	@Autowired
	private EvaluatePlan1DAO evaluatePlan1DAO;

	@Override
	public EvaluatePlan1DAO gettDAO() { return evaluatePlan1DAO; }

	public List<EvaluatePlan1DTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
		List<EvaluatePlan1DTO> lstDTO = evaluatePlan1DAO.getAll(searchDTOTmp, offset, limit);
		return lstDTO;
	}

	public Integer getCount(SearchCommonFinalDTO searchDTO) { return evaluatePlan1DAO.getCount(searchDTO); }

	//GET ONE
	public EvaluatePlan1DTO getOneObjById(Long gid) {
		EvaluatePlan1DTO dto = evaluatePlan1DAO.getOneObjById(gid);
		return dto;
	}

	//add
	public ServiceResult addDTO(EvaluatePlan1DTO evaluatePlan1DTO) {
		EvaluatePlan1BO bo = evaluatePlan1DAO.addDTO(evaluatePlan1DTO);
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setId(bo.getGid());
		return serviceResult;
	}

	//update
	public ServiceResult updateObj(EvaluatePlan1DTO evaluatePlan1DTO) {
		ServiceResult result;
		EvaluatePlan1BO bo = evaluatePlan1DAO.addDTO(evaluatePlan1DTO);
		result = new ServiceResult();
		return result;
	}

	//delete
	public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = evaluatePlan1DAO.deleteList(searchDTO.getLstFirst());
		return result;
	}

}
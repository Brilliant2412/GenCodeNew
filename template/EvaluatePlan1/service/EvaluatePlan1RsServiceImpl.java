package com.tav.service.rest;

import com.tav.service.business.EvaluatePlan1BusinessImpl;
import com.tav.service.dto.EvaluatePlan1DTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class EvaluatePlan1RsServiceImpl implements EvaluatePlan1RsService{

	@Autowired
	private EvaluatePlan1BusinessImpl evaluatePlan1BusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<EvaluatePlan1DTO> lst = evaluatePlan1BusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = evaluatePlan1BusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		EvaluatePlan1DTO result = evaluatePlan1BusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = evaluatePlan1BusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(EvaluatePlan1DTO evaluatePlan1DTO) {
		ServiceResult result = evaluatePlan1BusinessImpl.updateObj(evaluatePlan1DTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(EvaluatePlan1DTO evaluatePlan1DTO) {
		ServiceResult result = evaluatePlan1BusinessImpl.addDTO(evaluatePlan1DTO);
		return Response.ok(result).build();
	}

}
package com.tav.service.rest;

import com.tav.service.dto.EvaluatePlan1DTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface EvaluatePlan1RsService {
	@POST
	@Path("/getAll/{offset}/{limit}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll(SearchCommonFinalDTO searchDTO, @PathParam("offset") Integer offset, @PathParam("limit") Integer limit);

	@POST
	@Path("/getCount")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCount(SearchCommonFinalDTO searchDTO);

	@GET
	@Path("/getOneById/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getOneById(@PathParam("id") Long id);

	@POST
	@Path("/deleteList/")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteList(ObjectCommonSearchDTO searchDTO);

	@POST
	@Path("/updateBO/")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateObj(EvaluatePlan1DTO evaluatePlan1DTO);

	@POST
	@Path("/addDTO/")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response addDTO(EvaluatePlan1DTO evaluatePlan1DTO);
}
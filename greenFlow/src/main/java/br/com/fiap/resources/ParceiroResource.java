package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;



import br.com.fiap.exceptions.NotFoundException;
import br.com.fiap.model.Parceiro;
import br.com.fiap.services.ParceiroBO;

@Path("/parceiro")
public class ParceiroResource {

	private ParceiroBO parceiroBO = new ParceiroBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Parceiro> buscarTodosParceiros() throws ClassNotFoundException, SQLException {
		return (ArrayList<Parceiro>) parceiroBO.buscarTodosParceiros();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarParceiroPorId(@PathParam("id") Long id) {

		try {
			Parceiro parceiro = parceiroBO.buscarParceiroPorId(id);

			return Response.ok(parceiro).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirParceiro(Parceiro parceiro, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException {

		parceiroBO.inserirParceiro(parceiro);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder(); // recebe a informação do front (página)
		builder.path(Long.toString(parceiro.getId())); // identifica o id do campo (String)

		return Response.created(builder.build()).build(); // composição -> caminho (carrregar o que foi carregado) -
															// http -> 200
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarParceiro(Parceiro parceiro, @PathParam("id") Long id) {

		try {

			return Response.ok(parceiroBO.atualizarParceiro(id, parceiro)).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	public Response deletarParceiro(@PathParam("id") Long id) {
		try {
			return Response.ok(parceiroBO.deletarParceiro(id)).build();

		}

		catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

}
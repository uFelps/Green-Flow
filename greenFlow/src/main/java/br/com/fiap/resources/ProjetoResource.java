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
import br.com.fiap.model.Projeto;
import br.com.fiap.services.ProjetoBO;

@Path("/projeto")
public class ProjetoResource {

	private ProjetoBO projetoBO = new ProjetoBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Projeto> buscarTodosProjetos() throws ClassNotFoundException, SQLException {
		return (ArrayList<Projeto>) projetoBO.buscarTodosProjetos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProjetoPorId(@PathParam("id") Long id) {

		try {
			Projeto projeto = projetoBO.buscarProjetoPorId(id);

			return Response.ok(projeto).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirProjeto(Projeto projeto, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException {

		projetoBO.inserirProjeto(projeto);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder(); // recebe a informação do front (página)
		builder.path(Long.toString(projeto.getId())); // identifica o id do campo (String)

		return Response.created(builder.build()).build(); // composição -> caminho (carrregar o que foi carregado) -
															// http -> 200
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarProjeto(Projeto projeto, @PathParam("id") Long id) {

		try {

			return Response.ok(projetoBO.atualizarProjeto(id, projeto)).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	public Response deletarProjeto(@PathParam("id") Long id) {
		try {
			return Response.ok(projetoBO.deletarProjeto(id)).build();

		}

		catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

}
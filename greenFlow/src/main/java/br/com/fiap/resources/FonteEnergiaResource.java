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
import br.com.fiap.model.FonteEnergia;
import br.com.fiap.model.Parceiro;
import br.com.fiap.services.FonteEnergiaBO;
import br.com.fiap.services.ParceiroBO;

@Path("/fonteEnergia")
public class FonteEnergiaResource {

	private FonteEnergiaBO fonteEnergiaBO = new FonteEnergiaBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FonteEnergia> buscarTodasFontesEnergias() throws ClassNotFoundException, SQLException {
		return (ArrayList<FonteEnergia>) fonteEnergiaBO.buscarTodasFontesEnergia();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarFonteEnergiaPorId(@PathParam("id") Long id) {

		try {
			FonteEnergia fonteEnergia = fonteEnergiaBO.buscarFonteEnergiaPorId(id);

			return Response.ok(fonteEnergia).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirFonteEnergia(FonteEnergia fonteEnergia, @Context UriInfo uriInfo)
			throws ClassNotFoundException, SQLException {
		//COLOCAR TRY CATCH
		fonteEnergiaBO.inserirFonteEnergia(fonteEnergia);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Long.toString(fonteEnergia.getId())); 

		return Response.created(builder.build()).build(); 
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarFonteEnergia(FonteEnergia fonteEnergia, @PathParam("id") Long id) {

		try {

			return Response.ok(fonteEnergiaBO.atualizarFonteEnergia(id, fonteEnergia)).build();

		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	public Response deletarFonteEnergia(@PathParam("id") Long id) {
		try {
			return Response.ok(fonteEnergiaBO.deletarFonteEnergia(id)).build();

		}

		catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		catch (ClassNotFoundException | SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

	}

}
package br.edu.unyleya.elton.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.edu.unyleya.elton.model.Empresa;
import br.edu.unyleya.elton.servico.EmpresaService;
import br.edu.unyleya.elton.servico.Response;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EmpresasResource {
	private final EmpresaService empresaService = new EmpresaService();

	@GET
	public List<Empresa> get() {
		List<Empresa> empresas = empresaService.getEmpresas();
		return empresas;
	}

	@GET
	@Path("{id}")
	public Empresa get(@PathParam("id") long id) {
		Empresa c = empresaService.getEmpresa(id);
		return c;
	}

	
	@GET
	@Path("/nome/{nome}")
	public List<Empresa> getByNome(@PathParam("nome") String nome) {
		List<Empresa> empresas = empresaService.findByName(nome);
		return empresas;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		empresaService.delete(id);
		return Response.Ok("Empresa deletada com sucesso");
	}

	@POST
	public Response post(Empresa c) {
		empresaService.save(c);
		return Response.Ok("Empresa salva com sucesso");
	}

	@PUT
	public Response put(Empresa c) {
		empresaService.save(c);
		return Response.Ok("Empresa atualizada com sucesso");
	}
}

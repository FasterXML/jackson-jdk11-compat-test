package com.fasterxml.jackson.compat11.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("hello")
@Produces("application/json")
public class HelloResource
{
	@GET
	@Path("helloObject/{name}")
	public ReturnableObject helloObject(@PathParam("name") final String name)
	{
		return new ReturnableObject().setName(name);
	}

}

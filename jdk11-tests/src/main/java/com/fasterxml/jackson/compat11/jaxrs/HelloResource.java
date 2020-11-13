package com.fasterxml.jackson.compat11.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

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

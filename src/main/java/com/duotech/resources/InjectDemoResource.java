package com.duotech.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param" ) String matrixParam,
                                            @HeaderParam("authSessionID") String header,
                                            @CookieParam("name") String cookie){
        return "Matrix param: " + matrixParam +" Header Param: " + header + " Cookie " + cookie;
    }

    @GET
    @Path("context")
    public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeader){
        String path = uriInfo.getAbsolutePath().toString();
        String cookie = httpHeader.getCookies().toString();
        return "path: "+path+" Cookie: "+cookie;
    }
}

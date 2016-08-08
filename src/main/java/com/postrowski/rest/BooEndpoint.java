package com.postrowski.rest;

import com.postrowski.Boo;
import com.postrowski.BooService;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by postrowski on 2016-08-02.
 */
@Path( "/boo" )
public class BooEndpoint
{
    @Inject
    private BooService booService;

    @GET
    public Response simpleGet()
    {
        return Response.ok( "BooEndpoint" ).build();
    }

/*    @GET
    @Path( "/import" )
    public Response startImport()
    {
        final JobOperator jobOperator = BatchRuntime.getJobOperator();
        final long jobId = jobOperator.start( "fooJob", new Properties() );

        return Response.ok( "Done" + jobId ).build();
    }*/

    @GET
    @Path( "/find/{id}" )
    public Response find( @PathParam( "id" ) long id )
    {
        final Boo boo = booService.find( id );
        return Response.ok( " FINDED " + boo.getId() ).build();
    }

    @GET
    @Path( "/create" )
    public Response create()
    {
        final Boo boo = new Boo();
        boo.setName( "Created " + LocalDateTime.now().toString() );

        booService.persist( boo );

        return Response.ok( " CREATED " + boo.getId() ).build();
    }


    @GET
    @Path( "/update/{id}" )
    public Response update( @PathParam( "id" ) String id )
    {
        final Boo boo = new Boo();
        boo.setId( id );
        boo.setName( "UPDATED " +  LocalDateTime.now().toString() );
        boo.setVersion( 0L );

        final Boo update = booService.update( boo );

        return Response.ok( " UPDATED " + update.getId() ).build();
    }
}

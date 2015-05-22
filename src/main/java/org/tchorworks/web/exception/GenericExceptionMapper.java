package org.tchorworks.web.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception ex) {
        LOGGER.error("Error occurred.", ex);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(ex.getMessage()).
                build();
    }
}

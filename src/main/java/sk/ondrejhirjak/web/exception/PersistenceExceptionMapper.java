package sk.ondrejhirjak.web.exception;

import org.apache.ibatis.exceptions.PersistenceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).
                entity(exception.getMessage()).
                build();
    }
}

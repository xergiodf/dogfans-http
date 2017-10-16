package com.shawandpartners.dogfans.http;

import com.shawandpartners.dogfans.http.dao.FavoriteDao;
import com.shawandpartners.dogfans.http.dao.RestDao;
import com.shawandpartners.dogfans.http.dto.Breed;
import com.shawandpartners.dogfans.http.dto.ErrorResponse;
import com.shawandpartners.dogfans.http.enums.ErrorType;
import com.shawandpartners.dogfans.http.model.Favorite;
import com.shawandpartners.dogfans.http.util.Constants;
import com.shawandpartners.dogfans.http.util.converter.BreedConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xergio
 * @version 1 - 13.10.2017
 */
@Path("breeds")
@Api(value = "/breeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BreedController {

    private static final Logger LOG = Logger.getLogger("BreedController");

    @Inject RestDao restDao;

    @Inject FavoriteDao favoriteDao;

    @GET
    @ApiOperation(value = "A list of breeds")
    public Response findAllBreed() {
        return findBreed("all");
    }

    @GET
    @Path("{breed}")
    @ApiOperation(value = "A list of breeds")
    public Response findAllBreed(@PathParam("breed") String name) {
        return findBreed(name);
    }

    private Response findBreed(String name) {
        try {
            List<Breed> breeds = restDao.getBreeds(name);
            List<Favorite> favorites = favoriteDao.findAll();

            if (breeds.isEmpty())
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse(String.format(Constants.MSG_ERROR_NOTFOUND, name), ErrorType.APPLICATION)).build();

            return Response.ok(BreedConverter.getInstance().getBreedWrappers(breeds, favorites)).build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(Constants.MSG_ERROR_DEFAULT, ErrorType.APPLICATION)).build();
        }
    }

    @POST
    @Path("{breed}/favorite")
    @ApiOperation(value = "Set a breed as favorite")
    public Response favBreed(@PathParam("breed") String breedName) {
        try {
            List<Breed> breeds = restDao.getBreeds(breedName);

            if (breeds.isEmpty())
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse(String.format(Constants.MSG_ERROR_NOTFOUND, breedName), ErrorType.APPLICATION)).build();

            favoriteDao.save(breedName);

            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(Constants.MSG_ERROR_DEFAULT, ErrorType.VALIDATION)).build();
        }
    }

    @DELETE
    @Path("{breed}/favorite")
    @ApiOperation(value = "Set a breed as favorite")
    public Response remFavBreed(@PathParam("breed") String breedName) {
        try {
            List<Breed> breeds = restDao.getBreeds(breedName);

            if (breeds.isEmpty())
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse(String.format(Constants.MSG_ERROR_NOTFOUND, breedName), ErrorType.APPLICATION)).build();

            favoriteDao.remove(breedName);

            return Response.ok().build();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(Constants.MSG_ERROR_DEFAULT, ErrorType.APPLICATION)).build();
        }
    }


}

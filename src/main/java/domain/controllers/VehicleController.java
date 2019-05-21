package domain.controllers;

import domain.controllers.Requests.ChangeCarTrackerVehicleRequest;
import domain.controllers.Requests.CreateVehicleRequest;
import domain.controllers.Requests.MarkVehicleAsStolenRequest;
import domain.controllers.Requests.TransferOwenershipVehicleRequest;
import domain.models.OwnerCredentials;
import domain.models.Vehicle;
import domain.services.VehicleService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("vehicle")
public class VehicleController {

    @EJB
    private VehicleService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/stolen")
    @Produces("application/json")
    public Response getAllStolen() {
        return Response.ok(service.getAllStolen()).build();
    }

    @GET
    @Path("/{licencePlate}")
    @Produces("application/json")
    public Response getByLicencePlate(@PathParam(value="licencePlate") String licencePlate) {
        return Response.ok(service.getByLicencePlate(licencePlate)).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(Vehicle v) {
        return Response.ok(service.update(v)).build();
    }

    @PUT
    @Path("/transferownership")
    @Produces("application/json")
    public Response transferOwnership(TransferOwenershipVehicleRequest req) {
        return Response.ok(service.transferOwnership(req.getVehicleId(), req.getOcId())).build();
    }

    @PUT
    @Path("/changecartracker")
    @Produces("application/json")
    public Response changeCarTracker(ChangeCarTrackerVehicleRequest req) {
        return Response.ok(service.changeCarTracker(req.getVehicleId(), req.getCtId())).build();
    }

    @PUT
    @Path("/stolen")
    @Produces("application/json")
    public Response update(MarkVehicleAsStolenRequest req) {
        boolean success = service.markAsStolen(req.getLicencePlate(), req.isStolen());

        return Response.ok(success).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(CreateVehicleRequest request) {
        return Response.ok(service.create(request.getLicencePlate(), request.getRateCategoryId(), request.getCarTrackerId(), request.getOwnerCredentialsId())).build();
    }
}

package com.duotech.resources;

import com.duotech.model.Profile;
import com.duotech.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles(){
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profName}")
    public Profile getProfile(@PathParam("profName") String profName){
        return profileService.getProfile(profName);
    }

    @POST
    public Profile addProfile(Profile profile){
        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{profName}")
    public Profile updateMessage(@PathParam("profName")String profName, Profile profile){
        profile.setProfName(profName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profName}")
    public Profile deleteMessage(@PathParam("profName") String profName){

        return profileService.removeProfile(profName);
    }
}

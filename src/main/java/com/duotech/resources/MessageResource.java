package com.duotech.resources;

import com.duotech.model.Message;
import com.duotech.resources.bean.MessageFilerBean;
import com.duotech.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilerBean filterBean){
        System.out.println("JSON method called");
        if(filterBean.getYear()>0)
            return messageService.getAllMessagesForYear(filterBean.getYear());
        if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0)
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilerBean filterBean){
        System.out.println("XML method called");
        if(filterBean.getYear()>0)
            return messageService.getAllMessagesForYear(filterBean.getYear());
        if(filterBean.getStart() >= 0 && filterBean.getSize() >= 0)
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
        Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri.toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build();
        return uri.toString();
    }

    private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                    .path(MessageResource.class)
                    .path(Long.toString(message.getId()))
                    .build()
                    .toString();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = messageService.addMessage(message);
        String newMessageId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();
        return   Response.created(uri).entity(newMessage).build();
        //return messageService.addMessage(message);
    }

    /*@POST
    public Message addMessage(Message message) {

        return messageService.addMessage(message);
    }*/
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message deleteMessage(@PathParam("messageId") long id){

        return messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }
}

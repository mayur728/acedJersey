package com.duotech.service;

import com.duotech.database.DatabaseClass;
import com.duotech.model.Comment;
import com.duotech.model.ErrorMessage;
import com.duotech.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId){
        ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "wwww.google.co.nz");
        Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        Message message = messages.get(messageId);
        if(message == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if(comment == null){
            throw new NotFoundException(response);
        }
        return comment;
    }

    public Comment addComment(long messageId, Comment comment){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if(comment.getId() <=0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}

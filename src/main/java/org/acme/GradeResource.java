package org.acme;

import java.util.List;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;

@Path("/grades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GradeResource {

    @GET
    public List<Grade> getAllGrades() {
        return Grade.listAll();
    }

    @GET
    @Path("/grade/{idPerson}")
    public List<Grade> getGradesOfPersonId(@PathParam("idPerson") Long idPerson) {
        return Grade.list("idPerson", idPerson);
    }

    @POST
    public void addGrade(Grade grade) {
        grade.persist();
    }

    @POST
    @Path("new")
    @Transactional
    public void addGrade(JsonObject user) {
        Long idPerson = user.getLong("idPerson");
        int coursValue = user.getInteger("coursValue");
        float note = user.getFloat("note");
        Grade grade = new Grade();
        grade.idPerson = idPerson;
        grade.coursValue = coursValue;
        grade.note = note;
        grade.persist();
    }

    @PUT
    public void updateGrade(@PathParam("id") Long id, Grade grade) {
        Grade temp = Grade.findById(id);
        if (temp != null) {
            temp.idPerson = grade.idPerson;
            temp.coursValue = grade.coursValue;
            temp.note = grade.note;
        }
    }

    @DELETE
    public void deleteGrade(@PathParam("id") Long id) {
        Grade.deleteById(id);
    }
    
}

package org.acme;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.List;

import io.quarkus.security.identity.SecurityIdentity;
//import io.smallrye.jwt.build.Jwt;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.resource.spi.work.SecurityContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    SecurityIdentity securityIdentity;

    // Récupère toutes les personnes dans la table person
    @GET
    public List<Person> getAllPeople() {
        return Person.listAll();
    }

    // Récupérer une personne par son id
    @GET
    @Path("/byId/{id}")
    public Person getPersonById(@PathParam("id") Long id) {
        return Person.findById(id);
    }

    @GET
    @Path("/byName/{name}")
    public Person getPersonName(@PathParam("name") String name) {
        return Person.find("name", name).firstResult();
    }

    // Ajoute une personne dans la table
    /* 
    @POST
    public void addPerson(Person person) {
        person.persist();
    }
*/
    @POST
    @Path("new")
    @Transactional
    public void addPerson(JsonObject user) {
        String name = user.getString("name");
        String password = user.getString("password");
        Person person = new Person();
        person.name = name;
        person.password = password;
        person.persist();
    }

    // Modife une ligne de la table
    @PUT
    public void updatePerson(@PathParam("id") Long id, Person person) {
        Person temp = Person.findById(id);
        if (temp != null) {
            temp.name = person.name;
            temp.password = person.password;
        }
    }

    // Supprime une ligne de la table
    @DELETE
    public void deletePerson(@PathParam("id") Long id) {
        Person.deleteById(id);
    }
    

    // Authentification
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(JsonObject user, @Context SecurityContext securityContext) {
        System.out.println("JSON reçu: " + user.toString());
        String name = user.getString("name");
        String password = user.getString("password");
        System.out.println(name);
        System.out.println(password);
        if (verification(name, password)) {
            /*
            String token = Jwt.claims().upn(name).sign();
            // Retourne le token
            return Response.ok(token).build();
            */
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    // Vérifie s'il existe une personne avec ce nom et ce mot de passe dans la base de données, retourne true si oui, sinon null
    public boolean verification(String name, String password) {
        Person person = Person.find("name = ?1 and password = ?2", name, password).firstResult();
        if (person != null) {
            System.out.println(person.name.toString());
            return true;
        } else {
            return false;
        }
    }

}

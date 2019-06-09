package sa.course.resource;

import sa.course.model.Course;
import sa.course.service.CourseService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.persistence.Entity;
import java.net.URI;
import java.util.List;

@Path("/courses")
public class CourseResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    CourseService courseService;

    @GET
    public List<Course> getAllCourses(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return courseService.getAllCourses(first, maxResult);
    }

    @GET
    @Path("{code}")
    public Response getCourseByCode(@PathParam("code") long code) {
        Course course = courseService.getCourseByCode(code);
        response = Response.status(Response.Status.OK);
        response.entity(course);
        return response.build();
    }

    @POST
    public Response createCourse(Course course) {
        Course createdCourse = courseService.createCourse(course);
        response = Response.status(Response.Status.CREATED);
        response.entity(createdCourse);
        return response.build();
    }

    @PUT
    @Path("{code}")
    public Response updateCourse(@PathParam("code") long code, Course course) {
        Course updatedCourse = courseService.updateCourse(code, course);
        response = Response.status(Response.Status.OK);
        response.entity(updatedCourse);
        return response.build();
    }

    @DELETE
    @Path("{code}")
    public Response deleteCourse(@PathParam("code") long code) {
        long deletedCourseCode = courseService.deleteCourse(code);
        response = Response.status(Response.Status.OK);
        response.entity(deletedCourseCode);
        return response.build();
    }

}

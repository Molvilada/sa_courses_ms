package sa.course.service;

import sa.course.model.Course;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CourseService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Course> getAllCourses(int first, int maxResult) {
        return entityManager.createNamedQuery(Course.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public Course getCourseByCode(long code){
        return entityManager.find(Course.class, code);
    }

    public Course createCourse(Course course) {
        entityManager.persist(course);
        entityManager.flush();
        return course;
    }

    public Course updateCourse(long code, Course course) {
        Course courseToUpdate = entityManager.find(Course.class, code);
        courseToUpdate.setName(course.getName());
        courseToUpdate.setCredits(course.getCredits());
        courseToUpdate.setProfessor(course.getProfessor());
        entityManager.merge(courseToUpdate);
        return entityManager.find(Course.class, code);
    }

    public long deleteCourse(long code) {
        Course course = entityManager.find(Course.class, code);
        entityManager.remove(course);
        return code;
    }
}

package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviews {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            int id = 1;
            System.out.println("Creating a new Course...");
            Course course = new Course("This one's new");

            System.out.println("Adding Reviews...");
            course.addReview(new Review("That is some good stuff"));
            course.addReview(new Review("That is something"));

            System.out.println("Course : " + course);
            System.out.println("Reviews : " + course.getReviewList());
            session.save(course);

            System.out.println("Saving the courses...");

            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

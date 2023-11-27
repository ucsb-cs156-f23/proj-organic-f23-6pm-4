// create a permission executor that will check if a user is one of the instructors for a given courde

package edu.ucsb.cs156.organic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.io.Serializable;

import edu.ucsb.cs156.organic.repositories.CourseRepository;
import edu.ucsb.cs156.organic.repositories.UserRepository;
import liquibase.pro.packaged.S;
import edu.ucsb.cs156.organic.repositories.StaffRepository;
import edu.ucsb.cs156.organic.entities.User;
import edu.ucsb.cs156.organic.entities.Staff;
import edu.ucsb.cs156.organic.entities.Course;

@Service
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        if (targetType.equals("Course") && permission.equals("update")) {
            Optional<Course> course = courseRepository.findById((Long) targetId);
            if (course.isPresent()) {
                Iterable<Staff> instructors = staffRepository.findByCourseId((Long) targetId);
                Integer githubId = ((DefaultOAuth2User) authentication.getPrincipal()).getAttribute("id");
                for (Staff instructor : instructors) {
                    if (instructor.getGithubId() == githubId) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
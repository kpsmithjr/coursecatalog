package org.generation.coursecatalog.repository;

import java.util.Optional;

import org.generation.coursecatalog.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  Optional<Course> findByDeptAndNumber(String dept, String number);
}

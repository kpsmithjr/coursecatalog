package org.generation.coursecatalog.service;

import java.util.List;
import java.util.Optional;

import org.generation.coursecatalog.entity.Course;
import org.generation.coursecatalog.repository.CourseRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
  private final CourseRepository courseRepository;

  @Override
  public List<Course> getAll() {
    return courseRepository.findAll();
  }

  @Override
  public void addCourse(Course course) {
    // TODO: Check if course is already in the catalog
    Optional<Course> existingCourse = courseRepository.findByDeptAndNumber(course.getDept(), course.getNumber());
        
    if (existingCourse.isPresent()) {
      throw new IllegalStateException(String.format("Course %s:%s is already in catalog", course.getDept(), course.getNumber()));
    }
    
    courseRepository.save(course);
  }
  
}

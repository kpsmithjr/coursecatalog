package org.generation.coursecatalog.service;

import java.util.List;

import org.generation.coursecatalog.entity.Course;

public interface CourseService {
  List<Course> getAll();
  
  void addCourse(Course course);
}

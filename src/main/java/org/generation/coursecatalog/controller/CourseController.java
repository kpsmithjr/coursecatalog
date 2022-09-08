package org.generation.coursecatalog.controller;

import java.util.List;

import org.generation.coursecatalog.entity.Course;
import org.generation.coursecatalog.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {
  private final CourseService courseService;

  @GetMapping("/all")
  public List<Course> getAll() {
    return courseService.getAll();
  }

  @PostMapping("/add")
  public void addCourse(@RequestBody Course course) {
    courseService.addCourse(course);
  }
}

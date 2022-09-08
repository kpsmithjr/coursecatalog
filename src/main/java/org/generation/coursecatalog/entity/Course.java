package org.generation.coursecatalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String dept;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String description;

  public Course(String dept, String number, String description) {
    this.dept = dept;
    this.number = number;
    this.description = description;
  }  
}

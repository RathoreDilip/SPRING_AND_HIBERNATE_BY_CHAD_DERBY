package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> students;

	// define @PostContruct to load student data ... only once!

	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		students.add(new Student("Poornima", "Patel"));
		students.add(new Student("Mario", "Rossi"));
		students.add(new Student("Mary", "Smith"));
	}

	// define endpoint for "/student" - return list of students

	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	// define endpoint for "/students/{studentId}" - return student at index

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		// just index into the list ... keep it simple for now

		// check the studentId against the list size

		if ((studentId >= students.size()) || (studentId < 0))
			throw new StudentNotFoundException("Student id not found - " + studentId);

		return students.get(studentId);
	}

	// Add an exception handler using @ExceptionHandler

//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
//
//		// create a StudentErrorResponse
//		StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
//
//		studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
//		studentErrorResponse.setMessage(exc.getMessage());
//		studentErrorResponse.setTimeStamp(System.currentTimeMillis());
//
//		// return ResponseEntity
//
//		return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	// add another exception handler ... to catch any exception (catch all)
//
//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//
//		// create a StudentErrorResponse
//		StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
//
//		studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//		studentErrorResponse.setMessage(exc.getMessage());
//		studentErrorResponse.setTimeStamp(System.currentTimeMillis());
//
//		// return ResponseEntity
//
//		return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
//
//	}
}

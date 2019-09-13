package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ToDoException;
import com.example.model.Response;
import com.example.model.ToDo;
import com.example.service.ToDoService;

@RestController
public class ToDoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private ToDoService toDoService;
	
	
	@GetMapping(value="/todo")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ToDo> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return toDoService.getAllToDo();
	}
	
	@GetMapping(value = "/todo/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ToDo getToDoById(@PathVariable("id") long id){
    	logger.info("ToDo id to return {}", id);
    	ToDo toDo = toDoService.getToDoById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo doesn´t exist");
    	}
		return toDoService.getToDoById(id);
	}

    @DeleteMapping(value = "/todo/{id}")
    @ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id){
    	logger.info("ToDo id to remove {}", id);
    	ToDo toDo = toDoService.getToDoById(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to delete doesn´t exist");
    	}
		toDoService.removeToDo(toDo);
		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
	}
    
    @PostMapping(value = "/todo")
   	public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo todo){
    	logger.info("Payload to save {}", todo);
		return new ResponseEntity<>(toDoService.saveToDo(todo), HttpStatus.OK);
   	}
    
    @PatchMapping(value = "/todo")
   	public ResponseEntity<ToDo>  updateToDo(@RequestBody ToDo payload){
    	logger.info("Payload to update {}",payload);
    	ToDo toDo = toDoService.getToDoById(payload.getId());
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to update doesn´t exist");
    	}
		return new ResponseEntity<>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
	
}
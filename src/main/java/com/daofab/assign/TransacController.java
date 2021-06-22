package com.daofab.assign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assembler.ParentAssembler;
import com.daofab.exception.ResourceNotFoundException;
import com.daofab.model.Parent;

@RestController
public class TransacController implements ErrorController {

	@Autowired
	ParentChildProcessorService parentChildProcessorService;

	@Autowired
	private PagedResourcesAssembler<Parent> pagedResourcesAssembler;
	
	@Autowired
	ParentAssembler parentAssembler;

	@GetMapping("/hello")
	String hello() {
		return "Hello assignmnet !!";
	}

	@GetMapping("/f1")
	ResponseEntity<List<Parent>> fetchAllParent() {
		return ResponseEntity.accepted().body(parentChildProcessorService.processInstallments());
	}

	@GetMapping("/parent/{pageNo}/{pageSize}/{columnBy}")
	ResponseEntity<PagedModel<Parent>>  fetchByPage(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String columnBy) {

		Page<Parent> resultPage = parentChildProcessorService.findPaginated(pageNo, pageSize, columnBy);
		PagedModel<Parent> output = null;
		if (pageNo > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException("Resource exhausted");
		} else {
			output = pagedResourcesAssembler.toModel(resultPage, parentAssembler);
		}

		return new ResponseEntity<>(output,HttpStatus.OK);
	}

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}

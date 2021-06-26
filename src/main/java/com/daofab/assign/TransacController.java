package com.daofab.assign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.exception.ResourceNotFoundException;
import com.daofab.model.Parent;
import com.daofab.repo.ParentRepository;

@RestController
public class TransacController implements ErrorController {

	@Autowired
	ParentRepository parentRepository;

	@Autowired
	ParentChildProcessorService parentChildProcessorService;

	@Autowired
	private PagedResourcesAssembler pagedResourcesAssembler;

	@Autowired
	private ParentService parentService;

	public TransacController(ParentService parentService) {
		this.parentService = parentService;
	}

	@GetMapping("/hello")
	String hello() {
		return "Hello assignmnet !!";
	}

	@GetMapping("/f1")
	public ResponseEntity<List<Parent>> fetchAllParent() {
		return ResponseEntity.accepted().body(parentChildProcessorService.processInstallments());
	}

	@GetMapping
	public ResponseEntity findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer size,
			@RequestParam(required = false) String[] sort,
			@RequestParam(required = false, defaultValue = "asc") String dir) {

		CollectionModel<Parent> parentList = parentService.findAll(page, size, sort, dir);
		if (parentList != null) {
			return ResponseEntity.ok(parentList);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/parent/{pageNo}/{pageSize}/{columnBy}")
	ResponseEntity<PagedModel<Parent>> fetchByPage(@PathVariable int pageNo, @PathVariable int pageSize,
			@PathVariable String columnBy) {

		Page<Parent> resultPage = parentChildProcessorService.findPaginated(pageNo, pageSize, columnBy);

		PagedModel<Parent> output = null;
		if (pageNo > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException("Resource exhausted");
		} else {
			//output = pagedResourcesAssembler.toModel(resultPage, parentAssembler);
		}

		return new ResponseEntity<>(output, HttpStatus.OK);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity get(@PathVariable("id") Long id) {
//
//		Optional<ParentEntity> parentRecord = parentRepository.findById(id);
//
//		return parentRecord.isPresent() ? ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
//				.body(parentAssembler.toModel(parentRecord.get())) : ResponseEntity.notFound().build();
//	}
//
//	@GetMapping
//	public ResponseEntity get(@QuerydslPredicate(root = ParentEntity.class) Predicate predicate, Pageable pageable) {
//
//		Page<ParentEntity> inventories = parentRepository.findAll(predicate, pageable);
//
//		return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
//				.body(pagedResourcesAssembler.toModel(inventories, pagedResourcesAssembler));
//	}

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

package com.daofab.assign;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assembler.ParentAssembler;
import com.daofab.entity.ParentEntity;
import com.daofab.model.ChildResultDTO;
import com.daofab.model.Parent;
import com.daofab.repo.ParentRepository;

@RestController
public class TransacController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "Error handling page";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
	@Autowired
	ParentRepository parentRepository;
	

	@Autowired
	ParentChildProcessorService parentChildProcessorService;

	@Autowired
	ParentAssembler parentAssembler;
	
	private ParentService parentService;
	private ChildService childService;

	@Autowired
	public TransacController(ParentService parentService, ChildService childService) {
		this.parentService = parentService;
		this.childService = childService;
	}

	@GetMapping("/initializeData")
	public ResponseEntity<List<Parent>> fetchAllParent() {
		return ResponseEntity.accepted().body(parentChildProcessorService.processInstallments());
	}

	@GetMapping
	public ResponseEntity<CollectionModel<Parent>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer size,
			@RequestParam(required = false) String[] sort,
			@RequestParam(required = false, defaultValue = "asc") String dir) {

		CollectionModel<Parent> parentList = parentService.findAll(page, size, sort, dir);
		if (parentList != null) {
			return ResponseEntity.ok(parentList);
		}
		
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/childRec")
	public ResponseEntity<List<ChildResultDTO>> getChildRecords() {

		List<ChildResultDTO> childRecs = childService.fetchEmpDeptDataCrossJoin();

		return childRecs !=null ? ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
				.body(childRecs) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/childRec/{id}")
	public ResponseEntity<List<ChildResultDTO>> findChildRecords(@PathVariable("id")Long id) {

		List<ChildResultDTO> childRecs = childService.fetchEmpDeptDataCrossJoin(id);

		return childRecs !=null ? ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
				.body(childRecs) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Parent> get(@PathVariable("id") Long id) {

		Optional<ParentEntity> parentRecord = parentRepository.findById(id);

		return parentRecord.isPresent() ? ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
				.body(parentAssembler.toModel(parentRecord.get())) : ResponseEntity.notFound().build();
	}

}

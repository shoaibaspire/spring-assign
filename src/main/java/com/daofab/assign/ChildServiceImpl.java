package com.daofab.assign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daofab.model.ChildResultDTO;
import com.daofab.repo.ChildRepository;

@Service
public class ChildServiceImpl implements ChildService {

	ChildRepository childRepository;

	@Autowired
	public ChildServiceImpl(ChildRepository childRepository) {
		this.childRepository = childRepository;
	}

	@Override
	public List<ChildResultDTO> fetchEmpDeptDataCrossJoin() {

		List<ChildResultDTO> resultChild = null;
		resultChild = childRepository.fetchEmpDeptDataCrossJoin();

		return resultChild;
	}

	@Override
	public List<ChildResultDTO> fetchEmpDeptDataCrossJoin(Long id) {
		List<ChildResultDTO> resultChild = null;
		resultChild = childRepository.fetchEmpDeptDataCrossJoin(id);

		return resultChild;
	}

}

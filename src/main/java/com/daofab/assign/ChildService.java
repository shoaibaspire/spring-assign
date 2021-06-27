package com.daofab.assign;

import java.util.List;

import com.daofab.model.ChildResultDTO;

public interface ChildService {

	List<ChildResultDTO> fetchEmpDeptDataCrossJoin();
	
	List<ChildResultDTO> fetchEmpDeptDataCrossJoin(Long id);


}

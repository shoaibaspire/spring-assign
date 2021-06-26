package com.daofab.assign;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.daofab.entity.ChildEntity;
import com.daofab.entity.ParentEntity;
import com.daofab.model.Child;
import com.daofab.model.ChildData;
import com.daofab.model.Parent;
import com.daofab.model.ParentData;
import com.daofab.repo.ChildRepository;
import com.daofab.repo.ParentRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParentChildProcessorService {

//	@Autowired
//	ParentMapper pMapper;
//
//	@Autowired
//	ChildMapper cMapper;

	@Autowired
	ParentRepository parentRepository;

	@Autowired
	ChildRepository childRepo;

	final ObjectMapper objectMapper = new ObjectMapper();

	List<Parent> fetchAllParents() {
		return null;
	}

	List<Parent> processInstallments() {
		List<Parent> parentList = new ArrayList<>();
		List<Child> childList = new ArrayList<>();

		ParentData c;
		try {
			File parentJson = new ClassPathResource("Parent.json").getFile();
			c = objectMapper.readValue(parentJson, ParentData.class);
			for (Parent p : c.getData()) {
				System.out.println(p);
			}
			parentList.addAll(c.getData());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ChildData childData;
		try {
			File childJson = new ClassPathResource("Child.json").getFile();

			childData = objectMapper.readValue(childJson, ChildData.class);
			for (Child p : childData.getData()) {
				System.out.println(p);
			}
			childList.addAll(childData.getData());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<ParentEntity> pEntityList = new ArrayList<>();
		List<ChildEntity> cEntityList = new ArrayList<>();
		System.out.println("childList list::::---->" + childList);
		System.out.println("parentList list::::---->" + parentList);

		if (childList != null && !childList.isEmpty() && parentList != null && !parentList.isEmpty()) {
			System.out.println("1:---->");

			// pEntityList = pMapper.map(parentList);
			for (Parent eachParent : parentList) {
				ParentEntity each = DtotoParentEntity(eachParent);
				pEntityList.add(each);
			}
			System.out.println("pEntityList list::::---->" + pEntityList);

			pEntityList.stream().forEach(p -> parentRepository.save(p));

			System.out.println("111.from db pList list::::---->" + parentRepository.findAll());

			// extracted(childList, cEntityList);
			for (ParentEntity eachParent : parentRepository.findAll()) {

				for (Child eachChild : childList) {
					if (eachChild.getParentId() == eachParent.getId()) {
						ChildEntity eachChildentity = DtotoChildEntity(eachChild);

						int installmentAmt = eachChild.getPaidAmount();
						int currentTotalPaidAmount = eachParent.getTotalPaidAmount();
						currentTotalPaidAmount += installmentAmt;
						eachParent.setTotalPaidAmount(currentTotalPaidAmount);

						eachParent.addChildren(eachChildentity);

						// childRepo.save(eachChildentity);
						cEntityList.add(eachChildentity);
					}
				}

				parentRepository.save(eachParent);
			}

			System.out.println("End of persistence::" + parentRepository.findAll());
			System.out.println("End of cEntityList::" + cEntityList);
			childRepo.saveAll(cEntityList);
			System.out.println("End of childRepo::" + childRepo.findAll());

			// process main logic now
//			Map<Long, Integer> parentCumulativesum = new HashMap<>();
//			for (ChildEntity eachOne : cEntityList) {
//				if (parentCumulativesum.containsKey(eachOne.pId)) {
//					int oldVal = parentCumulativesum.get(eachOne.pId);
//					int newVal = eachOne.paidAmount + oldVal;
//					parentCumulativesum.put(eachOne.pId, newVal);
//
//				} else {
//					parentCumulativesum.put(eachOne.pId, eachOne.paidAmount);
//				}
//			}//cumulative sum end 
//			
//			pEntityList.stream().forEach(p -> {
//				if(parentCumulativesum.containsKey(p.id)) {
//					p.totalPaidAmount = parentCumulativesum.get(p.id);
//					//p.setChildren(null);
//				}
//				parentRepository.save(p);
//			});

			// get latest persisted ones
			pEntityList = parentRepository.findAll();
		}
		List<Parent> resultOutput = pEntityList.stream().map(eachValue -> parentEntityToDto(eachValue))
				.collect(Collectors.toList());

		return resultOutput;
	}

	private void extracted(List<Child> childList, List<ChildEntity> cEntityList) {
		for (Child eachChild : childList) {
			ChildEntity each = DtotoChildEntity(eachChild);
			System.out.println("each:::---->" + each);

			if (each.getPId() != null) {
				Optional<ParentEntity> currentParent = parentRepository.findById(each.getPId());

				System.out.println("each.getPId():::---->" + each.getPId());

				if (currentParent.isPresent()) {

					ParentEntity currentValue = currentParent.get();
					// currentValue.addChildren(each);

					// each.addParent(currentValue);
					System.out.println("currentParent:::---->" + currentParent);

					parentRepository.save(currentValue);
				}
			}
			cEntityList.add(each);
		}

		System.out.println("cEntityList list::::---->" + cEntityList);

		cEntityList.stream().forEach(p -> childRepo.save(p));
	}

	ParentEntity DtotoParentEntity(Parent dto) {
		if (dto == null)
			return null;
		ParentEntity pEntity = new ParentEntity();
		pEntity.totalAmount = dto.getTotalAmount();
		pEntity.receiver = dto.getReceiver();
		pEntity.sender = dto.getSender();

		return pEntity;
	}

	ChildEntity DtotoChildEntity(Child dto) {
		if (dto == null)
			return null;
		ChildEntity cEntity = new ChildEntity();
		// cEntity.id = dto.getId();
		cEntity.pId = dto.getParentId();
		cEntity.paidAmount = dto.getPaidAmount();
		// cEntity. =dto.getSender();
		return cEntity;
	}

	Parent parentEntityToDto(ParentEntity dto) {
		if (dto == null)
			return null;
		Parent parentInfo = new Parent();
		parentInfo.setTotalAmount(dto.getTotalAmount());
		parentInfo.setReceiver(dto.getReceiver());
		parentInfo.setSender(dto.getSender());
		parentInfo.setId(dto.getId());
		parentInfo.setTotalPaidAmount(dto.getTotalAmount());
		return parentInfo;
	}

	public Page<Parent> findPaginated(int pageNo, int pageSize, String columnBy) {

		boolean fieldExists = false;

		for (Field field : ParentEntity.class.getFields()) {
			if (field.getName().equals(columnBy)) {
				// System.out.println(field.getName());
				fieldExists = true;
				break;
			}
		}

		if (!fieldExists) {
			// sort by default by parent id
			columnBy = "id";
		}
		

		Page<ParentEntity> pEntList = parentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(columnBy)));

		System.out.println("pEntList::" + pEntList);
		List<Parent> resultOutput = pEntList.stream().map(eachValue -> parentEntityToDto(eachValue))
				.sorted(Comparator.comparing(Parent::getId)).collect(Collectors.toList());

		System.out.println("resultOutput::" + resultOutput);

		return new PageImpl<>(resultOutput);

	}

	public List<Parent> sortByFieldName(List<Parent> list, String fieldName) throws NoSuchFieldException {
		Field field = Parent.class.getDeclaredField(fieldName);
		if (!String.class.isAssignableFrom(field.getType())) {
			throw new IllegalArgumentException("Field is not a string!");
		}

		field.setAccessible(true);
		return list.stream().sorted((first, second) -> {
			try {
				String a = (String) field.get(first);
				String b = (String) field.get(second);
				return a.compareTo(b);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Error", e);
			}
		}).collect(Collectors.toList());
	}
}

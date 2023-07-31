package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialprotection.entity.Children;
import com.socialprotection.entity.ChildrenStatus;
import com.socialprotection.entity.TypeOfOrphan;
import com.socialprotection.repository.ChildrenRepository;
import com.socialprotection.repository.ChildrenStatusRepository;
import com.socialprotection.repository.ImageRepository;
import com.socialprotection.repository.TypeOfOrphanRepopsitory;
import com.socialprotection.service.ChildrenService;

@Service
public class ChildrenServiceImpl implements ChildrenService {
	@Autowired
	private ChildrenRepository childrenRepository;

	@Autowired
	private ChildrenStatusRepository childrenStatusRepository;

	@Autowired
	private TypeOfOrphanRepopsitory typeOfOrphanRepopsitory;

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Children save(Children children) {
		imageRepository.save(children.getImage());
		return childrenRepository.save(children);
	}

	@Override
	public TypeOfOrphan saveTypeOrphan(TypeOfOrphan typeOfOrphan) {
		return typeOfOrphanRepopsitory.save(typeOfOrphan);
	}

	@Override
	public ChildrenStatus saveChildrenStatus(ChildrenStatus childrenStatus) {
		return childrenStatusRepository.save(childrenStatus);
	}

	@Override
	public List<Children> findAll() {
		return childrenRepository.findAll();
	}

	@Override
	public Children findById(long id) {
		return childrenRepository.findOne(id);
	}

	@Override
	public void deleteChildrenById(long id) {
		childrenRepository.delete(id);
	}
}

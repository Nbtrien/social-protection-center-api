package com.socialprotection.service;

import java.util.List;

import com.socialprotection.entity.Children;
import com.socialprotection.entity.ChildrenStatus;
import com.socialprotection.entity.TypeOfOrphan;

public interface ChildrenService {
//	Save children
	public Children save(Children children);

	public TypeOfOrphan saveTypeOrphan(TypeOfOrphan typeOfOrphan);

	public ChildrenStatus saveChildrenStatus(ChildrenStatus childrenStatus);

//	Select children
	public List<Children> findAll();

	public Children findById(long id);

	public void deleteChildrenById(long id);

}

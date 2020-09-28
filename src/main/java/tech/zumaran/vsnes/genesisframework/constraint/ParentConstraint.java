package tech.zumaran.vsnes.genesisframework.constraint;

import java.util.Collection;
import java.util.List;

import tech.zumaran.vsnes.genesisframework.GenesisEntity;
import tech.zumaran.vsnes.genesisframework.GenesisRepository;
import tech.zumaran.vsnes.genesisframework.GenesisService;
import tech.zumaran.vsnes.genesisframework.exception.GenesisException;
import tech.zumaran.vsnes.genesisframework.exception.ParentConstraintException;

public interface ParentConstraint
			<Parent extends GenesisEntity, 
			ParentRepository extends GenesisRepository<Parent>,
			ParentService extends GenesisService<Parent, ParentRepository>,
			Child extends GenesisEntity,
			ChildRepository extends GenesisRepository<Child>,
			ChildService extends GenesisService<Child, ChildRepository>> {
	
	Parent getParent(Child child);
	
	ParentService parentService();
	
	ChildService childService();

	default Child verifyParentAndInsert(Child child) throws GenesisException {
		if (getParent(child) != null) {
			parentService().findById(getParent(child).getId());
			return childService().insert(child);
		} else {
			throw new ParentConstraintException("Parent must not be null.");
		}
	}
	
	default List<Child> verifyParentAndInsertAll(Collection<Child> children) throws GenesisException {
		for (Child child: children) {
			if (getParent(child) != null) {
				parentService().findById(getParent(child).getId());
			} else {
				throw new ParentConstraintException("Parent must not be null.");
			}
		}
		return childService().insertAll(children);
	}
}

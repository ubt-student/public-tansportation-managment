package org.pbm.map.repository;

import org.pbm.map.domain.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
}

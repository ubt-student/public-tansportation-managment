package project.transportation.publictransportationmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.transportation.publictransportationmanager.model.Useri;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Useri, Long> {
    void deleteUserById(Long id);

    Optional<Useri> findUserById(Long id);

    Optional<Useri> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Useri a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}

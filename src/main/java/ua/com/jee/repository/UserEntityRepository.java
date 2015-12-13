package ua.com.jee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.jee.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findByNameIgnoringCase(String name);

    UserEntity findByName(String name);

}

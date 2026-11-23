package com.microservice_employee.infrastructure.adapter.out.persistence.repository;

import com.microservice_employee.infrastructure.adapter.out.persistence.entity.EmployeeJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeJpaEntity, Long> {

    @Query("""
             SELECT e
                 FROM EmployeeJpaEntity e
                 WHERE LOWER(
                     CONCAT(
                         CONCAT(
                             CONCAT(
                                 CONCAT(
                                     COALESCE(e.firstName, ''),
                                     ' '
                                 ),
                                 CONCAT(COALESCE(e.secondName, ''), ' ')
                             ),
                             CONCAT(COALESCE(e.lastName, ''), ' ')
                         ),
                         COALESCE(e.motherLastName, '')
                     )
             ) LIKE CONCAT('%', :name, '%')
            """)
    Page<EmployeeJpaEntity> searchByName(
            @Param("name") String name,
            Pageable pageable
    );
}
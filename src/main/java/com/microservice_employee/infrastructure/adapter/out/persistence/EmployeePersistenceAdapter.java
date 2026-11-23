package com.microservice_employee.infrastructure.adapter.out.persistence;

import com.microservice_employee.application.port.out.EmployeeRepository;
import com.microservice_employee.domain.model.Employee;
import com.microservice_employee.infrastructure.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.microservice_employee.infrastructure.adapter.out.persistence.mapper.EmployeePersistenceMapper;
import com.microservice_employee.infrastructure.adapter.out.persistence.repository.EmployeeJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeePersistenceAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository jpaRepository;
    private final EmployeePersistenceMapper mapper;

    public EmployeePersistenceAdapter(
            EmployeeJpaRepository jpaRepository,
            EmployeePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeJpaEntity entity = mapper.toEntity(employee);
        EmployeeJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        List<EmployeeJpaEntity> entities = employees.stream()
                .map(mapper::toEntity)
                .toList();
        return jpaRepository.saveAll(entities)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Page<Employee> searchByName(String name, Pageable pageable) {
        return jpaRepository.searchByName(name.toLowerCase(), pageable)
                .map(mapper::toDomain);
    }
}
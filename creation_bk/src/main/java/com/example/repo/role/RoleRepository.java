
package com.example.repo.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.roles.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long>{
	Optional<Roles> findByRoleType(String name);
}
package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.email like ?1% ")
    List<User> findAndSort(String email, Sort sort);

    List<User> findByName(String nombre);

    Optional<User> findByEmailAndName(String email, String nombre);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name,String email);

    List<User> findByBirthDateBetween(LocalDate start, LocalDate end);

    List<User> findByNameLikeOrderByNameDesc(String name);

    @Query("SELECT new  com.fundamentos.springboot.fundamentos.dto.UserDto(u.id,u.name,u.birthDate) " +
            "FROM User u " +
            "WHERE u.birthDate=:paramBirthDate " +
            "AND u.email=:paramEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("paramBirthDate") LocalDate date,
                                                @Param("paramEmail") String email);
    List<User> findAll();
}

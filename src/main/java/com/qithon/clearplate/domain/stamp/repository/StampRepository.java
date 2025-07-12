package com.qithon.clearplate.domain.stamp.repository;

import com.qithon.clearplate.domain.stamp.entity.Stamp;
import com.qithon.clearplate.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {

  List<Stamp> findAllByUserOrderByCreatedAtDesc(User user);

  List<Stamp> findAllByUser(User user);
  // JpaRepository provides basic CRUD operations
    // Additional custom query methods can be defined here if needed

}

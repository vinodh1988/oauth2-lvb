package com.lvb.oauth2.dao;

import org.springframework.data.repository.CrudRepository;

import com.lvb.oauth2.entity.Users;

public interface UsersDAO extends CrudRepository<Users,Long>{
  Users findUsersByUsername(String username);
}

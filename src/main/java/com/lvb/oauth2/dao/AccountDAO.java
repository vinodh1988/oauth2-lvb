package com.lvb.oauth2.dao;

import org.springframework.data.repository.CrudRepository;

import com.lvb.oauth2.entity.Account;



public interface AccountDAO extends CrudRepository<Account,Long>{
   Account findByUsername(String username);
}

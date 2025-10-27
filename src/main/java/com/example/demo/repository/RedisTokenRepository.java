package com.example.demo.repository;

import com.example.demo.entity.RedisToken;
import org.springframework.data.repository.CrudRepository;


public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}

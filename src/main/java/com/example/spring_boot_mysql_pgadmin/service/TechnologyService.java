package com.example.spring_boot_mysql_pgadmin.service;

import com.example.spring_boot_mysql_pgadmin.entity.Technology;

import com.example.spring_boot_mysql_pgadmin.repository.mysql.MySQLRepo;
import com.example.spring_boot_mysql_pgadmin.repository.postgresql.PostgreSQLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {

    @Autowired
    private MySQLRepo mysqlRepository;  // Inject MySQL repository

    @Autowired
    private PostgreSQLRepo postgresqlRepository;  // Inject PostgreSQL repository

    public Technology saveOrUpdate(Technology technology, String dbType) {
        if ("postgres".equalsIgnoreCase(dbType)) {
            return postgresqlRepository.save(technology);
        } else {
            return mysqlRepository.save(technology);
        }
    }

    public Optional<Technology> findById(Long id, String dbType) {
        return "postgres".equalsIgnoreCase(dbType) ? postgresqlRepository.findById(id) : mysqlRepository.findById(id);
    }

    public List<Technology> findAll(String dbType) {
        return "postgres".equalsIgnoreCase(dbType) ? postgresqlRepository.findAll() : mysqlRepository.findAll();
    }

    public void deleteById(Long id, String dbType) {
        if ("postgres".equalsIgnoreCase(dbType)) {
            postgresqlRepository.deleteById(id);
        } else {
            mysqlRepository.deleteById(id);
        }
    }
}

package com.example.spring_boot_mysql_pgadmin.repository.mysql;

import com.example.spring_boot_mysql_pgadmin.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySQLRepo extends JpaRepository<Technology, Long> {
}

package com.example.spring_boot_mysql_pgadmin.repository.postgresql;

import com.example.spring_boot_mysql_pgadmin.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSQLRepo extends JpaRepository<Technology,Long> {
}

package dev.sunit.sessiondemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.Properties;

@Configuration
public class Config {

    @Bean
    public DataSource postgres() {

        Properties props = new Properties();

        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "test");
        props.setProperty("dataSource.password", "test");
        props.setProperty("dataSource.databaseName", "mydb");
        props.put("dataSource.logWriter", new PrintWriter(System.out));

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }

    @Bean
    public JdbcTemplate pgTemplate(@Qualifier("postgres") DataSource postgres) {
        return new JdbcTemplate(postgres);
    }

}

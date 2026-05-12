package com.vibeham.template.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    /**
     * Accepts DATABASE_URL in postgres:// format (as provided by managed Kubernetes services):
     *   postgres://user:pass@host:port/dbname?sslmode=require
     *
     * Falls back to individual env vars when DATABASE_URL is not set:
     *   DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD, DB_SSL_MODE
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl != null && !databaseUrl.isBlank()) {
            // URI parser doesn't understand the postgres:// scheme – swap to http:// temporarily
            URI uri = URI.create(databaseUrl.replaceFirst("^postgres(ql)?://", "http://"));

            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();
            if (uri.getQuery() != null) {
                // bare "sslmode" without value → treat as "sslmode=require"
                String query = uri.getQuery().contains("=") ? uri.getQuery() : uri.getQuery() + "=require";
                jdbcUrl += "?" + query;
            }

            String[] userInfo = uri.getUserInfo().split(":", 2);
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(userInfo[0]);
            config.setPassword(userInfo.length > 1 ? userInfo[1] : "");
        } else {
            String host    = env("DB_HOST",     "localhost");
            String port    = env("DB_PORT",     "5432");
            String name    = env("DB_NAME",     "spacedb");
            String sslMode = System.getenv("DB_SSL_MODE");

            String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + name;
            if (sslMode != null && !sslMode.isBlank()) {
                jdbcUrl += "?sslmode=" + sslMode;
            }

            config.setJdbcUrl(jdbcUrl);
            config.setUsername(env("DB_USER",     "postgres"));
            config.setPassword(env("DB_PASSWORD", "postgres"));
        }

        return new HikariDataSource(config);
    }

    private String env(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }
}

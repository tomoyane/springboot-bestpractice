package com.bestpractice.api.infrastrucuture;

import com.bestpractice.api.common.property.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableCaching
@Profile("!test")
public class InfrastructureBean {

    @Configuration
    @Profile("cache_local")
    public static class LocalCacheRepository {

    }

    @Configuration
    @Profile("cache_redis")
    public static class RedisCacheRepository {
        @Autowired
        private RedisProperty redisProperty;
    }

    @Configuration
    @Profile("db_local")
    public static class LocalDbRepository {
        @Bean
        public UserPersistentRepository userRepository() {
            return new LocalUserPersistentRepository();
        }
        @Bean
        public InfoPersistentRepository infoRepository() {
            return new LocalInfoPersistentRepository();
        }
    }

    @Configuration
    @Profile("db_rdbms")
    public static class RdbmsDbRepository {
        @Value("${spring.datasource.url}")
        private String url;
        @Value("${spring.datasource.username}")
        private String username;
        @Value("${spring.datasource.password}")
        private String password;
        @Value("${spring.datasource.driver-class-name}")
        private String driverClassName;
        @Bean
        public DriverManagerDataSource dataSource() {
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName(driverClassName);
            driverManagerDataSource.setUrl(url);
            driverManagerDataSource.setUsername(username);
            driverManagerDataSource.setPassword(password);
            return driverManagerDataSource;
        }
        @Bean
        public DataSourceTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }
        @Bean
        public UserPersistentRepository userRepository(JdbcTemplate jdbcTemplate) {
            return new RdbmsUserPersistentRepository(jdbcTemplate);
        }
        @Bean
        public InfoPersistentRepository infoRepository() {
            return new RdbmsInfoPersistentRepository(jdbcTemplate());
        }
    }

    @Configuration
    @Profile("db_cassandra")
    public static class CassandraDbRepository {
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate();
        }

        @Bean
        public UserPersistentRepository userRepository(JdbcTemplate jdbcTemplate) {
            return new RdbmsUserPersistentRepository(jdbcTemplate);
        }
    }

    @Configuration
    @Profile("db_mongo")
    public static class MongoDbRepository {
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate();
        }

        @Bean
        public UserPersistentRepository userRepository(JdbcTemplate jdbcTemplate) {
            return new RdbmsUserPersistentRepository(jdbcTemplate);
        }
    }
}

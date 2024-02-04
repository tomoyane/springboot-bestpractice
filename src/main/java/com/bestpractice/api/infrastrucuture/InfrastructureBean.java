package com.bestpractice.api.infrastrucuture;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.property.MongoProperty;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.rdbms.RdbmsUserPersistentRepository;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.net.InetSocketAddress;
import java.util.List;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
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

    private final RedisProperty redisProperty;

    public RedisCacheRepository(RedisProperty redisProperty) {
      this.redisProperty = redisProperty;
    }
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

    private final CassandraProperty cassandraProperty;

    public CassandraDbRepository(CassandraProperty cassandraProperty) {
      this.cassandraProperty = cassandraProperty;
    }

    @Bean
    public CqlSession cqlSession() {
      CqlSessionBuilder builder = CqlSession.builder();
      for (String hostAndPort : cassandraProperty.getHosts()) {
        String[] splitHostAndPort = hostAndPort.split(":");
        builder = builder.addContactPoint(
            new InetSocketAddress(splitHostAndPort[0], Integer.parseInt(splitHostAndPort[1])));
      }
      return builder
          .withLocalDatacenter("dc01")
          .withKeyspace(CqlIdentifier.fromCql(cassandraProperty.getKeyspace()))
          .build();
    }

    @Bean
    public UserPersistentRepository userRepository() {
      return new LocalUserPersistentRepository();
    }

    @Bean
    public InfoPersistentRepository infoRepository() {
      return new CassandraInfoPersistentRepository(cqlSession());
    }
  }

  @Configuration
  @Profile("db_mongo")
  public static class MongoDbRepository {

    private final MongoProperty mongoProperty;

    public MongoDbRepository(MongoProperty mongoProperty) {
      this.mongoProperty = mongoProperty;
    }

    @Bean
    public MongoClient mongoClient() {
      MongoCredential credential = MongoCredential.createCredential(
          mongoProperty.getUser(),
          mongoProperty.getAuthDatabase(),
          mongoProperty.getPassword().toCharArray());

      CodecRegistry pojoCodecRegistry = fromRegistries(
          MongoClientSettings.getDefaultCodecRegistry(),
          fromProviders(PojoCodecProvider.builder().automatic(true).build()));

      ServerAddress serverAddress = new ServerAddress(mongoProperty.getHost(),
          mongoProperty.getPort());
      return MongoClients.create(MongoClientSettings.builder()
          .codecRegistry(pojoCodecRegistry)
          .applyToClusterSettings(builder -> builder.hosts(List.of(serverAddress)))
          .credential(credential)
          .build());
    }

    @Bean
    public MongoDatabase mongoDatabase() {
      return mongoClient().getDatabase(mongoProperty.getPlatformDatabase());
    }

    @Bean
    public UserPersistentRepository userRepository() {
      return new MongoUserPersistentRepository(mongoClient(), mongoDatabase());
    }

    @Bean
    public InfoPersistentRepository infoRepository() {
      return new MongoInfoPersistentRepository(mongoClient(), mongoDatabase());
    }
  }
}

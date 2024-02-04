## How to start API server

Local datasource
```bash
./scripts/start_server.sh --spring_profile=local,db_local
```

RDBMS
```bash
./scripts/start_server.sh --spring_profile=local,db_rdbms
```

MongoDB
```bash
./scripts/start_server.sh --spring_profile=local,db_mongo
```

Cassandra
```bash
./scripts/start_server.sh --spring_profile=local,db_cassandra
```
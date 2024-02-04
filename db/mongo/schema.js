db.createUser({
    user: "spring_boot",
    pwd: "spring_boot",
    roles: [
        {role: "readWrite", db: "spring_boot"}
    ]
});

db.createCollection('users')
db.createCollection('infos')
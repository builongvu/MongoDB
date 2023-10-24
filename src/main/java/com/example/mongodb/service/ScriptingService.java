//package com.example.mongodb.service;
//
//@Service
//public class ScriptingService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public User createUser(String name, int age) {
//        String script = "return db.user.insert({name: '" + name + "', age: " + age + "});";
//        mongoTemplate.scriptExecute(script, Collections.emptyMap());
//    }
//
//    public User findUserById(String id) {
//        String script = "return db.user.findOne({_id: ObjectId('" + id + "')});";
//        Document result = mongoTemplate.scriptExecute(script, Collections.emptyMap());
//        return mongoTemplate.getConverter().read(User.class, result);
//    }
//
//    public List<User> findAllUsers() {
//        String script = "return db.user.find().toArray();";
//        List<Document> results = mongoTemplate.scriptExecute(script, Collections.emptyMap());
//        return results.stream()
//                .map(doc -> mongoTemplate.getConverter().read(User.class, doc))
//                .collect(Collectors.toList());
//    }
//
//    public void updateUser(String id, String name, int age) {
//        String script = "return db.user.update({_id: ObjectId('" + id + "')}, {$set: {name: '" + name + "', age: " + age + "}});";
//        mongoTemplate.scriptExecute(script, Collections.emptyMap());
//    }
//
//    public void deleteUser(String id) {
//        String script = "return db.user.remove({_id: ObjectId('" + id + "')});";
//        mongoTemplate.scriptExecute(script, Collections.emptyMap());
//    }
//}

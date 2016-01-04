# simple-server (java)
============
Generic Backend Server Skeleton for Java

Compile with:

```
mvn clean install
```

Run locally:

```
java -cp <classpath> -DserverInitializer=com.mymoviematch.simpleserver.example.EmptyInitializer com.mymoviematch.simpleserver.core.ServerRunner
```

Run locally (with custom config file):

```
java -cp <classpath> -DserverInitializer=com.mymoviematch.simpleserver.example.EmptyInitializer -DconfigFile=testdb.properties com.mymoviematch.simpleserver.core.ServerRunner
```

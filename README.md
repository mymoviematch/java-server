# java-server
============
Generic Java Backend Server Skeleton

Compile with:

```
mvn clean install
```

```

Run locally:

```
java -cp <classpath> -DserverInitializer=sk.ondrejhirjak.jserver.example.EmptyInitializer sk.ondrejhirjak.jserver.core.ServerRunner
```

Run locally (with custom config file):

```
java -cp <classpath> -DserverInitializer=sk.ondrejhirjak.jserver.example.EmptyInitializer -DconfigFile=testdb.properties sk.ondrejhirjak.jserver.core.ServerRunner
```

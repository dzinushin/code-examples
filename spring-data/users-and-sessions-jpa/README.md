

## Flywaydb usage
https://documentation.red-gate.com/flyway/

https://documentation.red-gate.com/flyway/quickstart-how-flyway-works/quickstart-gradle

https://documentation.red-gate.com/fd/gradle-task-flywaybaseline-184127424.html


```shell
./gradlew flywayInfo -i

./gradlew flywayInfo -i \
  -Dflyway.user=postgres \
  -Dflyway.password=postgres \
  -Dflyway.schemas=users-and-sessions \
  -Dflyway.url=jdbc:postgresql://localhost:5432/postgres
```

```shell
./gradlew flywayMigrate -i
```

```shell
./gradlew flywayValidate -i \
  -Dflyway.user=postgres \
  -Dflyway.password=postgres \
  -Dflyway.schemas=users-and-sessions \
  -Dflyway.url=jdbc:postgresql://localhost:5432/postgres
```

```shell
./gradlew flywayClean -i \
  -Dflyway.cleanDisabled=false

```

```shell
./gradlew flywayBaseline -i \
  -Dflyway.baselineVersion=1
```

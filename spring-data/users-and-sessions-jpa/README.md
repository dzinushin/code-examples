

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

### Как "схлопнуть" историю изменений flyway не удаляя данные (в т.ч. на проде):

* Всю историю изменений "схлопываем" в 1 файле ```V1__create_initial_scheme.sql```

На всех сущеcтвующих инстансах БД мкс проделываем следующие операции:

1. удаляем таблицу flyway_schema_history (нужно предварительно сделать backup)

```sql
-- делаем backup таблицы с историей миграций flyway
create table flyway_schema_history_bak as select * from flyway_schema_history;
```
```sql
-- удаляем таблицу flyway_schema_history 
drop table flyway_schema_history;
```

2. выполняем baseline на существующей БД (в параметрах нужно подставить актуальные параметры):

```
./gradlew flywayInfo \
  -Dflyway.user=postgres \
  -Dflyway.password=postgres \
  -Dflyway.schemas=public \
  -Dflyway.url=jdbc:postgresql://localhost:5432/postgres

./gradlew flywayBaseline \
  -Dflyway.user=postgres \
  -Dflyway.password=postgres \
  -Dflyway.schemas=public \
  -Dflyway.url=jdbc:postgresql://localhost:5432/postgres \
  -Dflyway.baselineVersion=1
```
или для кластера
```shell
./gradlew flywayInfo \
  -Dflyway.user=user-recogniser-service \
  -Dflyway.password=<password> \
  -Dflyway.schemas=public \
  -Dflyway.url="jdbc:postgresql://rc1a-7w8iuq4iaz2eknay.mdb.yandexcloud.net:6432,rc1b-hsq0xbl2dvppohjg.mdb.yandexcloud.net:6432/user-recogniser-service?targetServerType=master"


./gradlew flywayBaseline \
  -Dflyway.baselineVersion=1 \
  -Dflyway.user=user-recogniser-service \
  -Dflyway.password=<password> \
  -Dflyway.schemas=public \
  -Dflyway.url="jdbc:postgresql://rc1a-7w8iuq4iaz2eknay.mdb.yandexcloud.net:6432,rc1b-hsq0xbl2dvppohjg.mdb.yandexcloud.net:6432/user-recogniser-service?targetServerType=master"
```


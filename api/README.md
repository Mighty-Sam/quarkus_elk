# quarkus_elk readme
:::
1. git clone this project
2. `./mvnw clean package`
3. `docker-compose up -d`
4. url <http://localhost:8888/elk/user/api>, this api will generate a log-- "Find all user info: {}, userDtos".
5. url <http://localhost:9200/_cat/indices?v>, this url will return all index in elasticsearch. you will see an index called "test-log"， which is configured in this project.
6. url <http://localhost:9200/test-log/_search>, this url will elaborate all "test-log" info, inclusive of its value, index, messages etc.

![image](https://github.com/Mighty-Sam/quarkus_elk/assets/104480930/12a7b35f-ca1f-45d2-ab9e-2504373d79d3)

7. url <http://localhost:5601>, it will direct to Kibana homepage.


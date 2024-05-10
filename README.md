# quarkus_elk readme
:::
1. git clone this project
2. type `./mvnw clean package` in terminal
3. `docker-compose setup` in terminal
4. `docker-compose up -d` in terminal
5. invoke this api <http://localhost:8888/elk/user/api>, this api will generate a log-- "Find all user info: {}, userDtos".
6. invoke this api <http://elastic:Aa00000000@localhost:9200/_cat/indices?v>, this api will return all index in elasticsearch. you will see an index called "test-log"， which is configured in this project.
7. invoke this api <http://elastic:Aa00000000@localhost:9200/test-log/_search>, this api will elaborate all "test-log" info, inclusive of its value, index, messages etc.

![image](https://github.com/Mighty-Sam/quarkus_elk/assets/104480930/12a7b35f-ca1f-45d2-ab9e-2504373d79d3)

7. url <http://localhost:5601>, it will direct to Kibana homepage (username: elastic , password: Aa00000000).
8. After landing on the Kibana homepage, please click "discover" button and click "create index pattern".
9. In this demo, you can type `test-log*` as your index and click create button, you will see some logs that match the `test-log` index pop up.
![image](https://github.com/Mighty-Sam/quarkus_elk/assets/104480930/13c504e7-a08b-490f-b1c5-53b59711f389)



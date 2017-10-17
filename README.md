# GEOLOCATION DEMO DATASET BULK LOAD

Clone this repository

[Create a Liferay Workspace with the Blade CLI if you don't have one](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)

Note: the Liferay Workspace should be configured in an empty directory - *not* under portal-master.

Move this project under `modules` in your Liferay Workspace (the one you configured above) 

Build this project:

`gw deploy`

Deploy the resulting bundle (see `osgi/modules`) into a Liferay Portal

Start Liferay Portal

[Open the gogo shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

Confirm this module has been loaded:

`lb | grep "Liferay Geolocation Bulk Load"`

Confirm the command is available:

`help | grep geolocation`

Execute the command: 

`geolocation:load`

You can pass a number as a parameter to determine how 
many Journal Articles will be inserted. If you choose to not pass any number, the 
default value will be `10`.

In case the project doesn't build correctly nor the bundle doesn't start 
immediately after deployment, double check module versions in `bnd.bnd`,
dependencies versions in `build.gradle`, 
and the versions of the same packages deployed in the Portal.

# DATABASE x ELASTICSEARCH COMPARISON

After running the load command, you can check if everything was indexed
correctly. Just count the number of rows in the JournalArticle table in the DB
and then count the number of documents indexed in the Elasticsearch to see if
they match.

Run the following query to count the number of Journal Articles inserted in the
database of your choice (syntax may differ depending on the database):

```
SELECT companyId, count(*) FROM JournalArticle group by companyId;
```

Run the following query to count the number of Journal Articles indexed in the
Elasticsearch server:

```
curl -XPOST 'http://localhost:9200/_search?pretty' --data \
\
'{
  "size": 0,
  "aggregations": {
    "JournalArticle per companyId": {
      "terms": {
        "field": "companyId"
      }
    }
  },
  "query": {
    "term": {
      "entryClassName": "com.liferay.journal.model.JournalArticle"
    }
  }
}'
```

If you're using elasticsearch-head to run this query, make sure you're using
the `POST` method instead of `GET`.

# SOURCE URLS

https://data.cityofboston.gov/City-Services/311-Service-Requests/awu8-dc52
https://data.cityofboston.gov/browse?limitTo=datasets&utf8=✓
https://data.cityofboston.gov/resource/awu8-dc52.json

# APIS

https://github.com/socrata/datasync
https://github.com/socrata/soda-java

# APP URL

http://www.cityofboston.gov/doit/apps/citizensconnect.asp


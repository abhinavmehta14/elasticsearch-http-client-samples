This code has,

1. snippets to index and search small data sets in Elasticsearch via http client
2. uses elastic4s package 
3. works with Elasticsearch 6.1.2



Setup Elasticsearch on localhost with following steps,

1. Download Elasticsearch 6.1.2 [here](https://www.elastic.co/downloads/elasticsearch)
2. Uncompress and change working directory to folder where downloaded software resides
3. Run `bin/elasticsearch`
4. Run `curl http://localhost:9200/` to test if cluster is active



To run the code`Driver` object contains snippets for,

1. Creating index (`createIndexWrapper`) - creates index if it does not exist
There are two sample columns
One column has analyzer disabled to keep the search dumb i.e. return results only if there is exact match
2. Index data  (`indexData`) - snippet to index data
3. Search data (`searchIndex`) - search by one column



 

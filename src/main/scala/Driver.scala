import com.sksamuel.elastic4s.ElasticsearchClientUri
import com.sksamuel.elastic4s.http.ElasticDsl._
import com.sksamuel.elastic4s.http.HttpClient


object Driver
  extends App {

  val indexName = "index1"
  val dataType = "type1"
  val field1 = "not_analyzed_field"
  val field2 = "analyzed_field"

  def createIndexWrapper = {
    // create index if not exists
    val mapping1 = client.execute {
      indexExists(indexName)
    }.await
    println(mapping1)

    mapping1.right.get.status match {
      case 200 =>
        println(s"index already exists. index=$indexName type=$dataType")
      case _ =>
        println(s"creating index=$indexName type=$dataType")
        val notAnalyzedTextField = textField(field1) analyzer "noop"
        val analyzedTextField = textField(field2)
        val createIndexRequest = createIndex(indexName).mappings(
          mapping(dataType) as (
            notAnalyzedTextField,
            analyzedTextField
            )
        ).analysis(
          NoOpAnalyzer.NOOP_ANALYZER
        )
        val resp = client.execute {
          createIndexRequest
        }.await
        println(resp)
    }
  }

  def indexData = {
    client.execute {
      bulk(
        indexInto(indexName / dataType).fields(field1 -> "abhinav", field2 -> "abhinav abhinav"),
        indexInto(indexName / dataType).fields(field1 -> "abhinav kumar", field2 -> "abhinav"),
        indexInto(indexName / dataType).fields(field1 -> "abhinav mehta", field2 -> "abhinav kumar"),
        indexInto(indexName / dataType).fields(field1 -> "mehta", field2 -> "mehta")
      )
    }.await
  }

  def searchIndex = {
    val searchOnIndexedCol = client.execute {
      search(indexName).termQuery(field1, "abhinav")
    }.await
    println(searchOnIndexedCol.right)

    val searchOnNonIndexedCol = client.execute {
      search(indexName).termQuery(field2, "abhinav")
    }.await
    println(searchOnNonIndexedCol.right.get.body.get)
  }

  val client = HttpClient(ElasticsearchClientUri("localhost", 9200))
  createIndexWrapper
  indexData
  searchIndex
  client.close()

}
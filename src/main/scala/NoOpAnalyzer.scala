import com.sksamuel.elastic4s.analyzers.{StandardTokenFilter, KeywordTokenizer, CustomAnalyzerDefinition}

/*
 Elasticsearch 6.1.2 does not seem to have a not_analyzed option out of box
 This Analayzer is a hack to simulate that
 */
object NoOpAnalyzer {

  val NOOP_ANALYZER = CustomAnalyzerDefinition(
    "noop",
    KeywordTokenizer,
    StandardTokenFilter
  )
}

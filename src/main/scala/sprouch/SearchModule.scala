package sprouch

import akka.dispatch.Future
import spray.json.JsonWriter
import spray.httpx.SprayJsonSupport._
import spray.httpx.RequestBuilding._

import JsonProtocol._

trait SearchModule {
  self: UriBuilder =>
    
  def name:String
    
  protected[this] val pipelines:Pipelines
  import pipelines._
  
  private def searchUri(designDocId:String, indexerName:String, q:String, sort:Option[Seq[String]]):String = {
    val kv = Seq("q=" + q) ++ (sort match {
      case Some(keys) if !keys.isEmpty => Seq("sort=" + encode(implicitly[JsonWriter[Seq[String]]].write(keys).toString))
      case _ => Seq()
    })
    path(name, "_design", designDocId, "_search", indexerName) + query(kv:_*)
  }
  private def groupedSearchUri(
      designDocId:String,
      indexerName:String,
      q:String,
      sort:Option[Seq[String]],
      groupField:String,
      groupLimit:Option[Int],
      groupSort:Option[Seq[String]]):String = {
    val kv = Seq("q=" + q) ++ (sort match {
      case Some(keys) if !keys.isEmpty => Seq("sort=" + encode(implicitly[JsonWriter[Seq[String]]].write(keys).toString))
      case _ => Seq()
    }) ++ Seq("group_field=" + groupField) ++ (groupSort match {
      case Some(keys) if !keys.isEmpty => Seq("group_sort=" + encode(implicitly[JsonWriter[Seq[String]]].write(keys).toString))
      case _ => Seq()
    }) ++ groupLimit.toSeq.map("group_limit=" +)
    path(name, "_design", designDocId, "_search", indexerName) + query(kv:_*)
  }
  
  def search(designDocId:String, indexerName:String, query:String, sort:Option[Seq[String]] = None, docLogger:DocLogger = NopLogger):Future[SearchResponse] = {
    val p = pipeline[SearchResponse](docLogger = docLogger)
    p(Get(searchUri(designDocId, indexerName, query, sort)))
  }
  
  def groupedSearch(
                    designDocId:String,
                    indexerName:String,
                    query:String,
                    groupField:String,
                    sort:Option[Seq[String]] = None,
                    groupLimit:Option[Int] = None,
                    groupSort:Option[Seq[String]] = None,
                    docLogger:DocLogger = NopLogger):Future[GroupedSearchResponse] = {
    val p = pipeline[GroupedSearchResponse](docLogger = docLogger)
    p(Get(groupedSearchUri(designDocId, indexerName, query, sort, groupField, groupLimit, groupSort)))
  }
}
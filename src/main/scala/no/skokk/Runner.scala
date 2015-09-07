/*
package no.skokk

import java.net.InetAddress
import java.security.SecureRandom
import java.util.{Random, UUID}

import argonaut.Json
import org.joda.time.{DateTime, LocalDate}
import slick.driver.PostgresDriver
import unfiltered.request.{Path ⇒ UFPath}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.language.postfixOps
import scala.xml.{XML, NodeSeq}
/*

CREATE TABLE public.event (
  id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('event_id_seq'::regclass),
  timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
  event_type CHARACTER VARYING(255) NOT NULL,
  payload TEXT NOT NULL
);


 */

trait Monitor extends TableProfile{
  import profile.api._

/*
  class Event(tag: Tag) extends Table[Event](id: String, timestamp: DateTime, eventType: String, payload: String) {

    def id = column[String]("id",O.PrimaryKey)
    def timestamp = column[DateTime]("timestamp")
    def eventType = column[String]("eventtype")
    def payload = column[String]("payload")


  }
*/

}

//import scalaz.concurrent.Task

/*
/**
 * Created by jarnyste on 08/08/15.
 */

 class AsyncPlan extends unfiltered.filter.async.Plan with Tables  {
  def intent = {
    case GET(UFPath("/pass")) => Pass
    case req@GET(UFPath("/async")) =>
      //"respond" is usually called from an asynchronous callback handler
       req.respond(ResponseString("test") ~> Ok)
  }
}
*/
trait DbConnectionComponent {
  val profile: slick.driver.PostgresDriver
  val db: profile.backend.DatabaseDef

  /*
      object MyPostgresDriver extends PostgresDriver{
        override val typeMapperDelegates = new MyTypeMapperDelegates

        class MyTypeMapperDelegates extends this.TypeMapperDelegates {
          override val uuidTypeMapperDelegate = new MyUUIDTypeMapperDelegate

          class MyUUIDTypeMapperDelegate extends this.UUIDTypeMapperDelegate {
            override def setValue(v: UUID, p: PositionedParameters) = p.setObject(v, sqlType)
            override def setOption(v: Option[UUID], p: PositionedParameters) = p.setObjectOption(v, sqlType)
            override def nextValue(r: PositionedResult) = r.nextObject() match {
              case s: String => UUID.fromString(s)
              case u: UUID => u
            }
            override def updateValue(v: UUID, r: PositionedResult) = r.updateObject(v)
            override def valueToSQLLiteral(value: UUID) = "'" + value.toString.toUpperCase + "'"
          }
        }
      }
  }
  */

}

trait TableProfile extends DbConnectionComponent {
  import profile.api._

  implicit def localDateTimeTypeMapper = MappedColumnType.base[LocalDate, java.sql.Date](
    local => new java.sql.Date(local.toDateTimeAtStartOfDay.getMillis),
    date  => new LocalDate(date.getTime))

  implicit def dateTimeTypeMapper = MappedColumnType.base[DateTime, java.sql.Timestamp](
    datetime => new java.sql.Timestamp(datetime.getMillis),
    timestamp => new DateTime(timestamp.getTime))

  implicit def nodeSeqTypeMapper       = MappedColumnType.base[NodeSeq,       String] (_.toString(),  s => XML.loadString(s"<div>$s</div>"))
}

trait EventTables extends TableProfile {
  import profile.api._

  case class LiftedEventRow(id: Rep[Long], timestamp: Rep[String], eventType: Rep[String], payload: Rep[String])
  case class EventRow(id: Long, timestamp: String, eventType: String, payload: String)

  implicit object EventShape extends CaseClassShape(LiftedEventRow.tupled, EventRow.tupled)

  class EventTableT(tag: Tag) extends Table[EventRow](tag, "event") {
    def id = column[Long]    ("id", O.PrimaryKey,O.AutoInc)
    def timestamp = column[String]("timestamp")
    def event_type = column[String]  ("event_type")
    def payload = column[String]  ("payload")

    //      def * = (id, timestamp, event_type, payload) <>(EventRow.tupled, EventRow.unapply)
    def * = LiftedEventRow(id,timestamp,event_type,payload)
  }

  def EventTable = TableQuery[EventTableT]
}




object Runner extends App  {
//  unfiltered.jetty.Server.http(8080).plan(new AsyncPlan).run()
  val profile = PostgresDriver

  val slickDriver="slick.driver.PostgresDriver"
  val jdbcDriver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localdocker/testdb"
  val user ="skokk"
  val pwd = "skokk"
  slick.codegen.SourceCodeGenerator.main(
    Array(slickDriver, jdbcDriver, url, outputFolder, pkg,user,pwd)
  )

  import slick.driver.PostgresDriver.api._
  val db: PostgresDriver.backend.DatabaseDef = Database.forURL(url,user=user, password= pwd)


  implicit lazy val funContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(null)
  import languageFeature.postfixOps

/*
    val l =  Soknad.length.result
    val y: Future[Int] = for {
      ac1 ← db.run(inserts)
      ac2 ← db.run(l)
    } yield (ac2)

  val yy: dbio.DBIOAction[Int, NoStream, Write with Read] = for {
    ac1 ← inserts
    ac2 ← l
  } yield (ac2)
*/

println("Test")
}
*/

package no.skokk

import java.util.UUID

import no.penger.schema.Tables
import slick.dbio
import slick.dbio.Effect.{Read, Write}
import slick.driver.PostgresDriver
import unfiltered.request.{Path ⇒ UFPath}

import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.language.postfixOps

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

object Runner extends App with Tables {
//  unfiltered.jetty.Server.http(8080).plan(new AsyncPlan).run()
  val profile = PostgresDriver

  val slickDriver="slick.driver.PostgresDriver"
  val jdbcDriver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localdocker/billan"
  val outputFolder = "schema"
  val pkg ="no.penger.schema"
  val user ="skokk"
  val pwd = "skokk"
  slick.codegen.SourceCodeGenerator.main(
    Array(slickDriver, jdbcDriver, url, outputFolder, pkg,user,pwd)
  )

  import slick.driver.PostgresDriver.api._
  val db: PostgresDriver.backend.DatabaseDef = Database.forURL(url,user=user, password= pwd)


    val inserts: dbio.DBIOAction[Unit, NoStream, Write] = DBIO.seq(
    Soknad += SoknadRow(SoknadId(UUID.randomUUID()),"gnarg",None),
    Soknad += SoknadRow(SoknadId(UUID.randomUUID()),"uuargh",None)
    )




  implicit lazy val funContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(null)
  import concurrent.duration._
  import languageFeature.postfixOps

    val l =  Soknad.length.result
    val y: Future[Int] = for {
      ac1 ← db.run(inserts)
      ac2 ← db.run(l)
    } yield (ac2)

  val yy: dbio.DBIOAction[Int, NoStream, Write with Read] = for {
    ac1 ← inserts
    ac2 ← l
  } yield (ac2)

println("Test")
}

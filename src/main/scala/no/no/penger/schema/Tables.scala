package no.penger.schema

import java.util.UUID

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile

  import profile.simple._

  case class SoknadId(uuid:UUID)

  case class SoknadRow(soknadId: SoknadId,fjon:String,
                       opprettet: Option[java.sql.Timestamp] = None)

  implicit def sokidmapper = MappedColumnType.base[SoknadId,UUID](_.uuid,SoknadId.apply)

  /** Table description of table soknad. Objects of this class serve as prototypes for rows in queries. */
  class Soknad(tag: Tag) extends Table[SoknadRow](tag, "soknadspike") {
    def * = (soknadId, fjon, opprettet) <>(SoknadRow.tupled, SoknadRow.unapply)

    /** Database column soknad_id DBType(uuid), PrimaryKey, Length(2147483647,false) */
    val soknadId: Column[SoknadId] = column[SoknadId]("soknad_id", O.PrimaryKey)
    val fjon: Column[String] = column[String]("fjon")

    /** Database column opprettet DBType(timestamptz), Default(None) */
    val opprettet: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("opprettet", O.Default(None))

  }

  /** Collection-like TableQuery object for table Soknad */
  lazy val Soknad = new TableQuery(tag => new Soknad(tag))

  lazy val smurfSoknader: Query[Soknad, SoknadRow, Seq] = Soknad.filter(p⇒p.fjon > "smurf")





}

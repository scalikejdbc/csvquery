package example

import org.scalatest._
import csvquery._
import scalikejdbc._
import org.joda.time._
import skinny.logging.Logging

class UsageSpec extends FunSpec with Matchers with Logging {

  // http://support.spatialkey.com/spatialkey-sample-csv-data/
  val filepath = "src/test/resources/SacramentocrimeJanuary2006.csv"
  val headers = Seq("cdatetime", "address", "district", "beat", "grid", "crimedescr", "ucr_ncic_code", "latitude", "longitude")

  describe("CSVQuery") {

    it("counts csv records") {
      val count = withSession { implicit session =>
        withCSV(CSV(filepath, headers)) { table =>
          sql"select count(1) from $table".map(_.long(1)).single.apply()
        }
      }
      count should equal(Some(7584))
    }

    it("filters csv records") {
      val records = withSession { implicit session =>
        withCSV(CSV(filepath, headers)) { table =>
          val (from, to) = (38.45, 38.50)
          sql"select cdatetime, address, latitude from $table where latitude >= $from and latitude <= $to"
            .toMap.list.apply()
        }
      }
      logger.info("records: " + records.take(5))
      records.size should equal(1258)
    }

  }

  case class CrimeRecord(
    cdatetime: String,
    address: String,
    district: Int,
    beat: String,
    grid: Int,
    crimedescr: String,
    ucrNcicCode: Int,
    latitude: Double,
    longitude: Double)

  object CrimeRecordDAO extends SkinnyCSVMapper[CrimeRecord] {
    def csv = CSV(filepath, headers)
    override def extract(rs: WrappedResultSet, n: ResultName[CrimeRecord]) = autoConstruct(rs, n)
  }

  describe("SkinnyCSVMapper") {
    it("runs queries") {
      val records: Seq[CrimeRecord] = withSession { implicit s =>
        CrimeRecordDAO.where('crimedescr -> "459 PC  BURGLARY BUSINESS").apply()
      }
      logger.info("resuls: " + records.take(5))
      records.size should equal(135)
    }
  }

}

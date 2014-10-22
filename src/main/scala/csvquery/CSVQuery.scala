package csvquery

import scalikejdbc._

object CSVQuery {

  def autoCSVSession: DBSession = {
    val worker = s"csvquery-${System.currentTimeMillis}"
    ConnectionPool.add(worker, s"jdbc:h2:mem:${worker}", "", "")
    NamedAutoSession(worker)
  }

  def withSession[A](action: (DBSession) => A): A = action(autoCSVSession)

  def withCSV[A](csv: CSV)(action: (SQLSyntax) => A): A = action(csv.toTable)

}


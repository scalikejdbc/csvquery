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

  def withCSV[A](c1: CSV, c2: CSV)(action: (SQLSyntax, SQLSyntax) => A): A =
    action(c1.toTable, c2.toTable)

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV)(
    action: (SQLSyntax, SQLSyntax, SQLSyntax) => A
  ): A = {
    action(c1.toTable, c2.toTable, c3.toTable)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV)(
    action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A
  ): A = {
    action(c1.toTable, c2.toTable, c3.toTable, c4.toTable)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV)(
    action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A
  ): A = {
    action(c1.toTable, c2.toTable, c3.toTable, c4.toTable, c5.toTable)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV)(
    action: (
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax
    ) => A
  ): A = {
    action(
      c1.toTable,
      c2.toTable,
      c3.toTable,
      c4.toTable,
      c5.toTable,
      c6.toTable
    )
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV, c7: CSV)(
    action: (
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax
    ) => A
  ): A = {
    action(
      c1.toTable,
      c2.toTable,
      c3.toTable,
      c4.toTable,
      c5.toTable,
      c6.toTable,
      c7.toTable
    )
  }

  def withCSV[A](
    c1: CSV,
    c2: CSV,
    c3: CSV,
    c4: CSV,
    c5: CSV,
    c6: CSV,
    c7: CSV,
    c8: CSV
  )(
    action: (
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax
    ) => A
  ): A = {
    action(
      c1.toTable,
      c2.toTable,
      c3.toTable,
      c4.toTable,
      c5.toTable,
      c6.toTable,
      c7.toTable,
      c8.toTable
    )
  }

  def withCSV[A](
    c1: CSV,
    c2: CSV,
    c3: CSV,
    c4: CSV,
    c5: CSV,
    c6: CSV,
    c7: CSV,
    c8: CSV,
    c9: CSV
  )(
    action: (
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax
    ) => A
  ): A = {
    action(
      c1.toTable,
      c2.toTable,
      c3.toTable,
      c4.toTable,
      c5.toTable,
      c6.toTable,
      c7.toTable,
      c8.toTable,
      c9.toTable
    )
  }

  def withCSV[A](
    c1: CSV,
    c2: CSV,
    c3: CSV,
    c4: CSV,
    c5: CSV,
    c6: CSV,
    c7: CSV,
    c8: CSV,
    c9: CSV,
    c10: CSV
  )(
    action: (
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax,
      SQLSyntax
    ) => A
  ): A = {
    action(
      c1.toTable,
      c2.toTable,
      c3.toTable,
      c4.toTable,
      c5.toTable,
      c6.toTable,
      c7.toTable,
      c8.toTable,
      c9.toTable,
      c10.toTable
    )
  }

}

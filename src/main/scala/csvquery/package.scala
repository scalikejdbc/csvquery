import scalikejdbc._

package object csvquery {

  def autoCSVSession: DBSession = CSVQuery.autoCSVSession

  def withSession[A](action: (DBSession) => A): A = CSVQuery.withSession(action)

  def withCSV[A](csv: CSV)(action: (SQLSyntax) => A): A = CSVQuery.withCSV(csv)(action)

  def withCSV[A](c1: CSV, c2: CSV)(action: (SQLSyntax, SQLSyntax) => A): A = CSVQuery.withCSV(c1, c2)(action)

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5, c6)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV, c7: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5, c6, c7)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV, c7: CSV, c8: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5, c6, c7, c8)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV, c7: CSV, c8: CSV, c9: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5, c6, c7, c8, c9)(action)
  }

  def withCSV[A](c1: CSV, c2: CSV, c3: CSV, c4: CSV, c5: CSV, c6: CSV, c7: CSV, c8: CSV, c9: CSV, c10: CSV)(action: (SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax, SQLSyntax) => A): A = {
    CSVQuery.withCSV(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10)(action)
  }

}

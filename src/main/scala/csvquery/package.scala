import scalikejdbc._

package object csvquery {

  def autoCSVSession: DBSession = CSVQuery.autoCSVSession

  def withSession[A](action: (DBSession) => A): A = CSVQuery.withSession(action)

  def withCSV[A](csv: CSV)(action: (SQLSyntax) => A): A = CSVQuery.withCSV(csv)(action)

}

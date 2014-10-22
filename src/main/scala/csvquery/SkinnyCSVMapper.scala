package csvquery

import skinny.orm._

trait SkinnyCSVMapper[A] extends SkinnyNoIdMapper[A] {

  def csv: CSV

  override def tableName = csv.toTable
  override def defaultAlias = createAlias("csv")
  override def columns = csv.columns

}

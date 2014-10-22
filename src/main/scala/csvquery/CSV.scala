package csvquery

import scalikejdbc._

case class CSV(filepath: String, columns: Seq[String], charset: String = "UTF-8") {

  def toTable: SQLSyntax = {
    val file = sqls"'${unsafe(filepath)}'"
    val csv = sqls"'${unsafe(columns.map(_.toUpperCase).mkString(","))}'"
    val c = sqls"'${unsafe(charset)}'"
    sqls"csvread($file, $csv, $c)"
  }

  private[this] def unsafe(v: String): SQLSyntax = SQLSyntax.createUnsafely(v)
}


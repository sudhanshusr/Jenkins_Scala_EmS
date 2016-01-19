package models

//import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver.simple._
import play.api.mvc.Action
//import play.api.db.slick.DatabaseConfigProvider
//import scalaz.concurrent.Future

class Person(tag: Tag) extends Table[ (Int, String, String) ](tag, "person") {
  def id = column[ Int ]("id", O.PrimaryKey)
  def first_name = column[ String ]("first_name")
  def city = column[ String ]("city")
  override def * = (id, first_name, city)
}
//
object DBUtils {
  
  //val DBUrl = scala.util.Properties.envOrElse("DB_URL", "jdbc:mysql://172.27.59.122:3306/slickdemo")
  //val DBUsr = scala.util.Properties.envOrElse("DB_USR", "root")
  //val DBPaswd = scala.util.Properties.envOrElse("DB_PASWD", "root")
  //println(DBUrl)
  //"jdbc:mysql://172.27.59.122:3306/slickdemo"
  //val db = Database.forURL(DBUrl, DBUsr, DBPaswd, driver = "com.mysql.jdbc.Driver")
   val db = Database.forURL("jdbc:mysql://172.27.59.122:3306/slickdemo", "root", "root", driver = "com.mysql.jdbc.Driver")
  
  //val db = scala.slick.driver.JdbcProfile
  //val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  //val dbConfig = DatabaseC
  //println(scala.util.Properties.envOrElse("DATABASE_URL", "/home"))
  val persons = TableQuery[ Person ]

  def getallperson: List[ models.PersonModel ] = {
    db.withSession { implicit session =>
      val persondetails: List[ models.PersonModel ] = persons.run.toList.map(per => new PersonModel(per._1, per._2, per._3))
      persondetails: List[ models.PersonModel ]
      
    }
  }

  def adduser(person: PersonModel) = {
    db.withSession { implicit session =>
      println("in add")
      persons += (person.id, person.first_name, person.city)
    }
  }

  def deluser(id: Int) = {
    println("DB utis")
    db.withSession { implicit session =>
      println(persons.filter(_.id === id))
      persons.filter(_.id === id).delete
    }
  }

  def getuserbyid(id: Int) = {
    db.withSession { implicit session =>
      val personbyid: Option[ models.PersonModel ] = persons.filter(_.id === id).list.headOption.map(per => new PersonModel(per._1, per._2, per._3))
      personbyid: Option[ models.PersonModel ]
    }
  }
  def updateuser(id: Int, person: PersonModel) = {
    db.withSession { implicit session =>
      persons.where(_.id === id).update(person.id, person.first_name, person.city)
    }
  }
}
//}
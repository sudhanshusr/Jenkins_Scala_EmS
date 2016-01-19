package models

import play.api.data._
import play.api.data.Forms._

case class PersonModel(val id: Int, val first_name: String, val city: String)

object PersonForm {
  val personForm = Form(
    mapping(
      "id" -> number,
      "FirstName" -> text,
      "city" -> text
    )(PersonModel.apply)(PersonModel.unapply)
  )
}

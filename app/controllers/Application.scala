package controllers

import play.api._
import play.api.mvc._
import models.DBUtils
import models.PersonForm
import models.Person
import play.api.data.Form
import views.html.helper.form
import models.PersonModel
//import com.fasterxml.jackson.annotation.ObjectIdGenerators.None
//import models.{Person, PersonForm}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application."))
  }

  /*def add() = Action.async { implicit request =>
    PersonForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.index(errorForm, Seq.empty[User]))),
      data => {
        val newUser = User(0, data.firstName, data.lastName, data.mobile, data.email)
        UserService.addUser(newUser).map(res =>
          Redirect(routes.ApplicationController.index())
        )
      })
  }*/

  def add = Action {
    Ok(views.html.add())
  }

  def list = Action {
    val persons: List[ models.PersonModel ] = DBUtils.getallperson
    Ok(views.html.list(persons))
  }

  def addperson = Action { implicit request =>
    PersonForm.personForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => { BadRequest("thats not good") },
      data => {
        val newperson = PersonModel(data.id, data.first_name, data.city)
        println(newperson)
        DBUtils.adduser(newperson)
        Redirect(routes.Application.list())
      }
    )
  }

  def delperson(id: Int) = Action { implicit request =>
    println("in application")
    DBUtils.deluser(id)
    Redirect(routes.Application.list())
  }

  def edit(id: Int) = Action {
    println(id)
    val per: Option[ models.PersonModel ] = DBUtils.getuserbyid(id)
    println(per)
    Ok(views.html.edit(per))
  }

  def editperson = Action { implicit request =>
    PersonForm.personForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => { BadRequest("thats not good") },
      data => {
        val newperson = PersonModel(data.id, data.first_name, data.city)
        val id = data.id
        println(newperson)
        DBUtils.updateuser(id, newperson)
        Redirect(routes.Application.list())
      }
    )
  }

}

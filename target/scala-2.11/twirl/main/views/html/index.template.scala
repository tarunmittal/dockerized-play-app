
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userId: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.18*/("""

"""),_display_(/*3.2*/main("Your App")/*3.18*/ {_display_(Seq[Any](format.raw/*3.20*/("""

    """),format.raw/*5.5*/("""<!-- Container start -->
    <div class="container">
       	Hello :)

    </div>
""")))}),format.raw/*10.2*/("""
"""))}
  }

  def render(userId:String): play.twirl.api.HtmlFormat.Appendable = apply(userId)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (userId) => apply(userId)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Jul 28 21:21:26 IST 2015
                  SOURCE: /home/tarun/9tanki/github/play_2.3.9_project/dockerized-play-app/app/views/index.scala.html
                  HASH: 9eeb718d5c3c5c4384daac79a85cccc0e8b5072a
                  MATRIX: 723->1|827->17|857->22|881->38|920->40|954->48|1072->136
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|38->10
                  -- GENERATED --
              */
          
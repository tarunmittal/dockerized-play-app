
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>


<html>
    <head>
        <title>"""),_display_(/*8.17*/title),format.raw/*8.22*/("""</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src='"""),_display_(/*10.23*/routes/*10.29*/.Assets.at("javascripts/jquery-1.10.2.min.js")),format.raw/*10.75*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*11.46*/routes/*11.52*/.Assets.at("javascripts/html5.js")),format.raw/*11.86*/("""'> </script>
        <link href='"""),_display_(/*12.22*/routes/*12.28*/.Assets.at("stylesheets/bootstrap.css")),format.raw/*12.67*/("""' rel='stylesheet' type='text/css'  />
        <link href='"""),_display_(/*13.22*/routes/*13.28*/.Assets.at("stylesheets/style-responsive.css")),format.raw/*13.74*/("""' rel='stylesheet' type='text/css'  />
        <link href='"""),_display_(/*14.22*/routes/*14.28*/.Assets.at("images/favicon.gif")),format.raw/*14.60*/("""' rel='icon' type='image' />



    </head>
    <body>

    </body>
</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Jul 28 21:21:26 IST 2015
                  SOURCE: /home/tarun/9tanki/github/play_2.3.9_project/dockerized-play-app/app/views/main.scala.html
                  HASH: e3ab38845deddd32bcb2445a6bfca1a747a006a7
                  MATRIX: 727->1|845->31|875->35|958->92|983->97|1122->209|1137->215|1204->261|1289->319|1304->325|1359->359|1421->394|1436->400|1496->439|1584->500|1599->506|1666->552|1754->613|1769->619|1822->651
                  LINES: 26->1|29->1|31->3|36->8|36->8|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14
                  -- GENERATED --
              */
          
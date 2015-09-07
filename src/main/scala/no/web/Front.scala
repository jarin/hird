package no.web

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.html.Div

import scala.scalajs.js.JSApp

/**
 * Created by jarnyste on 01/09/15.
 */
object Front extends JSApp {

  def init(div:html.Div)= {
    val child = dom.document
    .createElement("div")
    child.textContent = "content"
    div.appendChild(child)
  }



  def main(): Unit = {
    import org.scalajs.dom
    import dom.document
    val panel =document.getElementById("panel").asInstanceOf[Div]
    init(panel)
  }
}


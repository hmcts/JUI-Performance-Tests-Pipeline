package uk.gov.hmcts.jui.sscs.simulations.checks

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef._
import io.gatling.http.check.url.CurrentLocationCheckType
//import io.gatling.core.check.extractor.css.CssCheckType



/*object CurrentPageCheck {
    def save: CheckBuilder[HttpCheck, Response, Response, String] = currentLocation.saveAs("currentPage")

    def currentPageTemplate: String = "${currentPage}"
}*/

object CurrentPageUrl {
  def save: CheckBuilder[CurrentLocationCheckType,String,String] = currentLocation.saveAs("currentPageUrl")

  def currentPageTemplate: String = "${currentPageUrl}"
}

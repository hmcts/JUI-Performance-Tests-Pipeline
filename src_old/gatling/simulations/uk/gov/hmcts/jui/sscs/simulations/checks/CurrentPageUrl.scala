package uk.gov.hmcts.jui.sscs.simulations.checks

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.response.Response

object CurrentPageUrl {
  def save: CheckBuilder[HttpCheck, Response, Response, String] = currentLocation.saveAs("currentPageUrl")

  def currentPageTemplate: String = "${currentPageUrl}"
}

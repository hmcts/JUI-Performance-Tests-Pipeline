
package uk.gov.hmcts.jui.sscs.scenario.utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration


object Environment {
  val URL_TO_TEST = "https://jui-webapp-aat.service.core-compute-aat.internal"
  val IDAM_WEB_URL = "https://idam.preprod.ccidam.reform.hmcts.net"

  val minThinkTime = 10
  val maxThinkTime = 15


  val constantthinkTime = 4


  val HttpSSCSProtocol = http

  val commonSSCSHeader = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Content-Type" -> "application/json",
    "Origin" -> URL_TO_TEST,
    "Pragma" -> "no-cache")
}

package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUIDocument {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val headers_3 = Environment.headers_3
  val headers_5 = Environment.headers_5

  val openDocument = exec(http("TX05_JUI_OpenDocument_01")
    .get("/api/documents/${documentID}")
      .headers(headers_3))
      .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TX05_JUI_OpenDocument_03")
      .get("/api/documents/${documentID}/binary")
      .headers(headers_5))

}

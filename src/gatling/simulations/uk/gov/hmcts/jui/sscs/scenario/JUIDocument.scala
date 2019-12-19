package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUIDocument {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val headers_3 = Environment.headers_3
  val headers_5 = Environment.headers_5

  val openDocument = exec(http("JUI_070_005_OpenDocument")
    .get("/api/documents/${documentID}")
      .headers(headers_3))

    .exec(http("JUI_070_010_OpenDocument")
      .get("/api/documents/${documentID}/binary")
      .headers(headers_5))
    .pause(MinThinkTime, MaxThinkTime)
}

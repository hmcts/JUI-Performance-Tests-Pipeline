package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUICases {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val pickRandomCase = exec(http("TX03_JUI_SelectCase")
    .get("/api/case/SSCS/Benefit/${P_case}"))

    /*.exec(http("TX03_JUI_SelectCase_icon-wysiwyg-ordered-list.svg")
      .get("/public/images/icon-wysiwyg-ordered-list.svg")
      .resources(http("TC03_JUI_SelectCase_icon-wysiwyg-unordered-list.svg")
        .get("/public/images/icon-wysiwyg-unordered-list.svg"),
        http("TC03_JUI_SelectCase_icon-wysiwyg-underline.svg")
          .get("/public/images/icon-wysiwyg-underline.svg"),
        http("TC03_JUI_SelectCase_icon-wysiwyg-bold.svg")
          .get("/public/images/icon-wysiwyg-bold.svg"),
        http("TC03_JUI_SelectCase_icon-wysiwyg-italic.svg")
          .get("/public/images/icon-wysiwyg-italic.svg")))*/
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TX04_JUI_SelectCase_clickPartiesTab")
      .get("/api/case/SSCS/Benefit/${P_case}/parties"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TX05_JUI_SelectCase_clickCasefileTab")
      .get("/api/case/SSCS/Benefit/${P_case}/casefile"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TX06_JUI_SelectCase_clickTimelineTab")
      .get("/api/case/SSCS/Benefit/${P_case}/timeline"))
    .pause(MinThinkTime, MaxThinkTime)
}

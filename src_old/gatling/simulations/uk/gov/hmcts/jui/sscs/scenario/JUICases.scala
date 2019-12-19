package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUICases {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val pickRandomCase = exec(http("JUI_030_SelectCase")
    .get("/api/case/SSCS/Benefit/${New_Case_Id}")
    //.check(css("ttp://dm-store-aat.service.core-compute-aat.internal:443/documents/(.*)", "value").saveAs("documentId"))
    .check(regex("documents/(.*?)\",\"document_filename").saveAs("documentID"))
  )

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

    .exec(http("JUI_040_SelectPartiesTab")
      .get("/api/case/SSCS/Benefit/${New_Case_Id}/parties"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_050_SelectCaseFileTab")
      .get("/api/case/SSCS/Benefit/${New_Case_Id}/casefile"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_060_SelectTimelineTab")
      .get("/api/case/SSCS/Benefit/${New_Case_Id}/timeline"))
    .pause(MinThinkTime, MaxThinkTime)
}
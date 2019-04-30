package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUIQuestion {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val CommonSSCSHeader = Environment.commonSSCSHeader

  val sendQuestion = exec(http("JUI_080_ClickQuestionsTab")
    .get("/api/case/SSCS/Benefit/${New_Case_Id}/questions"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_090_005_SaveQuestion")
      .post("/api/caseQ/${New_Case_Id}/questions")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"subject":"JUI PT Question 1","question":"JUI PT Question 1","rounds":"1"}"""))
      .resources(http("JUI_090_010_SaveQuestion")
        .get("/case/SSCS/Benefit/${New_Case_Id}/questions?created=success")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_100_CheckQuestionBeforeSending")
    .get("/api/caseQ/${New_Case_Id}/rounds/1"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_110_005_SendQuestion")
      .put("/api/caseQ/${New_Case_Id}/rounds/1")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{}"""))
      .resources(http("JUI_110_010_SendQuestion")
        .get("/case/SSCS/Benefit/${New_Case_Id}/questions?sent=success")))
    .pause(MinThinkTime, MaxThinkTime)

}
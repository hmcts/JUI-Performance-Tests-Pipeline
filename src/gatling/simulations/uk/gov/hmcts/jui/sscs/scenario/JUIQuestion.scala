package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUIQuestion {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val CommonSSCSHeader = Environment.commonSSCSHeader

  val sendQuestion = exec(http("TC07_JUI_SelectCase_clickQuestionsTab")
    .get("/api/case/SSCS/Benefit/${P_case}/questions"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC08_JUI_SelectCase_SaveQuestion")
      .post("/api/caseQ/${P_case}/questions")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"subject":"KJ JUI PT Question 1","question":"KJ JUI PT Question 1","rounds":"1"}"""))
      .resources(http("TC08_JUI_SelectCase_SaveQuestion_success")
        .get("/case/SSCS/Benefit/${P_case}/questions?created=success")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC08_JUI_SelectCase_SaveQuestion_light-f38ad40456-v1.woff2")
      .get("/assets/fonts/light-f38ad40456-v1.woff2")
      .resources(http("TC08_JUI_SelectCase_SaveQuestion_bold-a2452cb66f-v1.woff2")
        .get("/assets/fonts/bold-a2452cb66f-v1.woff2")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC09_JUI_SelectCase_CheckQuestionBeforeSending")
      .get("/api/caseQ/${P_case}/rounds/1"))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC010_JUI_SelectCase_SendQuestion")
      .put("/api/caseQ/${P_case}/rounds/1")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{}"""))
      .resources(http("TC011_JUI_SelectCase_SendQuestion_SentSuccess")
        .get("/case/SSCS/Benefit/${P_case}/questions?sent=success")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC011_JUI_SelectCase_SendQuestion_SentSuccess_bold-a2452cb66f-v1.woff2")
      .get("/assets/fonts/bold-a2452cb66f-v1.woff2"))
    .pause(MinThinkTime, MaxThinkTime)
}

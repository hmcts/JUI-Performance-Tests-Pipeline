package uk.gov.hmcts.jui.sscs.simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario._
import uk.gov.hmcts.jui.sscs.scenario.utils._

import scala.concurrent.duration._

class JUI_SSCSCaseJourney extends Simulation {

  val JUIBaseUrl = scala.util.Properties.envOrElse("URL_TO_TEST", Environment.JUI_URL).toLowerCase()

  val httpSSCSProtocol = Environment.HttpSSCSProtocol
    .baseUrl(JUIBaseUrl)
    .proxy(Proxy("proxyout.reform.hmcts.net", 8080).httpsPort(8080))
    .maxRedirects(10)
    .doNotTrackHeader("1")
  // .disableAutoReferer

  val JUISSCSSCN = scenario("SCN_JUI_SSCSJourney")
    .exec(
      //Logout.logout,
      /*CaseCreationPreReq.randNum,
      CaseCreationPreReq.Homepage,
      CaseCreationPreReq.Login,
      CaseCreationPreReq.CaseCreate,
      CaseCreationPreReq.DocumentUpload,
      CaseCreationPreReq.AssignToJudge,*/
      Browse.landingLoginPage,
      JUILogin.submitLogin,
      //JUICases.setCaseId, // Only required if amending existing cases
      /*JUICases.pickRandomCase,
      JUICases.pickRandomCase,
      JUIDocument.openDocument,
      //JUIDocument.AnnotateDocument, // Not currently working
      JUIQuestion.sendQuestion,
      JUIDecision.submitDecision,*/
      Logout.logout
    )

  setUp(JUISSCSSCN.inject(rampUsers(1) during (1 minute))
  )
    .protocols(httpSSCSProtocol)

}
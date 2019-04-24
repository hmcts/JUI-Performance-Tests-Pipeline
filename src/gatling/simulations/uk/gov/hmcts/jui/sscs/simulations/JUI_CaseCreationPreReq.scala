package uk.gov.hmcts.jui.sscs.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.session._
import uk.gov.hmcts.jui.sscs.scenario.utils._
import uk.gov.hmcts.jui.sscs.scenario._
import scala.concurrent.duration._
import simulations.uk.gov.hmcts.jui.sscs.scenario._

class JUI_CaseCreationPreReq extends Simulation {

  val CCDBaseUrl = scala.util.Properties.envOrElse("CCD_URL", Environment.CCD_WEB_URL).toLowerCase()

  val httpSSCSProtocol = Environment.HttpSSCSProtocol
    .baseURL(CCDBaseUrl)
    .proxy(Proxy("proxyout.reform.hmcts.net", 8080).httpsPort(8080))
  // .disableAutoReferer

  val CCDSSCSSCN = scenario("SCN_CCD_CaseCreate")
    .exec(
      CaseCreationPreReq.randNum,
      //CaseCreationPreReq.sdfDate,
      CaseCreationPreReq.homepage,
      CaseCreationPreReq.login,
      CaseCreationPreReq.CaseCreate,
      CaseCreationPreReq.DocumentUpload,
      CaseCreationPreReq.AssignToJudge
    )

  setUp(CCDSSCSSCN.inject(atOnceUsers(1))).protocols(httpSSCSProtocol)

}

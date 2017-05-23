/**
  * Created by hcolonna on 5/23/2017.
  */

 import scala.concurrent.duration._

 import io.gatling.core.Predef._
 import io.gatling.http.Predef._
 import io.gatling.jdbc.Predef._

class saks1 extends Simulation {


  object Home{
    val home = exec(http("home")
      .get("/Entry.jsp")
      .headers(headers_1))
  }
  object Womancloths {
    val womancloths = exec(http("womancloths")
      .get("/main/SectionPage.jsp?catId=2534374306418048&FOLDER<>folder_id=2534374306418048")
      .headers(headers_1))
  }
  object Search {
    val search = exec(http("search")
      .get(uri26 + "/search/EndecaSearch.jsp?bmForm=header-search&bmFormID=lMcq5Kz&bmUID=lMcq5KA&bmIsForm=true&bmPrevTemplate=%2FEntry.jsp&bmArch=bmForm&bmForm=endeca_search_form_one&bmHidden=submit-search&submit-search=&bmArch=bmSingle&bmSingle=N_Dim&bmHidden=N_Dim&N_Dim=0&bmArch=bmHidden&bmHidden=Ntk&bmHidden=Ntk&Ntk=Entire+Site&bmArch=bmHidden&bmHidden=Ntx&bmHidden=Ntx&Ntx=mode%2Bmatchpartialmax&bmHidden=Ntt&Ntt=coach&bmText=SearchString&SearchString=coach")
      .headers(headers_1))
  }
  val httpProtocol = http
    .baseURL("http://www.saksfifthavenue.com")
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0")

  val headers_0 = Map("Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val headers_1 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_3 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Content-Type" -> "application/ocsp-request",
    "Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val headers_26 = Map(
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Origin" -> "http://www.saksfifthavenue.com",
    "Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val headers_84 = Map(
    "Accept" -> "text/css,*/*;q=0.1",
    "Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val headers_86 = Map(
    "Accept" -> "application/json, text/javascript, */*; q=0.01",
    "Origin" -> "http://widgets.stores.saks.com",
    "Pragma" -> "akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val headers_104 = Map("Pragma" -> "no-cache, akamai-x-get-request-id,akamai-cache-on,akamai-x-cache-remote-on,akamai-x-check-cacheable,akamai-x-get-cache-key,akamai-get-extracted-values,akamai-x-get-nonces,akamai-x-get-get-ssl-client-session-id,akamai-x-true-cache-key,akamai-x-serial-no")

  val uri01 = "http://s.btstatic.com/tag.js"
  val uri26 = "http://www.saksfifthavenue.com"

  val scn = scenario("saks9").exec(Home.home, Womancloths.womancloths, Search.search)

  setUp(
    scn.inject(
      nothingFor(4 seconds), // 1
      atOnceUsers(10), // 2
      rampUsers(10) over(5 seconds), // 3
      constantUsersPerSec(20) during(15 seconds), // 4
      constantUsersPerSec(20) during(15 seconds) randomized, // 5
      rampUsersPerSec(10) to 20 during(10 minutes), // 6
      rampUsersPerSec(10) to 20 during(10 minutes) randomized, // 7
      splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy(10 seconds), // 8
      splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy atOnceUsers(30), // 9
      heavisideUsers(1000) over(20 seconds) // 10
    ).protocols(httpProtocol)
  )


}

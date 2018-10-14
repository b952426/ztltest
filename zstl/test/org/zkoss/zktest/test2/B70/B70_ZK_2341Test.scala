package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2341.zul")
class B70_ZK_2341Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(
      () => {
        val left = jq("@cardlayout").offsetLeft()
        click(jq("@button").eq(1))
        waitResponse()
        sleep(1000)
        verifyTolerant(parseInt(jq("span:contains(this is page 2-1)").eval("closest('table').parent().offset().left")), left, 10)

        click(jq("@button").eq(2))
        waitResponse()
        sleep(1000)
        verifyTolerant(parseInt(jq("span:contains(this is page 3-1)").eval("closest('table').parent().offset().left")), left, 10)

        click(jq("@button").eq(3))
        waitResponse()
        sleep(1000)
        verifyTolerant(parseInt(jq("span:contains(this is page 4-1)").eval("closest('table').parent().offset().left")), left, 10)
      })

  }
}
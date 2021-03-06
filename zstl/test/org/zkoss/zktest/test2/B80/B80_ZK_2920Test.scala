package org.zkoss.zktest.test2.B80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

/**
  *
  * @author Christopher
  */
@Tags(tags = "B80-ZK-2920.zul")
class B80_ZK_2920Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(
      () => {
        val button = jq("button")
        // open chosenbox
        click(button)
        waitResponse(true)
        val chosenbox = jq(".z-chosenbox-input")
        // focus on the input of chosenbox
        click(chosenbox.eq(0))
        waitResponse(true)
        // make sure chosenbox is empty at first
        verifyEquals(0, jq(".z-chosenbox-item").length())
        // select the first option from the chosenbox popup
        click(jq(".z-chosenbox-option").eq(0))
        waitResponse(true)
        // make sure one item is selected
        verifyEquals(1, jq(".z-chosenbox-item").length())
        // click else where to close the chosenbox
        click(jq(".z-window-header"));
        waitResponse(true)
        // open the chosenbox again
        click(button)
        waitResponse(true)
        // make sure the item is still selected
        verifyEquals(1, jq(".z-chosenbox-item").length())
      })
  }
}
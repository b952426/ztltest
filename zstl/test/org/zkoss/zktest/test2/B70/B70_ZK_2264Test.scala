package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2264.zul")
class B70_ZK_2264Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(
      () => {
        // don't support ie9
        val grid = jq("@grid");
        if (!hasNativeScroll(grid.toWidget())) { // ie8 only has native scrollbar
          // for fake scrollbar, we need to do some special steps to make it scroll like manually.
          // simulate mouse over that then we can do mouseDown()
          grid.toWidget().eval("_scrollbar._mouseEnter()");
          val indicator = jq(".z-scrollbar-indicator");
          // move to right side
          dragdropTo(indicator, "100,2", indicator.width() + ",2")
          waitResponse()

          click(jq(".z-paging-next"));
          waitResponse();

          // move to left side
          grid.toWidget().eval("_scrollbar._mouseEnter()");
          dragdropTo(indicator, "2,2", indicator.width() + ",2")
          waitResponse();

          println(jq("@column").eq(1).width());
          verifyTrue("The width of column eqauls -1 means grid can be scrolled.", jq("@column").eq(1).width() < 1);
        }
      })

  }
}
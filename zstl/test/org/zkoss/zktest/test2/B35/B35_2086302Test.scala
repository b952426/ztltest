/* B35_2086302Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Nov 08 22:51:02 GFT 2011 , Created by ldnigro
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B35

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags


/**
  * A test class for bug 2086302
  *
  * @author ldnigro
  *
  */
@Tags(tags = "B35-2086302.zul,C,E,Groupbox,Borderlayout")
class B35_2086302Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(
      () => {
        //click open button
        click(jq("$bt1"));
        waitResponse(true);

        var bl1 = jq("$bl1");
        verifyTrue(bl1.isVisible());
        var bl1h = bl1.height();
        var bl1w = bl1.width();
        verifyTrue(bl1h == 500);

        var bl2 = jq("$bl2");
        var bl2w = bl2.width();
        println("Testing width: " + bl2w)
        //Verify dimensions 25%, 25%, 50%
        var w2w = jq("$w2").parent().outerWidth();
        println("w2w: " + w2w)
        verifyTolerant(parseInt(bl2w * .25 + ""), w2w, 1);

        var c2w = jq("$c2").parent().outerWidth() + 8;
        println("c2w: " + c2w)
        verifyTolerant(parseInt(bl2w * .25 + ""), c2w, 1);

        var e2w = jq(jq("$bl2").find("@east").toWidget().$n("cave")).outerWidth()
        println("e2w: " + e2w)
        verifyTolerant(parseInt(bl2w * 0.5 + ""), e2w, 1);

        //Verify no border
        var bl2east = jq("$bl2").find("@east");
        verifyEquals(bl2east.css("border-top-width"), "0px");
        verifyEquals(bl2east.css("border-left-width"), "0px");
        verifyEquals(bl2east.css("border-right-width"), "0px");
        verifyEquals(bl2east.css("border-bottom-width"), "0px");

        var bl3 = jq("$bl3");
        var bl3w = bl3.width();
        println("Testing width: " + bl3w)
        //Verify dimensions 30%, 40%, 30%
        var w3w = jq("$w3").parent().outerWidth()
        println("w3w: " + w3w)
        verifyEquals(parseInt(bl3w * 0.3 + ""), w3w);

        var c3w = jq(jq("$bl3").find("@center").toWidget().$n("cave")).outerWidth() + 8;
        println("c3w: " + c3w)
        verifyTolerant(parseInt(bl3w * 0.4 + ""), c3w + 1, 1);

        var bl3center = jq("$bl3").find(".z-center");

        //Verify border
        verifyEquals(bl3center.css("border-top-width"), "1px");
        verifyEquals(bl3center.css("border-left-width"), "1px");
        verifyEquals(bl3center.css("border-right-width"), "1px");
        verifyEquals(bl3center.css("border-bottom-width"), "1px");

        var e3w = jq("$e3").parent().outerWidth()
        verifyTolerant(parseInt(bl3w * 0.3 + ""), e3w, 1);

        //Vertical dimensions

        var bl2h = bl2.parent().outerHeight();
        var bl3h = bl3.parent().outerHeight() + 8;

        println("Testing height : " + bl1h)
        println("bl2h" + bl2h)
        println("bl3h" + bl3h)
        verifyEquals(bl2h, parseInt(bl1h / 2));
        verifyEquals(bl3h, parseInt(bl1h / 2));

      }
    );
  }
}

/* B30_1823218Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:00 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase

class B30_1823218Test extends ZTL4ScalaTestCase {
  @Test
  def testLayout() = {
    val ztl$engine = engine()
    val tree = ztl$engine.$f("tree")
    val btn1 = jq("@button").toWidget
    runZTL(() => {
      verifyTolerant(jq(tree.$n("body")).offsetTop(), jq(tree.$n("head")).offsetTop() + jq(tree.$n("head")).height(), 1)
      click(btn1)
      waitResponse()
      verifyTolerant(jq(tree).width(), jq(tree.$n("body")).outerWidth(), 1)
      verifyTolerant(jq(tree).height(), jq(tree.$n("body")).outerHeight(), 1)
    })
  }
}




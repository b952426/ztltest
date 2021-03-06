/* 

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.bind.basic
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

/**
 * @author pao
 */
@Tags(tags = "zbind")
class Z60_CollectionIndexGrid1Test extends ZTL4ScalaTestCase {
  def testArg() = {
    val zul = """
      <include src="/bind/databinding/collection/collection-index-grid.zul"/>
"""

    runZTL(zul, () => {
      var outerbox = jq("$outergrid").toWidget()
      var outerrows = jq(outerbox).find("@rows").toWidget()
      val itemLabel = Array("A", "B", "C", "D")
      verifyEquals(4, outerrows.nChildren())
      var outerrow = outerrows.firstChild()
      for (i <- 0 to itemLabel.length - 1) {
        var outerl = itemLabel(i)
        var rowkid = outerrow.firstChild()
        verifyEquals("" + i, rowkid.attr("value")) // verify the index on label
        rowkid = rowkid.nextSibling()
        verifyEquals(outerl, rowkid.attr("value")) // verify the label on label
        var innergrid = rowkid.nextSibling()
        verifyTrue(innergrid.exists())
        var innerrows = jq(innergrid).find("@row")
        verifyEquals(2, innerrows.length())
        var innerrow = innerrows.first()
        for (j <- 0 to 1) {
          rowkid = innerrow.toWidget().firstChild()
          verifyEquals("" + j, rowkid.attr("value"))
          rowkid = rowkid.nextSibling()
          verifyEquals("" + i, rowkid.attr("value"))
          var innerl = itemLabel(i) + " " + j
          rowkid = rowkid.nextSibling()
          verifyEquals(innerl, rowkid.attr("value"))
          innerrow = innerrows.next()
        }
        rowkid = outerrow.lastChild()
        var btn = jq(rowkid).find("@button").toWidget() // index button
        var msg = jq("$msg").toWidget()
        click(btn)
        waitResponse()
        verifyEquals("item index " + i, msg.attr("value"))
        outerrow = outerrow.nextSibling()
      }
    })
  }
}
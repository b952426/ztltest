package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2271.zul")
class B70_ZK_2271Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2271.zul

	Purpose:
		
	Description:
		
	History:
		Wed, May 07, 2014 11:21:17 AM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	Please click the button, and then it should not show any JS error.
    <button label="Invalidate Listheader" onClick="listhead.invalidate()"/>
    <listbox>
       <listhead id="listhead">
           <listheader label="Column 1" hflex="min"/>
       </listhead>
   </listbox>
</zk>
"""
    runZTL(zscript,
      () => {
        click(jq("@button"));
        waitResponse();
        verifyFalse(jq(".z-error").exists());
      })

  }
}
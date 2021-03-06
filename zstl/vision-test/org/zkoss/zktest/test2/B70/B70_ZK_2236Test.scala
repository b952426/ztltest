package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.annotation.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B70-ZK-2236.zul")
class B70_ZK_2236Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2236.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Apr 01, 2014  3:01:04 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk xmlns:w="client">
1. When you click the button, the inner divs should get the same half size
<separator/>
2. Click the button again to hide the second div, the first div should get the full size. (not half size)
	<hlayout width="1024px" id="parent" style="border: 1px solid red"  spacing="0px">
		<vlayout hflex="1" id="left_part" style="border: 1px solid green">left part - flex1</vlayout>
		<div hflex="1" id="right_part" style="border: 1px solid blue" visible="false">right part - flex1</div>
	</hlayout>

	<button label="toggle right_part visibility" onClick='right_part.visible = !right_part.visible'/>
</zk>"""  
  runZTL(zscript,
    () => {
      verifyImage()
      click(jq(".z-button"))
      waitResponse()
      verifyImage()
    })
    
  }
}
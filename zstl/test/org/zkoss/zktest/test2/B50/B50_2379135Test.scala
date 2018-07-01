/* B50_2379135Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Oct 13 16:06:53 CST 2011 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B50

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget
import org.zkoss.ztl._
import org.zkoss.ztl.unit._

/**
  * A test class for bug 2379135
  *
  * @author benbai
  *
  */
@Tags(tags = "B50-2379135.zul,A,E,Listbox,context menu")
class B50_2379135Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript =
      """
			<window>
			<html><![CDATA[  
			<ol>
			<li>Right click the second item, and then it shall be selected</li>
			<li>Right click the third item, and then it shall be selected</li>
			<li>Right click the button, and the fourth item shalln't be selected.</li>
			</ol>
			]]></html>
			<zscript><![CDATA[
			void show() {
				i.value = "selcted: " + l.selectedItem.label;
			}
			]]></zscript>
			<listbox id="l">
				<listitem id="li1" label="First"/>
				<listitem id="li2" label="Second (right-click)" onRightClick="show()"/>
				<listitem id="li3" label="Third (context)" context="editPopup"/>
				<listitem>
					<listcell><button id="btn1" label="context but no select" context="editPopup"/></listcell>
				</listitem>
			</listbox>
			<label id="i" multiline="true"/>
			<menupopup id="editPopup" onOpen="show()">
			    <menuitem label="Undo"/>
			    <menuitem label="Redo"/>
			    <menu label="Sort">
					<menupopup>
				        <menuitem label="Sort by Name" autocheck="true"/>
				        <menuitem label="Sort by Date" autocheck="true"/>
					</menupopup>
			    </menu>
			</menupopup>
			</window>

    """

    def executor = () => {
      var (li1: Widget,
      li2: Widget,
      li3: Widget,
      btn1: Widget,
      l: Widget) = (
        engine.$f("li1"),
        engine.$f("li2"),
        engine.$f("li3"),
        engine.$f("btn1"),
        engine.$f("l")
      );
      waitResponse();

      contextMenu(li2);
      waitResponse();
      verifyContains(l.$n().get("innerHTML"), "Second")
      contextMenu(li3);
      waitResponse();
      verifyContains(l.$n().get("innerHTML"), "Third")
      contextMenu(btn1);
      waitResponse();
      verifyContains(l.$n().get("innerHTML"), "Third")
    }

    runZTL(zscript, executor);
  }
}
/* B50_3283943Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Oct 13 10:35:09 CST 2011 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B50

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags
import org.zkoss.ztl.unit.Widget

/**
  * A test class for bug 3283943
  *
  * @author benbai
  *
  */
@Tags(tags = "B50-3283943.zul,A,E,Grid,Listbox")
class B50_3283943Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript =
      """
			<zk xmlns:w="client">
				<html><![CDATA[
					<ol>
						<li>Click on Groups and Listgroups, if the UUIDs shown below are different, it is a bug.</li>
					</ol>
				]]></html>
				<grid>
					<rows>
						<group id="group1" onClick="lb1s.value = self.group.uuid">
							<attribute w:name="onClick"><![CDATA[
								var g = this.getGroup(),
									id = g ? g.uuid : 'null';
								this.$f('lb1c').setValue(id);
							]]></attribute>
							Group 1 (click to show getGroup() result)
						</group>
						<row>Row 1-1</row>
						<row>Row 1-2</row>
						<group id="group2" onClick="lb1s.value = self.group.uuid">
							<attribute w:name="onClick"><![CDATA[
								var g = this.getGroup(),
									id = g ? g.uuid : 'null';
								this.$f('lb1c').setValue(id);
							]]></attribute>
							Group 2 (click to show getGroup() result)
						</group>
						<row>Row 2-1</row>
						<row>Row 2-2</row>
						<group id="group3" onClick="lb1s.value = self.group.uuid">
							<attribute w:name="onClick"><![CDATA[
								var g = this.getGroup(),
									id = g ? g.uuid : 'null';
								this.$f('lb1c').setValue(id);
							]]></attribute>
							Group 3 (click to show getGroup() result)
						</group>
						<row>Row 3-1</row>
						<row>Row 3-2</row>
					</rows>
				</grid>
				<div>
					getGroup uuid (server): <label id="lb1s" />
				</div>
				<div>
					getGroup uuid (client): <label id="lb1c" />
				</div>
				<separator />
				<listbox onSelect="lb2s.value = self.selectedItem.listgroup.uuid">
					<attribute w:name="onSelect"><![CDATA[
						var g = this.getSelectedItem().getListgroup(),
							id = g ? g.uuid : 'null';
						this.$f('lb2c').setValue(id);
					]]></attribute>
					<listgroup><listcell>Listgroup 1</listcell></listgroup>
					<listitem id="li1"><listcell>Listitem 1-1</listcell></listitem>
					<listitem><listcell>Listitem 1-2</listcell></listitem>
					<listgroup><listcell>Listgroup 2</listcell></listgroup>
					<listitem id="li2"><listcell>Listitem 2-1</listcell></listitem>
					<listitem><listcell>Listitem 2-2</listcell></listitem>
					<listgroup><listcell>Listgroup 3</listcell></listgroup>
					<listitem id="li3"><listcell>Listitem 3-1</listcell></listitem>
					<listitem><listcell>Listitem 3-2</listcell></listitem>
				</listbox>
				<div>
					getListgroup uuid (server): <label id="lb2s" />
				</div>
				<div>
					getListgroup uuid (client): <label id="lb2c" />
				</div>
			</zk>

    """

    def executor = () => {
      var group1 = engine.$f("group1")
      var group2 = engine.$f("group2")
      var group3 = engine.$f("group3")
      var li1 = engine.$f("li1")
      var li2 = engine.$f("li2")
      var li3 = engine.$f("li3")
      var lb1s =  engine.$f("lb1s")
      var lb1c = engine.$f("lb1c")
      var lb2s = engine.$f("lb2s")
      var lb2c = engine.$f("lb2c")
      waitResponse();

      checkGridUuid(group1, lb1s, lb1c);
      checkGridUuid(group2, lb1s, lb1c);
      checkGridUuid(group3, lb1s, lb1c);
      checkGridUuid(li1, lb2s, lb2c);
      checkGridUuid(li2, lb2s, lb2c);
      checkGridUuid(li3, lb2s, lb2c);
    }

    def checkGridUuid(wgt: Widget, lbs: Widget, lbc: Widget) {
      clickAt(wgt.$n(), "10,3");
      waitResponse();
      verifyEquals(lbs.$n().attr("innerHTML"), lbc.$n().attr("innerHTML"))
    }

    runZTL(zscript, executor);

  }
}
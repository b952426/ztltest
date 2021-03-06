/* B30_1822517Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:00 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B30_1822517Test extends ZTL4ScalaTestCase {
  @Test
  def testRenderer() = {
    var zscript =
      """
<window>
<html><![CDATA[
1. Reload page.<br/>
2. Press change1 to syncModel of the 1st Listbox, it show 3 new Listitems.<br/>
3. Press change2 to syncModel of the 2nd Listbox, it should show 3 new Listitems but NONE is shown.<br/>
4. Reload page.<br/>
5. Press change3 to syncModel of the 2nd Listbox, it show 4 new Listitems.<br/>
6. Press change3 to syncModel of the 2nd Listbox again, it should still show 4 Listitems but it shows nothing. (Looks something wrong with reuse existing Listitem)<br/>

<p>The difference between 2 Listbox is the renderer. </p>
	<ul><li>1st Listbox use renderer which form listitem - listcell (2 level of nested components)</li>
    <li>2nd Listbox use renderer which form listitem - listcell - label (3 level of nested components)</li></ul>
]]></html>
	<zscript>
	import java.util.*;
	public List m = new ArrayList(6);
	m.add("k1");
	m.add("k2");
	m.add("k3");

	public ListModel lm1 = new ListModelList(m);

	//listitem - listcell
	public class Rend1 implements ListitemRenderer {
		public void render(Listitem item, Object obj, int index)
		{
			String entry = (String)obj;
			Listcell cell = new Listcell(entry);
			cell.setParent(item);
		}
	}

	//listitem - listcell - label
	public class Rend2 implements ListitemRenderer {
		public void render(Listitem item, Object obj, int index)
		{
			String entry = (String)obj;
			Listcell cell = new Listcell();
			new Label(entry).setParent(cell);
			cell.setParent(item);
		}
	}

	public ListitemRenderer rend1 = new Rend1();
	public ListitemRenderer rend2 = new Rend2();

	public void change1()
	{
		public List m2 = new ArrayList(6);
		m2.add("change1.key1");
		m2.add("change1.key2");
		m2.add("change1.key3");

		ListModel lm2 = new ListModelList(m2);

		l1.setModel(lm2);
	}

	public void change2()
	{
		public List m2 = new ArrayList(6);
		m2.add("change2.key1");
		m2.add("change2.key2");
		m2.add("change2.key3");

		ListModel lm2 = new ListModelList(m2);

		l2.setModel(lm2);
	}

	public void change3()
	{
		public List m2 = new ArrayList(6);
		m2.add("change3.val1");
		m2.add("change3.val2");
		m2.add("change3.val3");
		m2.add("change3.val4");

		ListModel lm2 = new ListModelList(m2);

		l2.setModel(lm2);
	}
	</zscript>
	<groupbox><caption label="1st Listbox (listcell with label prop)"/>
	<listbox model="${lm1}" itemRenderer="${rend1}" id="l1">
	</listbox>
	</groupbox>
	<groupbox><caption label="2nd Listbox (listcell with a label child)"/>
	<listbox model="${lm1}" itemRenderer="${rend2}" id="l2">
	</listbox>
	</groupbox>
	<button id="btn1" onClick="change1()" label="change1"/>
	<button id="btn2" onClick="change2()" label="change2"/>
	<button id="btn3" onClick="change3()" label="change3"/>
</window>
		 """
    val ztl$engine = engine()
    val l1 = ztl$engine.$f("l1")
    val l2 = ztl$engine.$f("l2")
    val btn1 = ztl$engine.$f("btn1")
    val btn2 = ztl$engine.$f("btn2")
    val btn3 = ztl$engine.$f("btn3")
    runZTL(zscript, () => {
      click(btn1)
      waitResponse()
      verifyEquals("3", jq(l1).find("@listitem").length())
      verifyEquals("change1.key1", jq(l1).find("@listitem:eq(0) div").html())
      verifyEquals("change1.key2", jq(l1).find("@listitem:eq(1) div").html())
      verifyEquals("change1.key3", jq(l1).find("@listitem:eq(2) div").html())
      click(btn2)
      waitResponse()
      verifyEquals("3", jq(l2).find("@listitem").length())
      verifyEquals("change2.key1", jq(l2).find("@listitem:eq(0) @label").html())
      verifyEquals("change2.key2", jq(l2).find("@listitem:eq(1) @label").html())
      verifyEquals("change2.key3", jq(l2).find("@listitem:eq(2) @label").html())
      for (i <- 0 until 3) {
        click(btn3)
        waitResponse()
        verifyEquals("4", jq(l2).find("@listitem").length())
        verifyEquals("change3.val1", jq(l2).find("@listitem:eq(0) @label").html())
        verifyEquals("change3.val2", jq(l2).find("@listitem:eq(1) @label").html())
        verifyEquals("change3.val3", jq(l2).find("@listitem:eq(2) @label").html())
        verifyEquals("change3.val4", jq(l2).find("@listitem:eq(3) @label").html())
      }
    })
  }
}




/* B50_2909820Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:41:58 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B50

;

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B50_2909820Test extends ZTL4ScalaTestCase {
  @Test
  def testclosed() = {
    var zscript =
      """
<zk>
    Please click upon "Item 2", and then click the "addChild" button.
    <separator/>
    You should see the item "Item 2.3.1" is added after "Item 2.2.1", rather
than after "Item 2.2"
    <tree id="tree" width="400px" rows="8">
        <treecols sizable="true">
            <treecol label="Name" />
            <treecol label="Description" align="right" />
        </treecols>
        <treechildren>
            <treeitem>
                <treerow>
                    <treecell label="Item 1" />
                    <treecell label="Item 1 description"/>
                </treerow>
            </treeitem>
            <treeitem>
                <treerow>
                    <treecell label="Item 2" />
                    <treecell label="Item 2 description" />
                </treerow>
                <treechildren>
                    <treeitem>
                        <treerow>
                            <treecell label="Item 2.1" />
                        </treerow>
                        <treechildren>
                            <treeitem>
                                <treerow>
                                    <treecell label="Item 2.1.1" />
                                </treerow>
                            </treeitem>
                            <treeitem>
                                <treerow>
                                    <treecell label="Item 2.1.2" />
                                </treerow>
                            </treeitem>
                        </treechildren>
                    </treeitem>
                    <treeitem>
                        <treerow>
                            <treecell label="Item 2.2" />
                        </treerow>
                        <treechildren>
                            <treeitem>
                                <treerow>
                                    <treecell label="Item 2.2.1" />
                                </treerow>
                            </treeitem>
                        </treechildren>
                    </treeitem>
                </treechildren>
            </treeitem>
            <treeitem label="Item 3" />
        </treechildren>
    </tree>
    <button label="addChild">
        <attribute name="onClick">
            Treeitem ti = tree.getSelectedItem();
Treechildren tcn = ti.getTreechildren();
Treeitem tempnew = new Treeitem();
Treechildren tcnnew = new Treechildren();
tcnnew.setParent(tempnew);
Treerow tr = new Treerow();
Treecell tc1 = new Treecell("Item 2.3.1");
Treecell tc2 = new Treecell("10000");
tr.setParent(tempnew);
tc1.setParent(tr);
tc2.setParent(tr);
tempnew.setParent(tcn);


        </attribute>
    </button>
</zk>
			"""
    val ztl$engine = engine()
    val tree = ztl$engine.$f("tree")
    runZTL(zscript, () => {
      click(jq("@treecell[label=\"Item 2.2\"]"))
      waitResponse()
      click(jq("@button"))
      waitResponse()
      verifyEquals(jq("@treecell[label=\"Item 2.2.1\"]").parent().next().html(), jq("@treecell[label=\"Item 2.3.1\"]").parent().html())
    })
  }
}




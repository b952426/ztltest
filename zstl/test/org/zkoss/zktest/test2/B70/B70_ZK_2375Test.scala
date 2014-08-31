package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2375.zul")
class B70_ZK_2375Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<zk>
    <label multiline="true">
    1. Open tree node 1, 3, 7, 15, 31, 63, 127, 255
    2. Navigate to page 2 then navigate back to page 1
    3. Close tree node 255
    4. Should see tree node 4, 8 (bug if not seeing).
    </label>
    <window apply="org.zkoss.bind.BindComposer" viewModel="@id('vm') @init('org.zkoss.zktest.test2.B70_ZK_2375_TreeVM')">
        <paging id="paging" pageSize="15" totalSize="@load(vm.totalsize)" />
        <tree id="tree" model="@load(vm.model)" mold="paging" paginal="${paging}" rows="15">
            <treecols>
                <treecol label="Col" />
            </treecols>
            <template name="model" var="each">
                <treeitem value="@load(each)">
                    <treerow>
                        <treecell label="t${each}" />
                    </treerow>
                </treeitem>
            </template>
        </tree>
    </window>
</zk>

"""  
  runZTL(zscript,
    () => {
      val icons = jq(".z-tree-icon");
      clickAt(icons.eq(0), "1,1");
      waitResponse();
      clickAt(icons.eq(1), "1,1");
      waitResponse();
      clickAt(icons.eq(2), "1,1");
      waitResponse();
      clickAt(icons.eq(3), "1,1");
      waitResponse();
      clickAt(icons.eq(4), "1,1");
      waitResponse();
      clickAt(icons.eq(5), "1,1");
      waitResponse();
      clickAt(icons.eq(6), "1,1");
      waitResponse();
      clickAt(icons.eq(7), "1,1");
      waitResponse();
      clickAt(jq(".z-paging-next"), "1,1");
      waitResponse();
      clickAt(jq(".z-paging-previous"), "1,1");
      waitResponse();
      clickAt(icons.eq(7), "1,1");
      waitResponse();
      verifyTrue("node t8 should exist.", jq("span.z-treecell-text:contains(t8)").exists());
    })
    
  }
}